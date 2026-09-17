package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Panel;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Region 布局演示页（路由 {@code layout/region}）：把整屏划分为上/下/左/右/中五个区域，区域间可拖动分隔条、可折叠。
 * 中间区域的两个按钮演示服务端事件：更新左侧面板标题（含悬浮提示），以及切换底部面板的显示/隐藏。
 */
@FineUIPage("layout/region")
public class Region extends PageBase {

    Panel panelLeftRegion;
    Panel panelBottomRegion;

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 更新左侧面板的标题与悬浮提示（附带更新时间）。 */
    public void Button4_Click(Object sender, EventArgs e) {
        String newTitle = "左侧面板（有提示信息） - 更新时间：" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        panelLeftRegion.setTitle(newTitle);
        panelLeftRegion.setTitleToolTip(newTitle);
    }

    /** 切换底部面板的显示/隐藏。 */
    public void btnHideBottomRegion_Click(Object sender, EventArgs e) {
        panelBottomRegion.setHidden(!panelBottomRegion.isHidden());
    }
}
