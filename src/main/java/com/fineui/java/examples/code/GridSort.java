package com.fineui.java.examples.code;

import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** 表格示例共用的服务端排序 helper：按字段就地排序行（{@code DESC} 降序、否则升序，空字段不排序、空值排最后）。 */
public final class GridSort {

    // 中文按拼音排序（与浏览器 localeCompare('zh-CN') 一致），使姓名等字符串列排序符合中文习惯。
    private static final Collator ZH = Collator.getInstance(Locale.CHINA);

    private GridSort() {
    }

    /** 就地排序对象/Map 列表：{@code field} 为空则不排序（Map 按键取、对象按 getter 反射取）。 */
    public static <T> void sortInPlace(List<T> rows, String field, String direction) {
        if (field == null || field.isEmpty()) {
            return;
        }
        boolean desc = "DESC".equalsIgnoreCase(direction);
        rows.sort((a, b) -> {
            int c = compare(fieldValue(a, field), fieldValue(b, field));
            return desc ? -c : c;
        });
    }

    /**
     * 就地多列排序：{@code sortFields} 为 {@code [字段,方向,字段,方向...]}（首个字段为主序，依次为次序）；
     * 为空则不排序。
     */
    public static <T> void sortInPlaceMulti(List<T> rows, String[] sortFields) {
        if (sortFields == null || sortFields.length < 2) {
            return;
        }
        rows.sort((a, b) -> {
            for (int i = 0; i + 1 < sortFields.length; i += 2) {
                String field = sortFields[i];
                if (field == null || field.isEmpty()) {
                    continue;
                }
                boolean desc = "DESC".equalsIgnoreCase(sortFields[i + 1]);
                int c = compare(fieldValue(a, field), fieldValue(b, field));
                if (c != 0) {
                    return desc ? -c : c;
                }
            }
            return 0;
        });
    }

    /** 从行取字段值：Map 按键取；对象按 JavaBean getter 反射取（首字母大小写不敏感）。 */
    public static Object fieldValue(Object bean, String field) {
        if (bean == null || field == null || field.isEmpty()) {
            return null;
        }
        if (bean instanceof Map<?, ?> map) {
            return map.get(field);
        }
        String cap = Character.toUpperCase(field.charAt(0)) + field.substring(1);
        for (String name : new String[]{"get" + cap, "is" + cap, field}) {
            try {
                return bean.getClass().getMethod(name).invoke(bean);
            } catch (ReflectiveOperationException ignored) {
                // 尝试下一种取值方式
            }
        }
        return null;
    }

    /**
     * 生成多列排序提示文案（如「排序字段：入学年份（降序），性别（升序）」）：{@code sortFields} 为
     * {@code [字段,方向,...]}，字段标题取表格中 {@code sortField} 匹配列的列头文本。
     */
    public static String buildSortTip(Grid grid, String[] sortFields) {
        List<String> tips = new ArrayList<>();
        if (sortFields != null) {
            for (int i = 0; i + 1 < sortFields.length; i += 2) {
                String field = sortFields[i];
                String direction = sortFields[i + 1];
                String title = "";
                for (GridColumn col : grid.getColumns()) {
                    if (field != null && field.equals(col.getSortField())) {
                        title = col.getHeaderText();
                        break;
                    }
                }
                tips.add(title + "（" + ("ASC".equalsIgnoreCase(direction) ? "升序" : "降序") + "）");
            }
        }
        return "排序字段：" + String.join("，", tips);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static int compare(Object x, Object y) {
        if (x == null && y == null) {
            return 0;
        }
        if (x == null) {
            return 1;   // 空值排最后
        }
        if (y == null) {
            return -1;
        }
        if (x instanceof String sx && y instanceof String sy) {
            return ZH.compare(sx, sy);   // 中文拼音序
        }
        if (x instanceof Comparable && x.getClass().isInstance(y)) {
            return ((Comparable) x).compareTo(y);
        }
        return ZH.compare(String.valueOf(x), String.valueOf(y));
    }
}
