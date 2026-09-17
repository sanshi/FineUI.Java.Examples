package com.fineui.java.examples.gridfilter;

import java.time.LocalDate;
import java.util.List;

/**
 * 表头过滤示例共用的「单条过滤条件命中判断」工具：按操作符对文本 / 数字 / 日期 / 多选标签做匹配。
 * 供各示例页的 {@code filterDataRowItem} 复用。
 */
public final class FilterMatchers {

    private FilterMatchers() {
    }

    /** 文本匹配：{@code equal}（相等）/{@code contain}（包含）/{@code start}（开始于）/{@code end}（结束于）；
     *  无操作符（{@code null}/空，如默认文本框过滤、服务端初始化过滤）按「包含」处理。 */
    public static boolean text(String operator, Object source, Object filtered) {
        String s = source == null ? "" : source.toString();
        String f = filtered == null ? "" : filtered.toString();
        return switch (operator == null || operator.isEmpty() ? "contain" : operator) {
            case "equal" -> s.equals(f);
            case "contain" -> s.contains(f);
            case "start" -> s.startsWith(f);
            case "end" -> s.endsWith(f);
            default -> false;
        };
    }

    /** 数字匹配：{@code greater}（大于）/{@code less}（小于）/{@code equal}（等于）。 */
    public static boolean number(String operator, Object source, Object filtered) {
        double s = toDouble(source);
        double f = toDouble(filtered);
        return switch (operator == null ? "" : operator) {
            case "greater" -> s > f;
            case "less" -> s < f;
            case "equal" -> s == f;
            default -> false;
        };
    }

    /** 日期匹配：{@code greater}/{@code less}/{@code equal}（按「年月日」比较，忽略时分秒）。 */
    public static boolean date(String operator, Object source, Object filtered) {
        LocalDate s = toDate(source);
        LocalDate f = toDate(filtered);
        if (s == null || f == null) {
            return false;
        }
        return switch (operator == null ? "" : operator) {
            case "greater" -> s.isAfter(f);
            case "less" -> s.isBefore(f);
            case "equal" -> s.isEqual(f);
            default -> false;
        };
    }

    /**
     * 多选标签匹配：源值 == 任一选中项即命中。{@code filtered} 可能是 {@link List}（回发时 JSON 反序列化得到）、
     * {@code Object[]}/{@code String[]}（服务端首屏用数组设初值时的原始对象）、或逗号分隔串——三者都支持。
     */
    public static boolean tags(Object source, Object filtered) {
        String s = source == null ? "" : source.toString();
        if (filtered instanceof List<?> list) {
            for (Object o : list) {
                if (o != null && s.equals(o.toString())) {
                    return true;
                }
            }
            return false;
        }
        if (filtered instanceof Object[] arr) {
            for (Object o : arr) {
                if (o != null && s.equals(o.toString())) {
                    return true;
                }
            }
            return false;
        }
        if (filtered != null) {
            for (String part : filtered.toString().split(",")) {
                if (s.equals(part.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 下拉/下拉框过滤（可自由输入）：有选中项（非空数组/列表）时按「源值等于任一选中项」；否则视为用户自由输入，
     * 按「源值包含所输文本」（{@code text}）。用「数组/列表是否非空」判定而非「值是否为 null」——
     * 客户端未选任何项时回带的是空数组而非 null。
     */
    public static boolean tagsOrInput(Object value, String text, Object source) {
        boolean hasSelection = (value instanceof List<?> list && !list.isEmpty())
                || (value instanceof Object[] arr && arr.length > 0);
        if (hasSelection) {
            return tags(source, value);
        }
        String s = source == null ? "" : source.toString();
        String t = text == null ? "" : text;
        return !t.isEmpty() && s.contains(t);
    }

    private static double toDouble(Object v) {
        if (v instanceof java.lang.Number n) {   // 全限定：本包内有示例类 Number，避免被遮蔽
            return n.doubleValue();
        }
        try {
            return v == null ? Double.NaN : Double.parseDouble(v.toString().trim());
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    private static LocalDate toDate(Object v) {
        if (v instanceof LocalDate d) {
            return d;
        }
        if (v instanceof java.time.LocalDateTime dt) {
            return dt.toLocalDate();
        }
        if (v == null) {
            return null;
        }
        String s = v.toString().trim();
        // 兼容 yyyy/MM/dd、yyyy-MM-dd、及带时分秒的串（取日期部分）
        s = s.replace('/', '-');
        int sp = s.indexOf(' ');
        if (sp > 0) {
            s = s.substring(0, sp);
        }
        int tp = s.indexOf('T');
        if (tp > 0) {
            s = s.substring(0, tp);
        }
        try {
            return LocalDate.parse(s);
        } catch (Exception e) {
            return null;
        }
    }
}
