package com.fineui.java.examples.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 页面基类 {@link PageBase} 里那几个「表格行增删改」助手的边界。
 *
 * <p>它们原先是各页面各抄一份的私有方法（同一个函数抄了几十遍），收进基类之后被 1000 多个示例页共用，
 * 所以每一条边界都得有用例钉住：改坏一处会同时影响所有单元格编辑的示例，而端到端用例只会碰到其中几页。
 *
 * <p>{@code session()} 依赖请求上下文（在请求之外调用会抛异常），不在这里测——它由起服务的端到端用例覆盖。
 */
class PageBaseTest {

    /** 拿一个可实例化的子类来调 protected 方法：基类是 abstract 的。 */
    private static final class Probe extends PageBase {
    }

    private final Probe page = new Probe();

    private static Map<String, Object> row(int id, String name) {
        Map<String, Object> r = new LinkedHashMap<>();
        r.put("Id", id);
        r.put("Name", name);
        return r;
    }

    private static List<Map<String, Object>> source() {
        List<Map<String, Object>> rows = new ArrayList<>();
        rows.add(row(1, "张三"));
        rows.add(row(2, "李四"));
        rows.add(row(3, "王五"));
        return rows;
    }

    private static Map<String, Object> modified(Map<String, Object> values) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("status", "modified");
        m.put("id", "2");
        m.put("values", values);
        return m;
    }

    // ---------- findRowById ----------

    @Test
    void findRowById_按字符串比对Id_找得到() {
        List<Map<String, Object>> rows = source();
        //:: 行标识从客户端来时是字符串，数据源里是 int——比对走 String.valueOf，两边都能对上
        assertSame(rows.get(1), PageBase.findRowById(rows, "2"));
    }

    @Test
    void findRowById_找不到时返回null_而不是抛异常() {
        assertNull(PageBase.findRowById(source(), "99"));
        assertNull(PageBase.findRowById(new ArrayList<>(), "1"));
    }

    @Test
    void findRowById_行标识为null时抛NPE_不静默返回null() {
        //:: 钉住现状：调用方拿到的行标识不该是 null，让它显式炸掉比静默返回「找不到」更容易查
        assertThrows(NullPointerException.class, () -> PageBase.findRowById(source(), null));
    }

    // ---------- deleteRowById ----------

    @Test
    void deleteRowById_删掉匹配的行() {
        List<Map<String, Object>> rows = source();
        PageBase.deleteRowById(rows, "2");
        assertEquals(2, rows.size());
        assertNull(PageBase.findRowById(rows, "2"));
    }

    @Test
    void deleteRowById_找不到就什么也不做() {
        List<Map<String, Object>> rows = source();
        PageBase.deleteRowById(rows, "99");
        assertEquals(3, rows.size());
    }

    @Test
    void deleteRowById_Id重复时会全删掉() {
        //:: 收进基类时用的是 removeIf（删全部），被它替掉的另一种写法只删第一个。
        //:: 示例数据的 Id 唯一，两者行为一致；这条用例把「按 removeIf 语义」这个选择钉下来。
        List<Map<String, Object>> rows = source();
        rows.add(row(2, "李四的重名行"));
        PageBase.deleteRowById(rows, "2");
        assertEquals(2, rows.size());
    }

    // ---------- getNextRowId ----------

    @Test
    void getNextRowId_取当前最大值加一() {
        assertEquals(4, PageBase.getNextRowId(source()));
    }

    @Test
    void getNextRowId_空数据源从1开始() {
        assertEquals(1, PageBase.getNextRowId(new ArrayList<>()));
    }

    @Test
    void getNextRowId_不按顺序也取最大值() {
        List<Map<String, Object>> rows = new ArrayList<>();
        rows.add(row(7, "七"));
        rows.add(row(3, "三"));
        assertEquals(8, PageBase.getNextRowId(rows));
    }

    @Test
    void getNextRowId_Id不是数字时抛异常_不静默给出错的下一个Id() {
        //:: 钉住现状：Id 列必须是整数。静默回退成 1 会让新增行覆盖已有行，比抛异常难查得多
        List<Map<String, Object>> rows = new ArrayList<>();
        rows.add(row(1, "一"));
        rows.get(0).put("Id", "abc");
        assertThrows(NumberFormatException.class, () -> PageBase.getNextRowId(rows));
    }

    // ---------- updateDataRow ----------

    @Test
    void updateDataRow_只写columns里列出的列() {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put("Name", "李四改了");
        values.put("Secret", "不该被写进去");
        Map<String, Object> rowData = row(2, "李四");

        page.updateDataRow(modified(values), rowData, new String[] { "Name" });

        assertEquals("李四改了", rowData.get("Name"));
        assertTrue(!rowData.containsKey("Secret"), "columns 之外的列不该被写入");
    }

    @Test
    void updateDataRow_values里没出现的列保持原样() {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put("Name", "李四改了");
        Map<String, Object> rowData = row(2, "李四");
        rowData.put("Major", "计算机");

        page.updateDataRow(modified(values), rowData, new String[] { "Name", "Major" });

        assertEquals("计算机", rowData.get("Major"), "改动项里没有这一列，就不该动它");
    }

    @Test
    void updateDataRow_数据行为null时什么也不做() {
        //:: 配合 findRowById 找不到行的情形：调用方不必自己判 null
        Map<String, Object> values = new LinkedHashMap<>();
        values.put("Name", "x");
        page.updateDataRow(modified(values), null, new String[] { "Name" });
    }

    @Test
    void updateDataRow_改动项里values不是Map时什么也不做() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("values", "这不是一个 Map");
        Map<String, Object> rowData = row(2, "李四");

        page.updateDataRow(m, rowData, new String[] { "Name" });

        assertEquals("李四", rowData.get("Name"));
    }

    @Test
    void updateDataRow_columns为空数组时什么也不写() {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put("Name", "李四改了");
        Map<String, Object> rowData = row(2, "李四");

        page.updateDataRow(modified(values), rowData, new String[0]);

        assertEquals("李四", rowData.get("Name"));
    }

    @Test
    void updateDataRow_四参重载先按行标识找行再写() {
        List<Map<String, Object>> rows = source();
        Map<String, Object> values = new LinkedHashMap<>();
        values.put("Name", "李四改了");

        page.updateDataRow(modified(values), "2", rows, new String[] { "Name" });

        assertEquals("李四改了", rows.get(1).get("Name"));
        assertEquals("张三", rows.get(0).get("Name"), "只该动匹配的那一行");
    }

    @Test
    void updateDataRow_四参重载_行标识对不上时什么也不做() {
        List<Map<String, Object>> rows = source();
        Map<String, Object> values = new LinkedHashMap<>();
        values.put("Name", "改了");

        page.updateDataRow(modified(values), "99", rows, new String[] { "Name" });

        assertEquals("张三", rows.get(0).get("Name"));
        assertEquals("李四", rows.get(1).get("Name"));
    }

    // ---------- htmlEncode ----------

    @Test
    void htmlEncode_转义HTML元字符() {
        assertEquals("&lt;script&gt;", page.htmlEncode("<script>"));
        assertEquals("a&amp;b", page.htmlEncode("a&b"));
    }

    @Test
    void htmlEncode_null归一成空串_不抛NPE() {
        //:: 底层 HtmlUtils.htmlEscape(null) 会抛 NPE，基类兜住它：拼字符串的调用方最多得到空串
        assertEquals("", page.htmlEncode(null));
    }
}
