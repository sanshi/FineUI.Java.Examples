package com.fineui.java.examples.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 多表头（大量列，列线对齐）演示数据：20 行，字段 Id + khbh/khmc/product/qc/qm
 * + 12 个月 ×（m{月}_fh_0..17 共 18 列 + m{月}_kp_0..16 共 17 列）。
 * 数值取确定性伪随机值（行索引与列质数因子混合，视觉上各不相同、又可稳定复现）。
 */
public final class GroupFieldManyColumnsGridData {

    // 给每列配一个不同的质数因子，避免同一行相邻列数值雷同
    private static final int[] PRIMES = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67};

    private GroupFieldManyColumnsGridData() {
    }

    /** 全部 20 行数据（Id=100..119）。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Id", 100 + i);
            row.put("khbh", "HT0100" + (i % 5 + 1));
            row.put("khmc", "客户" + i);
            row.put("product", "产品" + (i % 8));
            row.put("qc", cell(i, 3));
            row.put("qm", cell(i, 5));
            // 12 个月的「发货 / 开票」数值列，列序号依次累加、轮换质数因子
            int columnIndex = 0;
            for (int month = 1; month <= 12; month++) {
                for (int j = 0; j < 18; j++) {
                    row.put("m" + month + "_fh_" + j, cell(i, PRIMES[columnIndex++ % PRIMES.length]));
                }
                for (int j = 0; j < 17; j++) {
                    row.put("m" + month + "_kp_" + j, cell(i, PRIMES[columnIndex++ % PRIMES.length]));
                }
            }
            list.add(row);
        }
        return list;
    }

    // 确定性伪随机数（1000..9998），行索引与列质数因子混合，视觉上各不相同、又可稳定复现。
    private static int cell(int i, int prime) {
        return 1000 + ((i * prime * 631 + prime * 97) % 8999);
    }
}
