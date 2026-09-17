package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;

import java.util.Map;

/** 学生视图模型（ComplexColumn 用）：在 Student 基础上加派生列 FamilyInfo/HobbyInfo（getter 派生，服务端拼中文文案）。 */
public class StudentViewModel extends Student {

    /** 爱好显示名（getter 派生列）：爱好数组逐项转中文名后连接。 */
    @Display(name = "爱好")
    public String getHobbyInfo() {
        if (getHobby() == null) {
            return "";
        }
        Map<String, String> names = Map.of(
                "reading", "读书", "basketball", "篮球", "travel", "旅游", "movie", "电影", "music", "音乐");
        String[] result = new String[getHobby().length];
        for (int i = 0; i < getHobby().length; i++) {
            result[i] = names.getOrDefault(getHobby()[i], getHobby()[i]);
        }
        return String.join(", ", result);
    }

    /** 家庭信息显示名（getter 派生列）：拼接父母姓名。 */
    @Display(name = "家庭信息")
    public String getFamilyInfo() {
        if (getFamily() == null) {
            return "";
        }
        return "父亲: " + getFamily().getFatherName() + ", 母亲: " + getFamily().getMotherName();
    }
}
