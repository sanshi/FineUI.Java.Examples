package com.fineui.java.examples.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.matchesPattern;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** 验证 FineUI 页面、编辑器例外和传统登录页各自的脚本策略边界。 */
@SpringBootTest
@AutoConfigureMockMvc
class StrictCspCoverageTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fineUiPagesUseStrictDefaultAndEditorPagesUseExplicitException() throws Exception {
        mockMvc.perform(get("/basic/hello"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Security-Policy",
                        matchesPattern("script-src 'self' 'nonce-[^']+'(?: [^;]+)?;")))
                .andExpect(header().string("Content-Security-Policy", not(containsString("unsafe-inline"))))
                .andExpect(header().string("Content-Security-Policy", not(containsString("unsafe-eval"))));

        mockMvc.perform(get("/editor/ckeditor"))
                .andExpect(status().isOk())
                .andExpect(header().doesNotExist("Content-Security-Policy"));
    }

    @Test
    void traditionalLoginUsesExternalScriptAndSelfOnlyPolicy() throws Exception {
        mockMvc.perform(get("/traditional/login"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Security-Policy", "script-src 'self';"))
                .andExpect(content().string(containsString("/res/js/traditional-login.js")))
                .andExpect(content().string(not(containsString("<script>"))));
    }

    @Test
    void htmlErrorResponseDoesNotInheritFineUiCsp() throws Exception {
        // 未注册路由在建立 FineUI 页面上下文前就返回 404，不应套用普通页面的 CSP 策略。
        mockMvc.perform(get("/route-that-does-not-exist").accept(MediaType.TEXT_HTML))
                .andExpect(status().isNotFound())
                .andExpect(header().doesNotExist("Content-Security-Policy"));
    }
}
