package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.LargeGridData;
import com.fineui.java.examples.code.PageBase;

/**
 * 分页工具栏类型（路由 {@code grid-paging/paging-type}）：通过 URL 查询参数切换分页器类型（Arrow / NumberBox /
 * ArrowNumberBox / NumberButton / ArrowNumberButton）、是否显示分页信息、数字分页按钮个数（数据库分页 999 行）。
 */
@FineUIPage("grid-paging/paging-type")
public class PagingType extends PageBase {

    Grid Grid1;
    CheckBox cbxShowPagingMessage;
    DropDownList ddlMaxPagingNumberButton;
    Label labPostBackQuery;
    Button btnPtArrow;
    Button btnPtNumberBox;
    Button btnPtArrowNumberBox;
    Button btnPtNumberButton;
    Button btnPtArrowNumberButton;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            String pagingType = getQueryParam("type");
            if (pagingType == null || pagingType.isEmpty()) {
                // 缺省选中值
                pagingType = "ArrowNumberBox";
            }
            Grid1.setPagingType(com.fineui.java.core.enums.PagingType.valueOf(pagingType));

            boolean isNumberButton = "NumberButton".equals(pagingType) || "ArrowNumberButton".equals(pagingType);
            ddlMaxPagingNumberButton.setEnabled(isNumberButton);

            boolean isShowMessage = true;
            String showMessage = getQueryParam("message");
            if (showMessage != null && !showMessage.isEmpty()) {
                isShowMessage = Boolean.parseBoolean(showMessage);
            }
            Grid1.setShowPagingMessage(isShowMessage);
            cbxShowPagingMessage.setChecked(isShowMessage);

            int maxNumberButtonCount = 5;
            String maxNumberButton = getQueryParam("maxnumberbutton");
            if (maxNumberButton != null && !maxNumberButton.isEmpty()) {
                maxNumberButtonCount = Integer.parseInt(maxNumberButton);
            }
            Grid1.setMaxPagingNumberButton(maxNumberButtonCount);

            // 初始化选中的分页工具栏类型（按下对应按钮）
            pressMatchingButton(pagingType);

            loadData();
        }
    }

    private void loadData() {
        // 1. 设置总项数（数据库分页初始化必设）
        Grid1.setRecordCount(999);
        // 2. 获取当前分页数据
        Grid1.setDataSource(com.fineui.java.examples.code.LargeGridData.paged(Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        String pagingType = getQueryParam("type");
        if (pagingType == null || pagingType.isEmpty()) {
            pagingType = "未设置（使用默认 ArrowNumberBox）";
        }
        // 回发地址会保留页面原有查询串，因此事件处理函数仍可直接读取 ?type。
        labPostBackQuery.setText("回发中读取 URL 参数：type=" + pagingType);
        loadData();
    }

    private void pressMatchingButton(String pagingType) {
        switch (pagingType) {
            case "Arrow" -> btnPtArrow.setPressed(true);
            case "NumberBox" -> btnPtNumberBox.setPressed(true);
            case "ArrowNumberBox" -> btnPtArrowNumberBox.setPressed(true);
            case "NumberButton" -> btnPtNumberButton.setPressed(true);
            case "ArrowNumberButton" -> btnPtArrowNumberButton.setPressed(true);
            default -> {
            }
        }
    }
}
