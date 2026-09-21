package com.fineui.java.examples.gridurl;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.StudentGridData2;
import com.fineui.java.examples.gridfilter.FilterMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 网址数据源（{@code dataUrl}）示例的数据接口集合：表格通过 jQuery ajax 向这些地址按需拉取 JSON。
 *
 * <p>约定三种响应形态：① 纯数组 {@code [{...}]}（普通/客户端分页排序）；② 数据库分页对象
 * {@code {recordCount, data}}；③ 带合计 {@code {data, summaryData}}。字段严格投影到各示例所需的列，
 * 避免把演示数据里无关字段（及日期对象）一并下发。
 *
 * <p>REST 端点为精确路径匹配（不像页面路由那样大小写/连字符归一化），故每个接口同时登记
 * kebab 与 PascalCase 两种拼写，页面 {@code data-url} 用 kebab、直接访问也可用 PascalCase。
 */
@RestController
public class GridDataUrlDataController {

    private static final String[] BASIC_FIELDS = {"Id", "Name", "Gender", "EntranceYear", "AtSchool", "Major", "Group"};
    private static final String[] SUMMARY_FIELDS = {"Id", "Name", "Gender", "EntranceYear", "AtSchool", "Major", "Fee", "ExtraFee"};

    private final ObjectMapper objectMapper = JsonMapper.builder().build();

    // 基础学生数据源：data2=true → GetDataTable2（22 行，陈萍萍…）；否则 GetDataTable（12 行，张萍萍…）。
    private static List<Map<String, Object>> basicSource(Boolean data2) {
        return (data2 != null && data2) ? StudentGridData2.rows() : StudentGridData.rows();
    }

    private static List<Map<String, Object>> project(List<Map<String, Object>> source, String[] fields) {
        List<Map<String, Object>> out = new ArrayList<>(source.size());
        for (Map<String, Object> row : source) {
            Map<String, Object> picked = new LinkedHashMap<>();
            for (String f : fields) {
                picked.put(f, row.get(f));
            }
            out.add(picked);
        }
        return out;
    }

