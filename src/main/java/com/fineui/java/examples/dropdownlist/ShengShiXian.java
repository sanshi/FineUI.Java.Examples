package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 中国省市县联动（路由 {@code drop-down-list/sheng-shi-xian}）：三个下拉列表级联——选省份加载地区市、选地区市加载县区市；
 * 无需在后台维持列表项状态，选中项改变时经回发重新绑定下级列表。
 */
@FineUIPage("drop-down-list/sheng-shi-xian")
public class ShengShiXian extends PageBase {

    protected DropDownList ddlSheng;
    protected DropDownList ddlShi;
    protected DropDownList ddlXian;
    protected Label labResult;

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

    public void btnSubmit_Click(Object sender, EventArgs e) {
        String xian = ddlXian.getSelectedValue();
        labResult.setText("您选择的省市县：" + ddlSheng.getSelectedValue() + " | " + ddlShi.getSelectedValue()
                + ("-1".equals(xian) ? "" : " | " + xian));
    }
}
