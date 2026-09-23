package com.fineui.java.examples.code;

import com.fineui.java.core.CspConfig;
import com.fineui.java.core.PageContext;
import com.fineui.java.core.PageLifecycle;
import com.fineui.java.core.PageManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** 第三方编辑器场景基类只覆盖 CSP 总开关，确保全站默认严格时这些页面仍不输出策略头。 */
class ThirdPartyEditorPageBaseTest {

    private static final class Probe extends ThirdPartyEditorPageBase {
    }

    @AfterEach
    void tearDown() {
        PageContext.clear();
    }

    @Test
    void pageGetDisablesCspSeededByGlobalConfiguration() {
        Probe page = new Probe();
        PageContext.begin(page, false, "editor/probe");
        new PageManager()
                .cspScripts(true)
                .cspScriptsAllowNonce(true)
                .cspScriptsAllowUnsafeInline(false)
                .cspScriptsAllowUnsafeEval(false);

        PageLifecycle.invokePageGet(page);

        CspConfig csp = PageContext.current().csp();
        assertFalse(csp.isCspScripts());
        assertNull(csp.buildHeaderValue(false, null));
        assertTrue(csp.isCspAllowNonce(), "关闭总开关时无需改写全局播种的其它策略选项");
    }
}
