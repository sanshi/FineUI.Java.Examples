package com.fineui.java.examples.home;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** 回归：源码查看页的 prettify 皮肤必须随应用 context path 输出。 */
class SourceFileTest {

    @AfterEach
    void clearRequest() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void prettifySkinHrefUsesCurrentContextPath() {
        SourceFile page = new SourceFile();

        MockHttpServletRequest root = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(root));
        assertEquals("/res/google-code-prettify/themes/tomorrow.min.css", page.getPrettifySkinHref());

        MockHttpServletRequest deployment = new MockHttpServletRequest();
        deployment.setContextPath("/java/demo");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(deployment));
        assertEquals("/java/demo/res/google-code-prettify/themes/tomorrow.min.css", page.getPrettifySkinHref());
    }
}
