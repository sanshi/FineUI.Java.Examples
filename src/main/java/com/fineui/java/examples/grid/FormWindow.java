package com.fineui.java.examples.grid;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridCommandEventArgs;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 表格与表单（弹出窗体，表单中保存）：数据在表格里看、在弹出窗体的表单里改。
 *
 * <p>新增 / 编辑都只弹窗体、不动服务端数据；表单里点「保存数据」才回发，服务端按隐藏字段里的行标识
 * 决定新增还是编辑，重新绑定表格后选中这一行并关掉窗体。
 *
 * <p>删除有两个入口，都是为了演示「删除这件事该怎么做」：
 * <ul>
 *   <li>工具栏上的「删除」按钮——选中行的标识只有浏览器知道，所以在客户端确认后随自定义回发把行标识一起送上来；</li>
 *   <li>表格行上的删除命令——走表格的行命令回发，服务端从事件参数里直接拿到行标识。</li>
 * </ul>
 *
 * <p>数据存在会话里，模拟一张能改的数据表。真实项目不要在会话里放大量数据，否则会严重影响服务器性能。
 */
@FineUIPage("grid/form-window")
public class FormWindow extends PageBase {

    private static final String SESSION_KEY = "Grid.FormWindow";

    Grid Grid1;
    Window Window1;
    HiddenField hfFormID;
    TextBox tbxFormUserName;
    RadioButtonList rblFormGender;
    NumberBox nbFormEntranceYear;
    DatePicker dpFormEntranceDate;
    CheckBox cbFormAtSchool;
    DropDownList ddlFormMajor;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindGrid();
        }
    }

    /** 工具栏「删除」按钮的自定义回发：把客户端送来的选中行标识逐个删掉。 */
    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_DeleteRows".equals(e.getEventName())) {
            JsonNode selectedRows = Json.parse(e.getArgument()).path("selectedRows");
            for (JsonNode rowId : selectedRows) {
                deleteRowById(sourceData(), rowId.asText());
            }

            bindGrid();
            showNotify("删除数据成功!（表格数据已重新绑定）");
        }
    }

    /** 表格行上的删除命令：行标识由行命令回发参数直接给出，不需要再从数据键里反推。 */
    public void Grid1_RowCommand(Object sender, GridCommandEventArgs e) {
        if ("Delete".equals(e.getCommandName())) {
            deleteRowById(sourceData(), e.getRowID());

            bindGrid();
            showNotify("删除数据成功!（表格数据已重新绑定）");
        }
    }

    /** 保存表单：隐藏字段里的行标识为空即新增、否则为编辑；保存后重新绑定、选中这一行并关闭窗体。 */
    public void btnSave_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> source = sourceData();

        String rowId = hfFormID.getText();
        Map<String, Object> rowData;
        if (rowId.isEmpty()) {
            // 新增：行标识取当前最大 Id + 1（模拟数据库的自增长列）
            rowData = new LinkedHashMap<>();
            rowData.put("Id", getNextRowId(source));
            source.add(rowData);
        } else {
            // 编辑
            rowData = findRowById(source, rowId);
            if (rowData == null) {
                // 会话里的数据被别的页面改过（比如刚被删除）时会走到这里，此时按新增处理会把行标识算错，故直接提示
                showNotify("要保存的数据已不存在，请重新选择！", MessageBoxIcon.Error);
                return;
            }
        }

        // 姓名
        rowData.put("Name", tbxFormUserName.getValue().trim());
        // 性别（1 男 / 0 女）
        rowData.put("Gender", Integer.parseInt(rblFormGender.getSelectedValue()));
        // 入学年份
        rowData.put("EntranceYear", Integer.parseInt(nbFormEntranceYear.getValue()));
        // 入学日期
        rowData.put("EntranceDate", dpFormEntranceDate.getValue());
        // 是否在校
        rowData.put("AtSchool", cbFormAtSchool.isChecked());
        // 所学专业
        rowData.put("Major", ddlFormMajor.getSelectedValue());

        session().setAttribute(SESSION_KEY, source);

        // 重新绑定表格，选中刚保存的这一行，然后关掉窗体
        bindGrid();
        Grid1.setSelectedRowIdArray(new String[] {String.valueOf(rowData.get("Id"))});
        Window1.setHidden(true);
    }

    private void bindGrid() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    /** 会话里缓存的那张可改数据表；首次访问时用演示数据填充。 */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> copy = new ArrayList<>();
            for (Map<String, Object> row : StudentGridData.rows()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
