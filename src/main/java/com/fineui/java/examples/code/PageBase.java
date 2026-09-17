package com.fineui.java.examples.code;

import com.fineui.java.core.FineUIPageBase;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Map;

/**
 * 示例工程的页面基类：所有示例页都继承它，项目级的公共函数放在这里。
 *
 * <p>什么该放进来：需要请求上下文的（如取会话），或者绝大多数页面都可能用到的。只服务某一类场景的
 * （上传、多语言、移动端、WebUploader）放到对应的场景基类；入参自足的纯函数放静态工具类。
 *
 * <p>不重复包装框架已经提供的能力：{@code isPostBack()}、{@code showNotify(...)}、{@code showAlert(...)}、
 * {@code getQueryParam(...)} 等都由父类 {@link FineUIPageBase} 直接给出，本类不再转发一层。
 */
public abstract class PageBase extends FineUIPageBase {

    /** 当前请求的会话。示例里用它把「编辑后的数据」暂存起来，模拟一张能改的数据表。 */
    protected HttpSession session() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }

    /** HTML 编码：把用户输入或其它不可信数据安全地输出到页面，防止 XSS。 */
    protected String htmlEncode(String text) {
        return HtmlUtils.htmlEscape(text == null ? "" : text);
    }

    // ==================== 表格行的增删改 ====================
    // 示例的数据源是 List<Map<String, Object>>（一个 Map 一行、键是列名），下面几个函数把
    // 「按行标识找/删行」「取下一个行标识」「把改动写回数据行」这几件反复要做的事收在一处。
    // 行标识固定取 Id 列。

    /** 按行标识找到对应的数据行，找不到返回 {@code null}。 */
    protected static Map<String, Object> findRowById(List<Map<String, Object>> source, String rowId) {
        for (Map<String, Object> row : source) {
            if (rowId.equals(String.valueOf(row.get("Id")))) {
                return row;
            }
        }
        return null;
    }

    /** 按行标识删除一行，找不到就什么也不做。 */
    protected static void deleteRowById(List<Map<String, Object>> source, String rowId) {
        source.removeIf(row -> rowId.equals(String.valueOf(row.get("Id"))));
    }

    /** 取下一个可用的行标识：当前最大值 + 1，模拟数据库的自增长列。 */
    protected static int getNextRowId(List<Map<String, Object>> source) {
        int maxId = 0;
        for (Map<String, Object> row : source) {
            int id = Integer.parseInt(String.valueOf(row.get("Id")));
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }

    /**
     * 把表格「改动过的一行」写回数据行：只写 {@code columns} 里列出的列，改动项里其它键（status、id 等）忽略。
     *
     * @param modifiedRow 单元格编辑的一个改动项，即 {@code Grid.getModifiedData()} 的一个元素
     * @param rowData     要更新的数据行；传 {@code null} 时什么也不做（配合 findRowById 找不到行的情形）
     * @param columns     允许写入的列名
     */
    @SuppressWarnings("unchecked")
    protected void updateDataRow(Map<String, Object> modifiedRow, Map<String, Object> rowData, String[] columns) {
        if (rowData == null) {
            return;
        }
        Object valuesObj = modifiedRow.get("values");
        if (!(valuesObj instanceof Map)) {
            return;
        }
        Map<String, Object> values = (Map<String, Object>) valuesObj;
        for (String column : columns) {
            if (values.containsKey(column)) {
                rowData.put(column, values.get(column));
            }
        }
    }

    /** 同上，先按行标识在 {@code source} 里找到数据行，再把改动写回去。 */
    protected void updateDataRow(Map<String, Object> modifiedRow, String rowId, List<Map<String, Object>> source,
                                 String[] columns) {
        updateDataRow(modifiedRow, findRowById(source, rowId), columns);
    }
}
