package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 图片热区选择省份（路由 {@code iframe/select-province}）：子页用 HTML 图片热区
 * （{@code <img usemap='#ChinaMap'>} + 各省 {@code <area href="javascript:select('省')">}），
 * {@code select()} 经 {@code F.getActiveWindow().window.updateProvince(省)} 纯脚本回写父页
 * {@code DropDownList(ddlProvince)} 后隐藏窗体（区别于 {@link PassValueScript} 回写到
 * {@code TextBox}：本页回写目标是下拉框，且选择源是图片热区）。
 */
@FineUIPage("iframe/select-province")
public class SelectProvince extends PageBase {

    DropDownList ddlProvince;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            ddlProvince.setDataSource(DataSourceUtil.SHENG);
            ddlProvince.dataBind();
            ddlProvince.setSelectedValue("安徽");
        }
    }

    public String getLoadTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public void Window1_Close(Object sender, EventArgs e) {
        showNotify("触发了 Window1 的关闭事件！");
    }
}
