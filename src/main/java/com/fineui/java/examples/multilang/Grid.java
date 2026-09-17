package com.fineui.java.examples.multilang;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.MultilangPageBase;
import com.fineui.java.examples.code.StudentGridData;
import org.springframework.context.MessageSource;

/**
 * 多语言表格（路由 {@code multi-lang/grid}）：表格标题与列头文本走 {@code #{...}} 消息表达式
 * （切换语言随之变化）；列渲染器文案（性别/专业/分组等）经 {@code setJavaScriptResources}
 * 注入客户端 {@code F.setResources}，由 {@code F.getResource} 读取。
 */
@FineUIPage("multi-lang/grid")
public class Grid extends MultilangPageBase {

    // 字段名与模板 id 一致；用全限定名避免与本页面类名 Grid 冲突。
    com.fineui.java.core.controls.Grid Grid1;

    public Grid(MessageSource messageSource) {
        super(messageSource);
    }

    public void Page_Load(Object sender, EventArgs e) {
        // 客户端 JS 资源（key 与 grid.js 内 F.getResource 调用一致）
        setJavaScriptResources("Male", "Female", "GridGender", "GridMajor", "GridRowId", "GridRowText",
                "NoSelectionMessage", "NamePrefix", "IntroPrefix");

        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }
}
