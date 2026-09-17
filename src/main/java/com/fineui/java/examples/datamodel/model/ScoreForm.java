package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;

/** 嵌套的成绩数据对象。 */
public class ScoreForm {
    @Display(name = "语文")
    private Integer chinese;
    @Display(name = "数学")
    private Integer math;
    @Display(name = "物理")
    private Integer physics;
    @Display(name = "化学")
    private Integer chemistry;

    public Integer getChinese() {
        return chinese;
    }
    public void setChinese(Integer chinese) {
        this.chinese = chinese;
    }
    public Integer getMath() {
        return math;
    }
    public void setMath(Integer math) {
        this.math = math;
    }
    public Integer getPhysics() {
        return physics;
    }
    public void setPhysics(Integer physics) {
        this.physics = physics;
    }
    public Integer getChemistry() {
        return chemistry;
    }
    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }
}
