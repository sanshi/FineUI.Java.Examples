package com.fineui.java.examples.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟树表格演示数据（7 行，地区层级）：字段 Id/Name/Group/TreeLevel（TreeLevel 为缩进层级，0 为根）。
 * 中国(0) → 河南省(1) → 驻马店市(2)/漯河市(2)；安徽省(1) → 合肥市(2)/黄山市(2)。
 */
public final class SimulateTreeData {

    private SimulateTreeData() {
    }

    /** 全部 7 行地区数据。 */
    public static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        add(list, 101, "中国", "1", 0);
        add(list, 102, "河南省", "2", 1);
        add(list, 103, "驻马店市", "3", 2);
        add(list, 104, "漯河市", "3", 2);
        add(list, 105, "安徽省", "2", 1);
        add(list, 106, "合肥市", "3", 2);
        add(list, 107, "黄山市", "3", 2);
        return list;
    }

    private static void add(List<Map<String, Object>> list, int id, String name, String group, int treeLevel) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Name", name);
        row.put("Group", group);
        row.put("TreeLevel", treeLevel);
        list.add(row);
    }
}
