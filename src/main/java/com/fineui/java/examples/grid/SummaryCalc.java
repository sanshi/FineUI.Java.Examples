package com.fineui.java.examples.grid;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 合计行数据计算工具：对若干行按 Fee/ExtraFee 求和，格式化为两位小数（F2 风格）。 */
public final class SummaryCalc {

    private SummaryCalc() {
    }

    /** {Fee: 学费合计, ExtraFee: 学杂费合计}（两位小数字符串）。 */
    public static Map<String, Object> feeExtraFee(List<Map<String, Object>> rows) {
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("Fee", fmt(sum(rows, "Fee")));
        summary.put("ExtraFee", fmt(sum(rows, "ExtraFee")));
        return summary;
    }

    /** 带标题列（Major）的合计行：{Major: title, Fee, ExtraFee}。 */
    public static Map<String, Object> feeExtraFeeTitled(List<Map<String, Object>> rows, String title) {
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("Major", title);
        summary.put("Fee", fmt(sum(rows, "Fee")));
        summary.put("ExtraFee", fmt(sum(rows, "ExtraFee")));
        return summary;
    }

    private static double sum(List<Map<String, Object>> rows, String field) {
        double total = 0.0;
        for (Map<String, Object> row : rows) {
            Object v = row.get(field);
            if (v instanceof Number n) {
                total += n.doubleValue();
            }
        }
        return total;
    }

    private static String fmt(double value) {
        return String.format(java.util.Locale.US, "%.2f", value);
    }
}