    private static List<Map<String, Object>> slice(List<Map<String, Object>> all, int pageIndex, int pageSize) {
        if (pageSize <= 0) {
            return new ArrayList<>(all);
        }
        int from = Math.max(0, pageIndex * pageSize);
        if (from >= all.size()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(all.subList(from, Math.min(all.size(), from + pageSize)));
    }

    // —— 普通网址数据源（纯数组）——
    @GetMapping("/grid-data-url/grid-data-url-data")
    public List<Map<String, Object>> gridDataUrlData(@RequestParam(required = false) Boolean data2) {
        return project(basicSource(data2), BASIC_FIELDS);
    }

    // —— 客户端分页（一次性返回全量，客户端切页）——GetDataTable2（22 行）
    @GetMapping("/grid-data-url/paging-data")
    public List<Map<String, Object>> pagingData() {
        return project(StudentGridData2.rows(), BASIC_FIELDS);
    }

    // —— 客户端/服务端排序（纯数组，服务端按 sortField/sortDirection 排序后返回）——
    @GetMapping("/grid-data-url/sorting-data")
    public List<Map<String, Object>> sortingData(@RequestParam(required = false) String sortField,
                                                  @RequestParam(required = false) String sortDirection,
                                                  @RequestParam(required = false) Boolean data2) {
        List<Map<String, Object>> sorted = (data2 != null && data2)
                ? StudentGridData2.sortedRows(sortField, sortDirection)
                : StudentGridData.sortedRows(sortField, sortDirection);
        return project(sorted, BASIC_FIELDS);
    }

    // —— 数据库分页（返回 {recordCount, data}）——GetDataTable（12 行）默认；data2=true 用 GetDataTable2（22 行）
    @GetMapping("/grid-data-url/paging-database-data")
    public Map<String, Object> pagingDatabaseData(@RequestParam(defaultValue = "0") int pageIndex,
                                                  @RequestParam(defaultValue = "5") int pageSize,
                                                  @RequestParam(required = false) Boolean data2) {
        List<Map<String, Object>> all = basicSource(data2);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("recordCount", all.size());
        result.put("data", project(slice(all, pageIndex, pageSize), BASIC_FIELDS));
        return result;
    }

    // —— 数据库分页 + 数据库排序（返回 {recordCount, data}）——GetDataTable2（22 行）
    @GetMapping("/grid-data-url/paging-database-sorting-database-data")
    public Map<String, Object> pagingDatabaseSortingDatabaseData(@RequestParam(required = false) String sortField,
                                                                 @RequestParam(required = false) String sortDirection,
                                                                 @RequestParam(defaultValue = "0") int pageIndex,
                                                                 @RequestParam(defaultValue = "5") int pageSize) {
        List<Map<String, Object>> all = StudentGridData2.sortedRows(sortField, sortDirection);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("recordCount", all.size());
        result.put("data", project(slice(all, pageIndex, pageSize), BASIC_FIELDS));
        return result;
    }

    // —— 合计行（内存分页，整表合计）：{data, summaryData}，GetDataTable2 全量 + Fee/ExtraFee 整表汇总 ——
    @GetMapping("/grid-data-url/paging-summary-data")
    public Map<String, Object> pagingSummaryData() {
        List<Map<String, Object>> all = StudentGridData2.rows();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("data", project(all, SUMMARY_FIELDS));
        result.put("summaryData", sumFees(all));
        return result;
    }

    // —— 合计行（内存分页，当前页合计）：纯数组，当前页合计由列 summaryType 客户端计算 ——
    @GetMapping("/grid-data-url/paging-summary-current-page-data")
    public List<Map<String, Object>> pagingSummaryCurrentPageData() {
        return project(StudentGridData2.rows(), SUMMARY_FIELDS);
    }

    // —— 合计行（数据库分页，整表合计）：{recordCount, data, summaryData}，summaryData 仅首页带 ——
    @GetMapping("/grid-data-url/paging-database-summary-data")
    public Map<String, Object> pagingDatabaseSummaryData(@RequestParam(defaultValue = "0") int pageIndex,
                                                         @RequestParam(defaultValue = "5") int pageSize) {
        List<Map<String, Object>> all = StudentGridData2.rows();
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("recordCount", all.size());
        result.put("data", project(slice(all, pageIndex, pageSize), SUMMARY_FIELDS));
        if (pageIndex == 0) {
            result.put("summaryData", sumFees(all));   // 整表合计只需首页返回一次
        }
        return result;
    }

    // —— 合计行（数据库分页，当前页合计）：{recordCount, data, summaryData}，summaryData 每页带当前页合计 ——
    @GetMapping("/grid-data-url/paging-database-summary-current-page-data")
    public Map<String, Object> pagingDatabaseSummaryCurrentPageData(@RequestParam(defaultValue = "0") int pageIndex,
                                                                    @RequestParam(defaultValue = "5") int pageSize) {
        List<Map<String, Object>> all = StudentGridData2.rows();
        List<Map<String, Object>> page = slice(all, pageIndex, pageSize);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("recordCount", all.size());
        result.put("data", project(page, SUMMARY_FIELDS));
        result.put("summaryData", sumFees(page));   // 当前页合计：按本页数据汇总
        return result;
    }

    // —— 表头过滤（POST）：接收 filteredData（每列过滤条件的 JSON），返回过滤后纯数组 ——
    @PostMapping("/grid-data-url/filter-data")
    public List<Map<String, Object>> filterData(@RequestParam(required = false) String filteredData) {
        List<Map<String, Object>> rows = project(StudentGridData.rows(), BASIC_FIELDS);
        if (filteredData == null || filteredData.isBlank()) {
            return rows;
        }
        List<Map<String, Object>> conditions;
        try {
            conditions = objectMapper.readValue(filteredData, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            return rows;   // 解析失败按不过滤处理
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            if (matchesAll(row, conditions)) {
                result.add(row);
            }
        }
        return result;
    }

    // 逐列过滤条件判定：每列的 items 里任一/全部命中（matcher=any/all），列间取交集。
    @SuppressWarnings("unchecked")
    private static boolean matchesAll(Map<String, Object> row, List<Map<String, Object>> conditions) {
        for (Map<String, Object> cond : conditions) {
            String field = str(cond.get("field"));
            if (field == null) {
                continue;
            }
            Object source = row.get(field);
            List<Map<String, Object>> items = (List<Map<String, Object>>) cond.get("items");
            if (items == null || items.isEmpty()) {
                continue;
            }
            boolean any = "any".equals(cond.get("matcher"));
            boolean columnResult = any ? false : true;
            for (Map<String, Object> item : items) {
                String operator = str(item.get("operator"));
                Object value = item.get("value");
                boolean hit;
                if (value instanceof List<?>) {
                    // 多选（CheckBoxList，如 Major 列）：过滤值是数组，源值等于任一选中项即命中
                    hit = FilterMatchers.tags(source, value);
                } else if (isNumeric(source)) {
                    hit = FilterMatchers.number(operator, source, value);
                } else {
                    hit = FilterMatchers.text(operator, source, value);
                }
                if (any) {
                    columnResult = columnResult || hit;
                } else {
                    columnResult = columnResult && hit;
                }
            }
            if (!columnResult) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumeric(Object v) {
        return v instanceof Number;
    }

    private static String str(Object v) {
        return v == null ? null : v.toString();
    }

    // —— 树表格（全量）——
    @GetMapping("/grid-data-url/tree-grid-data")
    public List<Map<String, Object>> treeGridData() {
        return FileTreeData.all();
    }

    // —— 树表格（延迟加载）：lazyrowid 为空返回根节点，否则返回该节点的直接子节点 ——
    @GetMapping("/grid-data-url/tree-grid-lazy-load-data")
    public List<Map<String, Object>> treeGridLazyLoadData(@RequestParam(required = false) String lazyrowid) {
        if (lazyrowid == null || lazyrowid.isBlank()) {
            return FileTreeData.lazyRoots();
        }
        return FileTreeData.lazyChildrenOf(Integer.parseInt(lazyrowid.trim()));
    }

    private static Map<String, Object> sumFees(List<Map<String, Object>> rows) {
        int fee = 0, extraFee = 0;
        for (Map<String, Object> row : rows) {
            fee += ((Number) row.get("Fee")).intValue();
            extraFee += ((Number) row.get("ExtraFee")).intValue();
        }
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("Fee", fee);
        summary.put("ExtraFee", extraFee);
        return summary;
    }
}
