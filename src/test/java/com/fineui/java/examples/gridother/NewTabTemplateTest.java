package com.fineui.java.examples.gridother;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 回归：NewTab 系列的静态窗口地址由 Thymeleaf 按 context-path 输出，不能在页面解析期调用 F.resolveUrl。
 */
@SpringBootTest
@AutoConfigureMockMvc
class NewTabTemplateTest {

    @Autowired
    MockMvc mvc;

    @Test
    void staticWindowUrlsAreContextPathAwareWithoutParserTimeFineUiCalls() throws Exception {
        Map<String, String> pages = Map.of(
                "new-tab", "new-tab-window",
                "new-tab-same-tab", "new-tab-window",
                "new-tab-same-tab-confirm", "new-tab-window",
                "new-tab-hide-update", "new-tab-hide-update-window",
                "new-tab-hide-refresh", "new-tab-hide-refresh-window"
        );

        for (var page : pages.entrySet()) {
            String contextPath = "/java/demo";
            String html = mvc.perform(get(contextPath + "/grid-other/" + page.getKey()).contextPath(contextPath))
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();

            String expectedUrl = contextPath + "/grid-other/" + page.getValue();
            // Thymeleaf JavaScript inline mode 将 / 安全转义为 \/，浏览器解析后仍是同一个 URL。
            String javascriptString = expectedUrl.replace("/", "\\/");
            assertTrue(html.contains("var newTabUrl = \"" + javascriptString + "\";"),
                    () -> page.getKey() + " 应由 Thymeleaf 输出 context-path URL");
            assertFalse(html.contains("var newTabUrl = F.resolveUrl("),
                    () -> page.getKey() + " 不得在页面解析期调用 F.resolveUrl");
        }
    }
}
