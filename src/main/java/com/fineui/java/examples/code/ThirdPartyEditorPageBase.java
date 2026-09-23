package com.fineui.java.examples.code;

import com.fineui.java.core.EventArgs;

/**
 * 第三方富文本编辑器页面的场景基类。
 *
 * <p>示例站默认启用严格脚本策略后，现有编辑器仍会动态执行内联脚本或字符串代码。为了保留这些教学页面的
 * 完整功能，只在实际加载编辑器的页面上关闭 FineUI 输出的 CSP 响应头。外层入口页和仅负责打开编辑窗口的
 * 页面继续使用严格策略，避免把兼容范围扩大到整个编辑器目录或整个示例站。
 */
public abstract class ThirdPartyEditorPageBase extends PageBase {

    /** 在响应头生成前关闭本页 CSP；所有编辑器页面都通过继承统一执行，不在子类重复声明。 */
    public void Page_Get(Object sender, EventArgs e) {
        getPageManager().cspScripts(false);
    }
}
