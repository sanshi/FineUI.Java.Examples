package com.fineui.java.examples.iframe.selectprovince;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 子页：图片热区选择省份（路由 {@code iframe/select-province/iframe-window}）。用 HTML 图片热区
 * （{@code <img usemap='#ChinaMap'>} + 各省 {@code <area href="javascript:select('省')">}），
 * {@code select()} 经 {@code F.getActiveWindow().window.updateProvince(省)} 纯脚本回写父页
 * {@code ddlProvince} 后隐藏窗体。页面无服务端逻辑。
 */
@FineUIPage("iframe/select-province/iframe-window")
public class IFrameWindow extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
