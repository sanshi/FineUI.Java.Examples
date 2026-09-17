package com.fineui.java.examples.mobile.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.mobile.MobilePageBase;

import java.util.List;

/**
 * 移动端选择省市县演示页（路由 {@code mobile/window/position-sheng-shi-xian}）：从底部弹出级联下拉框（省→市→县），
 * 选省份/地区市时经回发重绑下级列表；点「选择」校验通过后隐藏窗体并居中通知所选结果。
 */
@FineUIPage("mobile/window/position-sheng-shi-xian")
public class PositionShengShiXian extends MobilePageBase {

    DropDownList ddlSheng;
    DropDownList ddlShi;
    DropDownList ddlXian;
    Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindSheng();
            bindShi();
            bindXian();
        }
    }

    private void bindSheng() {
        ddlSheng.setDataSource(DataSourceUtil.SHENG);
        ddlSheng.dataBind();

        ddlSheng.insertListItem(0, "-1", "选择省份", true);
        ddlSheng.setSelectedValue("-1");
    }

    private void bindShi() {
        String sheng = ddlSheng.getSelectedValue();

        if (sheng != null && !sheng.isEmpty() && !"-1".equals(sheng)) {
            List<String> cities = DataSourceUtil.SHI.get(sheng);
            if (cities != null) {
                ddlShi.setDataSource(cities);
                ddlShi.dataBind();
            }
        }

        ddlShi.insertListItem(0, "-1", "选择地区市", true);
        ddlShi.setSelectedValue("-1");

        // 是否禁用
        ddlShi.setEnabled(ddlShi.getItemCount() != 1);
    }

    private void bindXian() {
        String shi = ddlShi.getSelectedValue();

        if (shi != null && !shi.isEmpty() && !"-1".equals(shi)) {
            List<String> counties = DataSourceUtil.XIAN.get(shi);
            if (counties != null) {
                ddlXian.setDataSource(counties);
                ddlXian.dataBind();
            }
        }

        ddlXian.insertListItem(0, "-1", "选择县级市", true);
        ddlXian.setSelectedValue("-1");

        // 是否禁用
        ddlXian.setEnabled(ddlXian.getItemCount() != 1);
    }

    public void ddlSheng_SelectedIndexChanged(Object sender, EventArgs e) {
        ddlShi.clearItems();
        bindShi();

        ddlXian.clearItems();
        bindXian();
    }

    public void ddlShi_SelectedIndexChanged(Object sender, EventArgs e) {
        ddlXian.clearItems();
        bindXian();
    }

    public void Button1_Click(Object sender, EventArgs e) {
        // 隐藏窗体
        Window1.setHidden(true);

        // 弹出选中的值
        String xian = ddlXian.getSelectedValue();
        showCenterNotify("您选择的省市县：" + ddlSheng.getSelectedValue() + " | " + ddlShi.getSelectedValue()
                + ("-1".equals(xian) ? "" : " | " + xian));
    }
}
