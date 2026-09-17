package com.fineui.java.examples.window;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体工具栏演示页（路由 {@code window/toolbar}）：窗体顶部/底部各放一个工具栏，
 * 顶部工具栏含工具条文本、分隔符与按钮；底部工具栏含两个服务端按钮，分别用于修改窗体内文本与关闭窗体。
 */
@FineUIPage("window/toolbar")
public class Toolbar extends PageBase {

    com.fineui.java.core.controls.Window Window1;
    com.fineui.java.core.controls.Label mylabel;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 修改窗体内的文本值（追加当前时间以直观看到变化）。 */
    public void btnChangeText_Click(Object sender, EventArgs e) {
        mylabel.setText("这是修改后的值！" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    /** 关闭（隐藏）窗体。 */
    public void btnClose_Click(Object sender, EventArgs e) {
        Window1.setHidden(true);
    }
}
