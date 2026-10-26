package com.fineui.java.examples.panel;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.ToolbarSeparator;
import com.fineui.java.core.controls.ToolbarText;
import com.fineui.java.core.controls.Toolbar;
import com.fineui.java.core.enums.IconFont;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 面板综合演示页（路由 {@code panel/panel}）：展示面板的标题、图标、工具栏（顶部）、内容面板与折叠展开，
 * 并用一组按钮在服务端动态修改面板标题/图标、工具条文本、工具栏与内容面板的显示状态。
 */
@FineUIPage("panel/panel")
public class Panel extends PageBase {

    com.fineui.java.core.controls.Panel Panel1;
    com.fineui.java.core.controls.Panel Panel2;
    ToolbarText ToolbarText1;
    ToolbarSeparator ToolbarSeparator1;
    Toolbar Toolbar1;

    private static final String STATUS_MARKUP = "<b data-kind=\"status\">服务已连接</b>";
    protected Label labToolbarText;

    public void btnRichToolbarText_Click(Object sender, EventArgs e) {
        ToolbarText1.setTextRawHtml(new RawHtml(STATUS_MARKUP));
    }

    public void btnPlainToolbarText_Click(Object sender, EventArgs e) {
        // 设置普通文本时明确关闭可信标记，同一段内容就会显示为带标签的文字。
        ToolbarText1.setTextRaw(false);
        ToolbarText1.setText(STATUS_MARKUP);
    }

    public void btnClearToolbarText_Click(Object sender, EventArgs e) {
        // null表示清空内容并取消可信标记；只取消标记应调用setTextRaw(false)。
        ToolbarText1.setTextRawHtml(null);
    }

    public void btnReadToolbarText_Click(Object sender, EventArgs e) {
        // 清空后的null显示为空文字，避免Java字符串拼接把它显示成字面的“null”。
        String text = ToolbarText1.getText();
        labToolbarText.setText("文字：" + (text == null ? "" : text) + "；是否可信 HTML 标记："
                + (ToolbarText1.isTextRaw() ? "是" : "否"));
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Panel2.setContent("可以在此放置<a href=\"http://www.w3schools.com/html/\" target=\"_blank\">HTML</a>标签。");
    }

    /** 提示面板当前折叠状态。 */
    public void Button2_Click(Object sender, EventArgs e) {
        showNotify("面板处于" + (Panel1.isCollapsed() ? "折叠" : "展开") + "状态");
    }

    /** 切换内容面板的折叠/展开。 */
    public void Button3_Click(Object sender, EventArgs e) {
        Panel2.setCollapsed(!Panel2.isCollapsed());
    }

    /** 更新面板标题（附带更新时间）。 */
    public void Button4_Click(Object sender, EventArgs e) {
        Panel1.setTitle("面板（" + now() + "）");
    }

    /** 更新工具条文本一的值。 */
    public void Button5_Click(Object sender, EventArgs e) {
        ToolbarText1.setText("工具条文本一（" + now() + "）");
    }

    /** 显示/隐藏工具条文本一（连同其后的分隔线）。 */
    public void Button6_Click(Object sender, EventArgs e) {
        ToolbarText1.setHidden(!ToolbarText1.isHidden());
        ToolbarSeparator1.setHidden(!ToolbarSeparator1.isHidden());
    }

    /** 隐藏工具栏。 */
    public void Button7_Click(Object sender, EventArgs e) {
        Toolbar1.setHidden(true);
    }

    /** 显示工具栏。 */
    public void Button8_Click(Object sender, EventArgs e) {
        Toolbar1.setHidden(false);
    }

    /** 在三个音量图标之间循环切换面板图标。 */
    public void Button9_Click(Object sender, EventArgs e) {
        IconFont current = Panel1.getIconFont();
        if (current == IconFont._VolumeUp) {
            Panel1.setIconFont(IconFont._VolumeDown);
        } else if (current == IconFont._VolumeDown) {
            Panel1.setIconFont(IconFont._VolumeOff);
        } else {
            Panel1.setIconFont(IconFont._VolumeUp);
        }
    }

    /** 清空面板图标。 */
    public void Button10_Click(Object sender, EventArgs e) {
        Panel1.setIconFont(IconFont.None);
    }

    private static String now() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
