package com.fineui.java.examples.web;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import com.fineui.java.examples.code.StudentGridData;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 表格「导出与下载」示例的服务端入口：把表格数据拼成 HTML 表格（{@code .xls} 扩展名，Excel 可直接打开），
 * 带 {@code Content-Disposition: attachment} 触发浏览器下载。行内下载则输出该行数据的纯文本。
 *
 * <p>页面用一个隐藏 {@code <form>} POST（或 GET）到此入口，服务端不依赖表格控件状态、直接从数据源重建内容。
 */
@RestController
public class ExcelExportController {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final String META = "<meta http-equiv=\"Content-Type\" content=\"application/vnd.ms-excel;charset=utf-8\"/>";

    // 导出全部列（Excel 页）
    @PostMapping("/grid/excel/export")
    public ResponseEntity<byte[]> exportAll() {
        StringBuilder sb = new StringBuilder(META);
        sb.append("<table cellspacing=\"0\" rules=\"all\" border=\"1\" style=\"border-collapse:collapse;\">");
        sb.append("<tr><th></th><th>姓名</th><th>性别</th><th>入学年份</th><th>是否在校</th><th>所学专业</th><th>分组</th><th>注册日期</th></tr>");
        int i = 1;
        for (Map<String, Object> row : StudentGridData.rows()) {
            sb.append("<tr>");
            td(sb, i++);
            td(sb, row.get("Name"));
            td(sb, "1".equals(String.valueOf(row.get("Gender"))) ? "男" : "女");
            td(sb, row.get("EntranceYear"));
            td(sb, Boolean.TRUE.equals(row.get("AtSchool")) ? "√" : "×");
            td(sb, row.get("Major"));
            tdImage(sb, "/res/images/16/" + row.get("Group") + ".png");
            td(sb, ((LocalDate) row.get("LogTime")).format(DATE_FMT));
            sb.append("</tr>");
        }
        sb.append("</table>");
        return excelFile(sb.toString());
    }

    // 导出（带用户输入的语文成绩）——GET 与 POST 两种方式，content 为 {rowId: score} 的 JSON
    @GetMapping("/grid/excel-input/export")
    public ResponseEntity<byte[]> exportInputGet(@RequestParam(required = false) String content) {
        return exportInput(content);
    }

    @PostMapping("/grid/excel-input/export")
    public ResponseEntity<byte[]> exportInputPost(@RequestParam(required = false) String content) {
        return exportInput(content);
    }

