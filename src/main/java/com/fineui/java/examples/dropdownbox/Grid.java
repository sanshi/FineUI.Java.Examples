package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 下拉表格（单选，初始值）演示页（路由 {@code drop-down-box/grid}）：下拉框的弹出面板内放一个 Grid，
 * 选中某行后其值/文本自动同步进下拉框；下拉框初始值 {@code value=105} 反向选中对应行。
 * 「获取下拉框的选中值」回发读取下拉框当前文本与值。
 */
@FineUIPage("drop-down-box/grid")
public class Grid extends PageBase {

    DropDownBox DropDownBox1;
    // 弹出面板内的表格；用全限定名避免与本页面类名 Grid 冲突。
    com.fineui.java.core.controls.Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 内存数据：首屏绑定一次，数据由客户端保管，回发无需重绑。
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, String.join(", ", DropDownBox1.getValues())));
        } else {
            labResult.setText("下拉框为空");
        }
    }
}
