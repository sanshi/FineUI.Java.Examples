package com.fineui.java.examples.tabstrip;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * IFrame 内页（路由 {@code tab-strip/iframe-reload-first-empty-page}）：供 {@code tab-strip/iframe-reload-first}
 * 的选项卡内嵌加载；页面显示自身加载时间，用于直观验证第一个选项卡每次激活都重新加载了 IFrame。
 */
@FineUIPage("tab-strip/iframe-reload-first-empty-page")
public class IFrameReLoadFirstEmptyPage extends PageBase {

    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 本页面加载时间（每次加载都取当前时间）。 */
    public String getLoadTime() {
        return LocalTime.now().format(TIME_FORMAT);
    }
}
