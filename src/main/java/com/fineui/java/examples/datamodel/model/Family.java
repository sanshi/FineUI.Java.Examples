package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;

/** 家庭信息（二级属性列用）。 */
public class Family {

    @Display(name = "父亲")
    private String fatherName;

    @Display(name = "母亲")
    private String motherName;

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
}
