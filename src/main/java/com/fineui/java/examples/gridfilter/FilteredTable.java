package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.controls.GridColumnFilteredData;
import com.fineui.java.core.controls.GridColumnFilteredItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 表头过滤示例共用的「按当前过滤条件逐行筛选数据」工具。
 *
 * <p>演示数据是一次性返回的内存数据，故这里逐行套用各列的过滤条件；<b>实际项目请勿这么做</b>——应把过滤条件
 * 直接用于数据库检索。每列的过滤条件由 {@link GridColumn#getColumnFilteredData()} 从表格回带的过滤数据提取，
 * 单条条件是否命中交给页面提供的 {@link ItemMatcher}（不同列 / 不同操作符的匹配语义由页面决定）。
 */
public final class FilteredTable {

    /** 单条过滤条件匹配器：给定某行某列的源值 + 一条过滤条件 + 列标识符，返回是否匹配。 */
    @FunctionalInterface
    public interface ItemMatcher {
        boolean matches(Object sourceValue, GridColumnFilteredItem item, String columnId);
    }

    private FilteredTable() {
    }

    /** 按表格当前过滤条件（各列 {@code ColumnFilteredData}）逐行筛选 {@code source}，返回满足全部列条件的行。 */
    public static List<Map<String, Object>> getFilteredRows(List<Map<String, Object>> source, Grid grid, ItemMatcher matcher) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> row : source) {
            boolean keep = true;
            for (GridColumn column : grid.getColumns()) {
                if (!checkRow(row, column, matcher)) {
                    // 只要有一列的过滤条件不满足，本行就被排除，无需再查其它列
                    keep = false;
                    break;
                }
            }
            if (keep) {
                result.add(row);
            }
        }
        return result;
    }

    private static boolean checkRow(Map<String, Object> row, GridColumn column, ItemMatcher matcher) {
        GridColumnFilteredData cfd = column.getColumnFilteredData();
        if (cfd == null || cfd.getItems().isEmpty()) {
            return true;   // 该列无过滤条件 → 直接通过
        }
        String columnId = cfd.getColumnId();
        String field = (cfd.getDataField() != null && !cfd.getDataField().isEmpty()) ? cfd.getDataField() : columnId;
        Object sourceValue = row.get(field);
        String matcherRule = cfd.getMatcher();

        // matcher=all：全部条件满足才算命中；matcher=any：任一条件满足即命中
        boolean valid = "all".equals(matcherRule);
        for (GridColumnFilteredItem item : cfd.getItems()) {
            boolean hit = matcher.matches(sourceValue, item, columnId);
            if (hit) {
                if ("any".equals(matcherRule)) {
                    valid = true;
                    break;
                }
            } else {
                if ("all".equals(matcherRule)) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }
}
