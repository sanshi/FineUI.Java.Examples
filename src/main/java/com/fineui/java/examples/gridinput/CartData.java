package com.fineui.java.examples.gridinput;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 购物车演示数据（Id/Code/Name/Desc/Price/Number）。 */
final class CartData {

    private CartData() {
    }

    static List<Map<String, Object>> rows() {
        List<Map<String, Object>> list = new ArrayList<>();
        add(list, 101, "100022", "商品一", 35.5, 1);
        add(list, 102, "100023", "商品二", 18.99, 2);
        add(list, 103, "100024", "商品三", 18.99, 2);
        add(list, 104, "100025", "商品四", 22.00, 1);
        return list;
    }

    private static void add(List<Map<String, Object>> list, int id, String code, String name, double price, int number) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", id);
        row.put("Code", code);
        row.put("Name", name);
        row.put("Desc", "这是" + name + "的介绍，巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉。");
        row.put("Price", price);
        row.put("Number", number);
        list.add(row);
    }
}
