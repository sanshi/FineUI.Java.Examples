package com.fineui.java.examples.block;

import com.fineui.java.core.BuildConstants;
import com.fineui.java.core.FStateCodec;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** 验证三个响应式表单的按钮都能完成服务端回发，并返回各自的成功通知。 */
@SpringBootTest(properties = "fineui.security-csrf=false")
@AutoConfigureMockMvc
class FormPostbackTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
            "btnSubmitForm1, 表单 1 验证并提交成功！",
            "btnSubmitForm2, 表单 2 验证并提交成功！",
            "btnSubmitForm3, 表单 3 验证并提交成功！"
    })
    void submitReturnsSuccessNotice(String buttonId, String message) throws Exception {
        // 只保留目标按钮，直接验证模板声明的事件能找到页面处理函数。
        String state = "[{\"type\":\"Button\",\"id\":\"" + buttonId + "\"}]";
        String encodedState = BuildConstants.LOCAL_DEBUG ? state : FStateCodec.encode(state, 23);

        mockMvc.perform(post("/block/form")
                        .header("X-FineUI-Ajax", "true")
                        .param("__EVENTTARGET", buttonId)
                        .param("__EVENTARGUMENT", "click")
                        .param("__FSTATE", encodedState))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commands[0].type").value("message.notify"))
                .andExpect(jsonPath("$.commands[0].message").value(message));
    }
}
