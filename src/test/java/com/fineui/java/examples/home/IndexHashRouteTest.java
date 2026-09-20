package com.fineui.java.examples.home;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** 回归：示例首页在 context-path 部署时应把 Hash 保持为应用内短路由。 */
@SpringBootTest
@AutoConfigureMockMvc
class IndexHashRouteTest {

    @Autowired
    MockMvc mvc;

    @Test
    void enablesCompactHashUsingTheRuntimeBaseUrl() throws Exception {
        String contextPath = "/java/demo";
        String html = mvc.perform(get(contextPath + "/").contextPath(contextPath))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(html.contains("\"baseUrl\" : \"/java/demo/\"")
                        || html.contains("\"baseUrl\":\"/java/demo/\""),
                "F.render 应下发当前应用根，供运行时恢复 IFrame 的完整请求 URL");

        String indexJs = mvc.perform(get(contextPath + "/res/js/index.js").contextPath(contextPath))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(indexJs.contains("compactHash: true"),
                "各端共享的首页脚本应默认使用不含 context-path 的 Hash 路由");
    }
}
