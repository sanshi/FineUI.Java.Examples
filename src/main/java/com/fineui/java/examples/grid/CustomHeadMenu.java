package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.MenuCheckBox;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 自定义列头菜单（路由 {@code grid/custom-head-menu}）：关闭内置列头菜单，在列头放自定义筛选图标，点击弹出
 * 独立浮动菜单；菜单项勾选变化经服务端回发，按当前筛选条件重新绑定数据。
 */
@FineUIPage("grid/custom-head-menu")
public class CustomHeadMenu extends PageBase {

    Grid Grid1;
    MenuCheckBox btnSelectAtSchool;
    MenuCheckBox btnSelectNotAtSchool;
    MenuCheckBox btnEntranceYearGreatThan2002;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindGrid();
        }
    }

    private void bindGrid() {
        List<Map<String, Object>> rows = StudentGridData.rows().stream()
                .filter(row -> {
                    boolean atSchool = Boolean.TRUE.equals(row.get("AtSchool"));
                    if (btnSelectAtSchool.isChecked() && !atSchool) {
                        return false;
                    }
                    if (btnSelectNotAtSchool.isChecked() && atSchool) {
                        return false;
                    }
                    if (btnEntranceYearGreatThan2002.isChecked()
                            && ((Number) row.get("EntranceYear")).intValue() <= 2002) {
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
        Grid1.setDataSource(rows);
        Grid1.dataBind();
    }

    public void btnAtSchool_CheckedChanged(Object sender, EventArgs e) {
        bindGrid();
    }

    public void btnEntranceYear_CheckedChanged(Object sender, EventArgs e) {
        bindGrid();
    }
}
