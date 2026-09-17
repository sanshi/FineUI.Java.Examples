package com.fineui.java.examples.mobile.animation;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 移动端滑动动画（四个方向）演示页（路由 {@code mobile/animation/slide-position}）：向左/右/上/下滑入面板二，
 * 返回时按对应反方向滑回。全部为声明式静态内容，切换逻辑在客户端。
 */
@FineUIPage("mobile/animation/slide-position")
public class SlidePosition extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
