package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟树的下拉列表（路由 {@code drop-down-list/simulate-tree}）：下拉列表以缩进模拟树形，父节点不可选、仅叶子可选；
 * 支持读取选中项、重新绑定数据源。
 */
@FineUIPage("drop-down-list/simulate-tree")
public class SimulateTree extends PageBase {

    protected DropDownList DropDownList1;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    /** 一个树节点数据项（id/name/level 层级/enableSelect 是否可选）。 */
    public static class JQueryFeature {
        private final String id;
        private final String name;
        private final int level;
        private final boolean enableSelect;

        public JQueryFeature(String id, String name, int level, boolean enableSelect) {
            this.id = id;
            this.name = name;
            this.level = level;
            this.enableSelect = enableSelect;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public boolean isEnableSelect() {
            return enableSelect;
        }
    }

    private void loadData() {
        List<JQueryFeature> myList = new ArrayList<>();
        myList.add(new JQueryFeature("0", "jQuery", 0, false));
        myList.add(new JQueryFeature("1", "核心", 1, false));
        myList.add(new JQueryFeature("2", "选择符", 1, false));
        myList.add(new JQueryFeature("3", "基本选择符", 2, true));
        myList.add(new JQueryFeature("4", "内容选择符", 2, true));
        myList.add(new JQueryFeature("5", "属性选择符", 2, true));
        myList.add(new JQueryFeature("6", "筛选", 1, false));
        myList.add(new JQueryFeature("7", "过滤", 2, true));
        myList.add(new JQueryFeature("8", "查找", 2, true));
        myList.add(new JQueryFeature("9", "事件", 1, false));
        myList.add(new JQueryFeature("10", "页面载入", 2, true));
        myList.add(new JQueryFeature("11", "事件处理", 2, true));
        myList.add(new JQueryFeature("12", "事件委托", 2, true));

        DropDownList1.setDataTextField("name");
        DropDownList1.setDataValueField("id");
        DropDownList1.setDataSimulateTreeLevelField("level");
        DropDownList1.setDataEnableSelectField("enableSelect");
        DropDownList1.setDataSource(myList);
        DropDownList1.dataBind();

        DropDownList1.setSelectedValue("3");
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        if (DropDownList1.getText() != null && !DropDownList1.getText().isEmpty()) {
            labResult.setText(String.format("选中项：%s（值：%s）", DropDownList1.getText(), DropDownList1.getSelectedValue()));
        } else {
            labResult.setText("无选中项");
        }
    }

    public void btnDataBind_Click(Object sender, EventArgs e) {
        // 绑定数据源
        DropDownList1.setDataSource(getData2());
        DropDownList1.dataBind();

        // 设置选中项
        DropDownList1.setSelectedValue("11");
    }

    private List<JQueryFeature> getData2() {
        List<JQueryFeature> myList = new ArrayList<>();
        myList.add(new JQueryFeature("0", "jQuery - 2", 0, false));
        myList.add(new JQueryFeature("1", "核心 - 2", 1, false));
        myList.add(new JQueryFeature("6", "筛选 - 2", 1, false));
        myList.add(new JQueryFeature("7", "过滤 - 2", 2, true));
        myList.add(new JQueryFeature("8", "查找 - 2", 2, true));
        myList.add(new JQueryFeature("9", "事件 - 2", 1, false));
        myList.add(new JQueryFeature("10", "页面载入 - 2", 2, true));
        myList.add(new JQueryFeature("11", "事件处理 - 2", 2, true));
        myList.add(new JQueryFeature("12", "事件委托 - 2", 2, true));

        return myList;
    }
}
