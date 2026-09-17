package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;

/** 权限数据对象：演示「控件显示名称文本、绑定数值失败后用 ModelState.remove 移除绑定错误」的场景。 */
public class PowerForm {
    @Display(name = "浏览权限")
    private Integer viewPowerId;
    private String viewPowerName;

    public Integer getViewPowerId() {
        return viewPowerId;
    }

    public void setViewPowerId(Integer viewPowerId) {
        this.viewPowerId = viewPowerId;
    }

    public String getViewPowerName() {
        return viewPowerName;
    }

    public void setViewPowerName(String viewPowerName) {
        this.viewPowerName = viewPowerName;
    }
}
