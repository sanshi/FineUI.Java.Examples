package com.fineui.java.examples.mobile;

import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.PageBase;

/**
 * 移动示例页的可选基类：在通用页面基类之上，提供移动端习惯的「居中通知」便捷方法。
 *
 * <p>移动端的通知框通常居中弹出（而非顶部）。这里复用示例站已有的客户端全局函数
 * {@code showCenterNotify}（定义在 {@code res/js/common.js}：{@code F.notify} 居中、最小宽度 200、
 * 3 秒后自动关闭、点遮罩关闭），通过回发命令通道按名调用它，无需在服务端另造通知构建器。
 *
 * <p>用法：移动页继承本类，在事件处理器里调用 {@link #showCenterNotify(String)} 即可。使用本方法的页面
 * 需在自身模板里引入 {@code res/js/common.js}（其中定义了 {@code showCenterNotify}）。
 * 若只需顶部普通通知，直接用父类的 {@code showNotify} 即可。
 */
public abstract class MobilePageBase extends PageBase {

    /** 列表项模板（图标 + 标题 + 描述）：占位 {@code {0}=图标URL}、{@code {1}=标题}、{@code {2}=描述}，配 {@code res/css/datalist.css}。 */
    protected static final String DATALIST_ITEM_TEMPLATE =
            "<table class=\"item-table\"><tr><td><img class=\"item-img\" src=\"%s\"><div class=\"item-text\">%s</div><div class=\"item-desc\">%s</div></td></tr></table>";

    /** 精简列表项模板（图标 + 标题）：占位 {@code {0}=图标URL}、{@code {1}=标题}。 */
    protected static final String DATALIST_SIMPLE_ITEM_TEMPLATE =
            "<table class=\"item-table\"><tr><td><img class=\"item-img\" src=\"%s\"><div class=\"item-text\">%s</div></td></tr></table>";

    /** 居中通知（无图标）。 */
    protected void showCenterNotify(String message) {
        showCenterNotify(message, MessageBoxIcon.None);
    }

    /** 居中通知（带图标）。回发后由客户端全局函数 {@code showCenterNotify(message, messageIcon)} 显示。 */
    protected void showCenterNotify(String message, MessageBoxIcon icon) {
        invokeClientFunction("showCenterNotify", message, icon == null ? "" : icon.clientName());
    }
}
