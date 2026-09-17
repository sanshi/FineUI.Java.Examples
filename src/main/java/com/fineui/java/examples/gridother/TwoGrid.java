package com.fineui.java.examples.gridother;

import java.util.List;
import java.util.Map;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.StudentGridData2;
import com.fineui.java.examples.griddynamic.ClassGridData;

/** 主从两个表格（路由 {@code grid-other/two-grid}）：左侧选班级，右侧联动显示该班级的学生。 */
@FineUIPage("grid-other/two-grid")
public class TwoGrid extends PageBase {

    Grid Grid1;
    Grid Grid2;
    Label labelClassDesc;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        List<Map<String, Object>> classTable = ClassGridData.rows();
        int selectedRowId = (int) classTable.get(0).get("Id");

        Grid2.setDataSource(classTable);
        Grid2.dataBind();

        Grid2.setSelectedRowIdArray(new String[] { String.valueOf(selectedRowId) });
        labelClassDesc.setText(getClassDesc(selectedRowId, classTable));

        Grid1.setDataSource(getClassDetailRows(selectedRowId));
        Grid1.dataBind();
    }

    private String getClassDesc(int classId, List<Map<String, Object>> classTable) {
        for (Map<String, Object> row : classTable) {
            int currentClassId = (int) row.get("Id");
            if (classId == currentClassId) {
                return "班级描述：" + row.get("Desc");
            }
        }
        return "";
    }

    private List<Map<String, Object>> getClassDetailRows(int classId) {
        // 第一个班级用一份学生数据，其余班级用另一份
        if (classId == 101) {
            return StudentGridData.rows();
        }
        return StudentGridData2.rows();
    }

    public void Grid2_RowSelect(Object sender, GridRowEventArgs e) {
        int selectedRowId = Integer.parseInt(e.getRowID());

        List<Map<String, Object>> classTable = ClassGridData.rows();
        labelClassDesc.setText(getClassDesc(selectedRowId, classTable));

        Grid1.setDataSource(getClassDetailRows(selectedRowId));
        Grid1.dataBind();
    }
}
