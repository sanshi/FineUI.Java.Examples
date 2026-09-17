package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 最小高度演示页（路由 {@code layout/min-height}）：对比两个窗体——一个设 MinHeight（可自由增高、不低于最小高度），
 * 一个设固定 Height；拖动改变高度即可看出区别。
 */
@FineUIPage("layout/min-height")
public class MinHeight extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
