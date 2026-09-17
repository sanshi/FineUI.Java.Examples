package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 导出文件（行内下载，自定义 POST 请求，手工实现行命令，路由 {@code grid/excel-row-command-download-crc}）：
 * 手工渲染下载链接 + 客户端点击/双击行时把行数据填入隐藏表单 POST 到独立入口，返回该行数据的纯文本下载。
 */
@FineUIPage("grid/excel-row-command-download-crc")
public class ExcelRowCommandDownloadCRC extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