    private ResponseEntity<byte[]> exportInput(String content) {
        JsonNode scores = readJson(content);
        StringBuilder sb = new StringBuilder(META);
        sb.append("<table cellspacing=\"0\" rules=\"all\" border=\"1\" style=\"border-collapse:collapse;\">");
        sb.append("<tr><th></th><th>姓名</th><th>性别</th><th>入学年份</th><th>是否在校</th><th>所学专业</th><th>分组</th><th>语文成绩</th></tr>");
        int i = 1;
        for (Map<String, Object> row : StudentGridData.rows()) {
            String rowId = String.valueOf(row.get("Id"));
            sb.append("<tr>");
            td(sb, i++);
            td(sb, row.get("Name"));
            td(sb, "1".equals(String.valueOf(row.get("Gender"))) ? "男" : "女");
            td(sb, row.get("EntranceYear"));
            td(sb, Boolean.TRUE.equals(row.get("AtSchool")) ? "√" : "×");
            td(sb, row.get("Major"));
            tdImage(sb, "/res/images/16/" + row.get("Group") + ".png");
            td(sb, scores.has(rowId) ? scores.get(rowId).asText() : "");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return excelFile(sb.toString());
    }

    // 行内下载：content 为该行数据 JSON（{id,index,values}），输出该行数据的纯文本 row_{id}.txt
    @PostMapping("/grid/excel-row-command-download/export")
    public ResponseEntity<byte[]> exportRow(@RequestParam(required = false) String content) {
        JsonNode rowData = readJson(content);
        String rowId = rowData.path("id").asText("");
        int rowIndex = rowData.path("index").asInt(0);
        JsonNode v = rowData.path("values");
        StringBuilder sb = new StringBuilder();
        sb.append("你点击了第 ").append(rowIndex + 1).append(" 行，数据如下：\n");
        sb.append("ID：").append(rowId).append('\n');
        sb.append("姓名：").append(v.path("Name").asText("")).append('\n');
        sb.append("性别：").append("1".equals(v.path("Gender").asText("")) ? "男" : "女").append('\n');
        sb.append("入学年份：").append(v.path("EntranceYear").asText("")).append('\n');
        sb.append("是否在校：").append(v.path("AtSchool").asBoolean(false) ? "是" : "否").append('\n');
        sb.append("所学专业：").append(v.path("Major").asText("")).append('\n');
        sb.append("分组：").append(v.path("Group").asText(""));
        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        // 文件名只保留安全字符（防 Content-Disposition 头注入：行 id 来自客户端）
        String safeRowId = rowId.replaceAll("[^A-Za-z0-9_-]", "");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=row_" + safeRowId + ".txt")
                .contentType(MediaType.TEXT_PLAIN)
                .body(bytes);
    }

    // 导出为物理文件：直接把菜单 XML 文件内容作为 text/xml 返回下载（ExcelDownloadFile 页）
    @PostMapping("/grid/excel-download-file/export")
    public ResponseEntity<byte[]> exportDownloadFile() {
        byte[] bytes;
        try {
            bytes = new ClassPathResource("static/res/menu.xml").getInputStream().readAllBytes();
        } catch (Exception e) {
            bytes = new byte[0];
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("text/xml;charset=utf-8"))
                .body(bytes);
    }

    // 导出多表头：content 为客户端解析出的列结构 [{text, columns?}]，据此重建含 rowspan/colspan 的多表头 Excel（ExcelGroupField 页）
    @PostMapping("/grid/excel-group-field/export")
    public ResponseEntity<byte[]> exportGroupField(@RequestParam(required = false) String content) {
        JsonNode columns = readJson(content);
        StringBuilder sb = new StringBuilder(META);
        sb.append("<table cellspacing=\"0\" rules=\"all\" border=\"1\" style=\"border-collapse:collapse;\">");

        MultiHeaderTable mht = new MultiHeaderTable();
        mht.resolve(columns);
        for (List<Object[]> headerRow : mht.multiTable) {
            sb.append("<tr>");
            for (Object[] cell : headerRow) {
                int rowspan = (int) cell[0];
                int colspan = (int) cell[1];
                JsonNode column = (JsonNode) cell[2];
                sb.append("<th")
                        .append(rowspan != 1 ? " rowspan=\"" + rowspan + "\"" : "")
                        .append(colspan != 1 ? " colspan=\"" + colspan + "\"" : "")
                        .append(colspan != 1 ? " style=\"text-align:center;\"" : "")
                        .append(">").append(escapeHtml(column.path("text").asText(""))).append("</th>");
            }
            sb.append("</tr>");
        }

        int rowIndex = 1;
        for (Map<String, Object> row : StudentGridData.rows()) {
            sb.append("<tr>");
            td(sb, rowIndex++);
            td(sb, row.get("Name"));
            td(sb, "1".equals(String.valueOf(row.get("Gender"))) ? "男" : "女");
            td(sb, row.get("ChineseScore"));
            td(sb, row.get("MathScore"));
            td(sb, row.get("TotalScore"));
            td(sb, row.get("Major"));
            td(sb, ((LocalDate) row.get("LogTime")).format(DATE_FMT));
            sb.append("</tr>");
        }
        sb.append("</table>");
        return excelFile(sb.toString());
    }

    // 导出选定列：columns 为被选中的列头文本 JSON 数组，只导出这些列（ExcelSelectColumns 页）
    @PostMapping("/grid/excel-select-columns/export")
    public ResponseEntity<byte[]> exportSelectColumns(@RequestParam(required = false) String columns) {
        JsonNode selected = readJson(columns);
        Set<String> pick = new LinkedHashSet<>();
        if (selected.isArray()) {
            for (JsonNode name : selected) {
                pick.add(name.asText());
            }
        }
        StringBuilder sb = new StringBuilder(META);
        sb.append("<table cellspacing=\"0\" rules=\"all\" border=\"1\" style=\"border-collapse:collapse;\">");

        sb.append("<tr><th></th>");
        for (String col : new String[]{"姓名", "性别", "入学年份", "是否在校", "所学专业", "分组", "注册日期"}) {
            if (pick.contains(col)) {
                sb.append("<th>").append(col).append("</th>");
            }
        }
        sb.append("</tr>");

        int rowIndex = 1;
        for (Map<String, Object> row : StudentGridData.rows()) {
            sb.append("<tr>");
            td(sb, rowIndex++);
            if (pick.contains("姓名")) td(sb, row.get("Name"));
            if (pick.contains("性别")) td(sb, "1".equals(String.valueOf(row.get("Gender"))) ? "男" : "女");
            if (pick.contains("入学年份")) td(sb, row.get("EntranceYear"));
            if (pick.contains("是否在校")) td(sb, Boolean.TRUE.equals(row.get("AtSchool")) ? "√" : "×");
            if (pick.contains("所学专业")) td(sb, row.get("Major"));
            if (pick.contains("分组")) tdImage(sb, "/res/images/16/" + row.get("Group") + ".png");
            if (pick.contains("注册日期")) td(sb, ((LocalDate) row.get("LogTime")).format(DATE_FMT));
            sb.append("</tr>");
        }
        sb.append("</table>");
        return excelFile(sb.toString());
    }

    private static ResponseEntity<byte[]> excelFile(String html) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=myexcel.xls")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(html.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 多表头解析：把嵌套的列结构 {@code [{text, columns?}]} 展开成逐行的表头单元格（含 rowspan/colspan），
     * 以便拼出 HTML 的 {@code <table>} 多级表头。算法：逐层下推子列，父列 colspan 累加子列数、无子列的列 rowspan 向下延伸。
     */
    private static final class MultiHeaderTable {
        // 每行的表头单元格：Object[]{rowspan, colspan, 列节点, 父列节点}
        final List<List<Object[]>> multiTable = new ArrayList<>();

        void resolve(JsonNode cols) {
            List<Object[]> row = new ArrayList<>();
            if (cols.isArray()) {
                for (JsonNode column : cols) {
                    row.add(new Object[]{1, 1, column, null});
                }
            }
            resolveMultiTable(row, 0);
        }

        private void resolveMultiTable(List<Object[]> row, int level) {
            List<Object[]> nextRow = new ArrayList<>();
            for (Object[] cell : row) {
                JsonNode groupField = (JsonNode) cell[2];
                if (groupField != null && groupField.has("columns")) {
                    JsonNode gfCols = groupField.get("columns");
                    cell[1] = gfCols.size();
                    plusColspan(level - 1, (JsonNode) cell[3], gfCols.size() - 1);
                    for (JsonNode column : gfCols) {
                        nextRow.add(new Object[]{1, 1, column, groupField});
                    }
                }
            }
            multiTable.add(row);
            if (!nextRow.isEmpty()) {
                plusRowspan(level);
                resolveMultiTable(nextRow, level + 1);
            }
        }

        // 增加上层各行中「没有子列」的列的 rowspan（向上递归）
        private void plusRowspan(int level) {
            if (level < 0) {
                return;
            }
            for (Object[] cells : multiTable.get(level)) {
                JsonNode groupField = (JsonNode) cells[2];
                if (groupField == null || !groupField.has("columns")) {
                    cells[0] = (int) cells[0] + 1;
                }
            }
            plusRowspan(level - 1);
        }

        // 增加父列（向上递归）的 colspan
        private void plusColspan(int level, JsonNode parent, int plusCount) {
            if (level < 0) {
                return;
            }
            for (Object[] cells : multiTable.get(level)) {
                if (cells[2] == parent) {
                    cells[1] = (int) cells[1] + plusCount;
                    plusColspan(level - 1, (JsonNode) cells[3], plusCount);
                }
            }
        }
    }

    private static JsonNode readJson(String content) {
        try {
            return MAPPER.readTree(content == null || content.isEmpty() ? "{}" : content);
        } catch (Exception e) {
            return MAPPER.createObjectNode();
        }
    }

    private static void td(StringBuilder sb, Object value) {
        sb.append("<td>").append(escapeHtml(value == null ? "" : value.toString())).append("</td>");
    }

    /** HTML 转义单元格文本值（用户输入的成绩等会被反射进导出 HTML，转义防注入）。 */
    private static String escapeHtml(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    private static void tdImage(StringBuilder sb, String url) {
        sb.append("<td><img src=\"").append(url).append("\"></td>");
    }
}
