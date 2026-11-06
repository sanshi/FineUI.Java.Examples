package com.fineui.java.examples.config;

import com.fineui.java.core.CspConfig;
import com.fineui.java.core.FineUIConfig;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridConfig;
import com.fineui.java.core.MobileAdaptionConfig;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.security.FineUICsrfProperties;

import java.util.List;
import java.util.Locale;

/** 浏览 Spring Boot 应用可设置的 FineUI 全局属性。 */
@FineUIPage("config/global-properties")
public class GlobalProperties extends PageBase {
    private static final String CATALOG_JSON = buildCatalog();

    public String getCatalogJson() {
        return CATALOG_JSON;
    }

    private record PropertyRow(String category, String name, String description, String valueType,
                               Object initialValue, String options, Object example, String note) {}

    private static String buildCatalog() {
        // 使用全新的配置对象，避免把示例站在 application.properties 中的覆盖值当成产品初始值。
        var ui = new FineUIConfig();
        var csp = new CspConfig();
        var grid = new GridConfig();
        var mobile = new MobileAdaptionConfig();
        var csrf = new FineUICsrfProperties();
        return Json.encodeForScriptData(List.of(

            // 主题与显示
            new PropertyRow("主题与显示", "fineui.custom-scrollbar", "在支持的桌面浏览器中使用自定义滚动条样式。",
                    "布尔值", ui.isCustomScrollbar(), "true、false", true, ""),
            new PropertyRow("主题与显示", "fineui.default-corner", "使用主题定义的控件圆角。",
                    "布尔值", ui.isDefaultCorner(), "true、false", false, ""),
            new PropertyRow("主题与显示", "fineui.display-mode", "调整控件的字号和间距。",
                    "字符串", ui.getDisplayMode(), "normal（标准）、compact（紧凑）、small（小字）、large（大字）、largeSpace（大间距）", "large", ""),
            new PropertyRow("主题与显示", "fineui.enable-animation", "启用支持动画的控件过渡效果。",
                    "布尔值", ui.isEnableAnimation(), "true、false", true, ""),
            new PropertyRow("主题与显示", "fineui.language", "选择控件提示、按钮和分页栏使用的内置语言。",
                    "字符串", ui.getLanguage(), "zh_CN（简体中文）、zh_TW（繁体中文）、en（英语）", "en", ""),
            new PropertyRow("主题与显示", "fineui.mobile-adaption", "为移动浏览器输出视口设置，让页面按设备宽度显示。",
                    "布尔值", mobile.isMobileAdaption(), "true、false", true, ""),
            new PropertyRow("主题与显示", "fineui.tab-strip-animation-type", "选择切换选项卡时的动画。",
                    "字符串", ui.getTabStripAnimationType(), "slideX（水平滑动）、slideY（垂直滑动）、fade（淡出）、pop（弹出）、flip（翻转）", "fade", "启用控件动画后生效。"),
            new PropertyRow("主题与显示", "fineui.theme", "选择页面使用的主题。",
                    "字符串", ui.getTheme(), "pure_black、pure_blue、pure_green、pure_orange、pure_purple、cupertino、start、dark_hive、flick、south_street", "pure_blue", ""),

            // 应用与资源
            new PropertyRow("应用与资源", "fineui.home", "指定访问站点根地址时显示的 FineUI 页面。",
                    "字符串", "", "已注册的页面路由，例如 basic/hello", "basic/hello", "留空时仍可通过页面自身的路由访问。"),

            // 表单与布局
            new PropertyRow("表单与布局", "fineui.form-change-confirm", "在离开已修改的表单时请求确认，避免丢失输入。",
                    "布尔值", ui.isFormChangeConfirm(), "true、false", true, ""),
            new PropertyRow("表单与布局", "fineui.form-comma-separator", "设置数字输入框在浏览器中显示的千分位分隔符。",
                    "字符串", ui.getFormCommaSeparator(), "分隔字符，例如逗号或空格", " ", ""),
            new PropertyRow("表单与布局", "fineui.form-dot-separator", "设置数字输入框在浏览器中显示的小数分隔符。",
                    "字符串", ui.getFormDotSeparator(), "分隔字符，例如 . 或 ,", ",", ""),
            new PropertyRow("表单与布局", "fineui.form-label-align", "设置表单字段标签的位置。",
                    "字符串", ui.getFormLabelAlign(), "left（左对齐）、right（右对齐）、top（位于上方）", "right", ""),
            new PropertyRow("表单与布局", "fineui.form-label-separator", "设置字段标签文字与输入区域之间的分隔文本。",
                    "字符串", ui.getFormLabelSeparator(), "字符串，可留空", "：", ""),
            new PropertyRow("表单与布局", "fineui.form-label-width", "设置表单字段标签的宽度。",
                    "整数", ui.getFormLabelWidth(), "整数，单位为像素", 120, ""),
            new PropertyRow("表单与布局", "fineui.form-message-target", "选择字段校验失败时显示提示的位置。",
                    "字符串", ui.getFormMessageTarget(), "side（右侧图标）、qtip（浮动提示）、title（浏览器提示）、none（隐藏提示）", "qtip", ""),
            new PropertyRow("表单与布局", "fineui.form-red-star-position", "设置必填字段红色星号的位置。",
                    "字符串", ui.getFormRedStarPosition(), "afterText（文字后）、beforeText（文字前）、afterSeparator（分隔符后）", "beforeText", ""),
            new PropertyRow("表单与布局", "fineui.message-box-display-progress", "显示通知对话框（Notify）自动关闭前的倒计时进度条。",
                    "布尔值", ui.isMessageBoxDisplayProgress(), "true、false", false, ""),
            new PropertyRow("表单与布局", "fineui.number-box-no-wheel", "禁止用鼠标滚轮改变数字输入框的值。",
                    "布尔值", ui.isNumberBoxNoWheel(), "true、false", true, ""),

            // 回发与加载提示
            new PropertyRow("回发与加载提示", "fineui.ajax-loading-mask-text", "设置 AJAX 加载遮罩上的文字。",
                    "字符串", ui.getAjaxLoadingMaskText(), "提示文字，空字符串使用当前语言的加载提示", "正在保存…", "遮罩样式为 mask 且已启用遮罩文字时生效。"),
            new PropertyRow("回发与加载提示", "fineui.ajax-loading-text", "设置页面顶部 AJAX 加载提示的文字。",
                    "字符串", ui.getAjaxLoadingText(), "提示文字，空字符串使用当前语言的加载提示", "正在查询…", ""),
            new PropertyRow("回发与加载提示", "fineui.ajax-loading-type", "选择 AJAX 回发期间的提示样式。",
                    "字符串", ui.getAjaxLoadingType(), "default（页面顶部提示）、mask（页面遮罩）", "mask", ""),
            new PropertyRow("回发与加载提示", "fineui.ajax-timeout", "设置浏览器等待 AJAX 请求完成的超时时间。",
                    "整数", ui.getAjaxTimeout(), "正整数，单位为秒；0 表示不设置超时", 60, ""),
            new PropertyRow("回发与加载提示", "fineui.enable-ajax", "设置回发的全局 AJAX 标记。",
                    "布尔值", ui.isEnableAjax(), "true、false", true, "该值传入客户端，全局标记不会将 Java 控件事件切换为整页表单提交。"),
            new PropertyRow("回发与加载提示", "fineui.enable-ajax-loading", "发起 AJAX 回发时显示加载提示。",
                    "布尔值", ui.isEnableAjaxLoading(), "true、false", true, ""),
            new PropertyRow("回发与加载提示", "fineui.iframe-loading", "加载内嵌页面（IFrame）时显示加载提示。",
                    "布尔值", ui.isIframeLoading(), "true、false", true, ""),
            new PropertyRow("回发与加载提示", "fineui.show-ajax-loading-mask-text", "使用 mask 加载样式时，在遮罩上显示提示文字。",
                    "布尔值", ui.isShowAjaxLoadingMaskText(), "true、false", true, ""),
            new PropertyRow("回发与加载提示", "fineui.simple-error", "服务器返回带标题的 HTML 错误页时，用简短提示显示错误。",
                    "布尔值", ui.isSimpleError(), "true、false", true, ""),

            // 表格与分页
            new PropertyRow("表格与分页", "fineui.grid-column-width", "设置未单独指定宽度的表格列宽。",
                    "整数", ui.getGridColumnWidth(), "整数，单位为像素", 160, ""),
            new PropertyRow("表格与分页", "fineui.grid-convert-long-to-string", "将表格数据源中的长整型转为字符串，避免浏览器丢失整数精度。",
                    "布尔值", grid.isGridConvertLongToString(), "true、false", true, ""),
            new PropertyRow("表格与分页", "fineui.grid-header-filter-icon-visible", "为可筛选的列始终显示表头筛选图标。",
                    "布尔值", ui.isGridHeaderFilterIconVisible(), "true、false", true, ""),
            new PropertyRow("表格与分页", "fineui.grid-header-sort-icon-visible", "为可排序的列始终显示表头排序图标。",
                    "布尔值", ui.isGridHeaderSortIconVisible(), "true、false", true, ""),
            new PropertyRow("表格与分页", "fineui.grid-min-column-width", "设置表格列的默认最小宽度。",
                    "整数", ui.getGridMinColumnWidth(), "整数，单位为像素", 48, ""),
            new PropertyRow("表格与分页", "fineui.grid-pager-align-right", "将分页按钮放在工具栏的右侧。",
                    "布尔值", ui.isGridPagerAlignRight(), "true、false", false, ""),
            new PropertyRow("表格与分页", "fineui.grid-pager-auto-simple-mode", "分页栏宽度不足时自动切换为简洁分页。",
                    "布尔值", ui.isGridPagerAutoSimpleMode(), "true、false", true, "简洁分页显示上一页、当前页和总页数、下一页。"),
            new PropertyRow("表格与分页", "fineui.grid-paging-toolbar-visible", "在未启用分页时也显示表格底部工具栏。",
                    "布尔值", ui.isGridPagingToolbarVisible(), "true、false", false, ""),
            new PropertyRow("表格与分页", "fineui.grid-paging-type", "选择分页栏使用的翻页控件。",
                    "字符串", ui.getGridPagingType(), "arrow、numberBox、arrowNumberBox、numberButton、arrowNumberButton", "arrow", "arrow 表示翻页箭头，numberBox 是页码输入框，numberButton 是页码按钮。"),
            new PropertyRow("表格与分页", "fineui.grid-show-empty-icon-text", "表格无数据且没有指定空状态文字时，显示内置图标和提示。",
                    "布尔值", ui.isGridShowEmptyIconText(), "true、false", true, ""),
            new PropertyRow("表格与分页", "fineui.grid-show-paging-message", "在表格工具栏显示当前分页信息。",
                    "布尔值", ui.isGridShowPagingMessage(), "true、false", true, ""),
            new PropertyRow("表格与分页", "fineui.grid-show-selection-message", "在表格工具栏显示选中行的信息。",
                    "布尔值", ui.isGridShowSelectionMessage(), "true、false", true, ""),
            new PropertyRow("表格与分页", "fineui.grid-show-tooltip-when-truncated", "仅在单元格文本被截断时显示浮动提示。",
                    "布尔值", ui.isGridShowTooltipWhenTruncated(), "true、false", true, "适用于启用了浮动提示的表格列。"),
            new PropertyRow("表格与分页", "fineui.grid-store-date-as-string", "将日期列的客户端数据保存为格式化字符串。",
                    "布尔值", ui.isGridStoreDateAsString(), "true、false", true, "适用于按日期类型读取的列。"),
            new PropertyRow("表格与分页", "fineui.grid-text-selection", "允许用户选中并复制表格单元格中的文字。",
                    "布尔值", ui.isGridTextSelection(), "true、false", true, ""),

            // 页面水印
            new PropertyRow("页面水印", "fineui.watermark", "在页面上铺设重复水印。",
                    "布尔值", ui.isWatermark(), "true、false", true, ""),
            new PropertyRow("页面水印", "fineui.watermark-font-family", "设置页面水印的字体。",
                    "字符串", ui.getWatermarkFontFamily(), "CSS 字体名称，可用逗号分隔备选字体", "Arial, sans-serif", "启用页面水印后生效。"),
            new PropertyRow("页面水印", "fineui.watermark-font-size", "设置页面水印的字号。",
                    "整数", ui.getWatermarkFontSize(), "整数，单位为像素", 16, "启用页面水印后生效。"),
            new PropertyRow("页面水印", "fineui.watermark-font-style", "设置页面水印的字体样式。",
                    "字符串", ui.getWatermarkFontStyle(), "normal、italic、oblique", "italic", "启用页面水印后生效。"),
            new PropertyRow("页面水印", "fineui.watermark-font-weight", "设置页面水印的字重。",
                    "字符串", ui.getWatermarkFontWeight(), "CSS 字重，例如 normal、bold、700", "bold", "启用页面水印后生效。"),
            new PropertyRow("页面水印", "fineui.watermark-letter-spacing", "设置页面水印的字间距。",
                    "整数", ui.getWatermarkLetterSpacing(), "整数，单位为像素", 3, "启用页面水印后生效。"),
            new PropertyRow("页面水印", "fineui.watermark-opacity", "设置页面水印的不透明度。",
                    "小数", ui.getWatermarkOpacity(), "0 至 1 的数值", 0.12, "启用页面水印后生效。"),
            new PropertyRow("页面水印", "fineui.watermark-rotate", "设置页面水印的旋转角度。",
                    "整数", ui.getWatermarkRotate(), "整数，单位为度，负数表示逆时针旋转", -20, "启用页面水印后生效。"),
            new PropertyRow("页面水印", "fineui.watermark-space-x", "设置页面水印的水平间距。",
                    "整数", ui.getWatermarkSpaceX(), "正整数，单位为像素", 240, "启用页面水印后生效。"),
            new PropertyRow("页面水印", "fineui.watermark-space-y", "设置页面水印的垂直间距。",
                    "整数", ui.getWatermarkSpaceY(), "正整数，单位为像素", 180, "启用页面水印后生效。"),
            new PropertyRow("页面水印", "fineui.watermark-text", "设置水印文字。",
                    "字符串", ui.getWatermarkText(), "字符串", "内部资料", "启用水印且文字为空时显示 I♡FineUI。"),
            new PropertyRow("页面水印", "fineui.watermark-z-index", "设置页面水印的叠放层级。",
                    "整数", ui.getWatermarkZIndex(), "整数，对应 CSS z-index", 9000, "启用页面水印后生效。"),

            // 内容与脚本安全
            new PropertyRow("内容与脚本安全", "fineui.csp-allow-nonce", "允许执行带本次请求 nonce 的可信模板内联脚本。",
                    "布尔值", csp.isCspAllowNonce(), "true、false", true, "在 fineui.csp-scripts 开启时生效。框架为可信模板脚本添加与响应头匹配的 nonce。"),
            new PropertyRow("内容与脚本安全", "fineui.csp-allow-self", "在脚本策略中允许加载与页面同源的脚本。",
                    "布尔值", csp.isCspAllowSelf(), "true、false", true, ""),
            new PropertyRow("内容与脚本安全", "fineui.csp-allow-unsafe-eval", "在脚本策略中允许 eval 或 new Function 动态编译脚本。",
                    "布尔值", csp.isCspAllowUnsafeEval(), "true、false", false, "开启 fineui.csp-scripts 时生效。旧业务或第三方库确实需要动态编译时才开启。"),
            new PropertyRow("内容与脚本安全", "fineui.csp-allow-unsafe-inline", "将 unsafe-inline 加入脚本策略，允许内联脚本。",
                    "布尔值", csp.isCspAllowUnsafeInline(), "true、false", false, "开启 fineui.csp-scripts 时生效。可信模板内联脚本优先使用 nonce。"),
            new PropertyRow("内容与脚本安全", "fineui.csp-allow-urls", "在脚本策略中加入指定的脚本来源。",
                    "字符串数组", csp.getCspAllowUrls(), "多个脚本来源用英文逗号分隔", List.of("https://cdn.example.com"), "在 fineui.csp-scripts 开启时生效；未设置时不追加来源。"),
            new PropertyRow("内容与脚本安全", "fineui.csp-scripts", "输出内容安全策略（CSP）的 script-src 响应头，限制脚本来源与执行方式。",
                    "布尔值", csp.isCspScripts(), "true、false", true, "开启后按同源、来源清单、nonce 和动态执行等配置组合出页面策略。"),
            new PropertyRow("内容与脚本安全", "fineui.encode-text", "对控件的普通文本进行 HTML 编码。",
                    "布尔值", ui.isEncodeText(), "true、false", true, ""),
            new PropertyRow("内容与脚本安全", "fineui.security-csrf", "启用 FineUI 与 Spring Security 的 CSRF 令牌集成。",
                    "布尔值", csrf.isSecurityCsrf(), "true、false", true, "须引入 Spring Security。应用自定义安全过滤器链时，在该链中配置 CSRF。"),
            new PropertyRow("内容与脚本安全", "fineui.security-csrf-repository", "选择用于保存 CSRF 令牌的仓库。",
                    "字符串", csrf.getSecurityCsrfRepository().name().toLowerCase(Locale.ROOT), "cookie（浏览器 Cookie）、session（服务器会话）", "session", "启用 fineui.security-csrf 时生效。")
        ));
    }
}
