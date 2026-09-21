package com.fineui.java.examples.gridinput;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 编辑框（方向键导航）（路由 {@code grid-input/arrow-key}）：单元格内渲染表单控件，客户端收集值经自定义回发汇总弹出。 */
@FineUIPage("grid-input/arrow-key")
public class ArrowKey extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("GetInputs".equals(e.getEventName())) {
            JsonNode inputs = Json.parse(e.getArgument());
            showNotifyRaw(GridInputSupport.buildResultTable(inputs));
        }
    }
}
