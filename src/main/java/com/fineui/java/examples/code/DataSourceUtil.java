package com.fineui.java.examples.code;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 示例数据源工具：省市县联动数据（从 classpath 的 {@code data/*.json} 一次性加载）。
 *
 * <p>{@link #SHENG} 省份名数组；{@link #SHI} 省份→地区市数组；{@link #XIAN} 地区市→县区市数组。
 */
public final class DataSourceUtil {

    private DataSourceUtil() {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /** 全部省份名（有序）。 */
    public static final List<String> SHENG = load("data/sheng.json", new TypeReference<List<String>>() {
    });

    /** 省份名 → 该省地区市名数组。 */
    public static final Map<String, List<String>> SHI = load("data/shi.json", new TypeReference<Map<String, List<String>>>() {
    });

    /** 地区市名 → 该市县区市名数组。 */
    public static final Map<String, List<String>> XIAN = load("data/xian.json", new TypeReference<Map<String, List<String>>>() {
    });

    private static <T> T load(String path, TypeReference<T> type) {
        try (InputStream in = DataSourceUtil.class.getClassLoader().getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("资源不存在: " + path);
            }
            return MAPPER.readValue(in, type);
        } catch (Exception e) {
            throw new IllegalStateException("加载省市县数据失败: " + path, e);
        }
    }

    // —— 模拟国家列表（移动端 DataList 示例用；字段 Id/Image/Name/Desc/Group）——

    private static Map<String, Object> country(String id, String image, String name, String desc, String group) {
        Map<String, Object> row = new java.util.LinkedHashMap<>();
        row.put("Id", id);
        row.put("Image", image);
        row.put("Name", name);
        row.put("Desc", desc);
        row.put("Group", group);
        return row;
    }

    /** 模拟国家列表（6 项，含亚洲/欧洲/美洲分组）。 */
    public static List<Map<String, Object>> getCountryTable() {
        List<Map<String, Object>> list = new java.util.ArrayList<>();
        list.add(country("cn", "cn", "中国", "中国位于东亚，是以华夏文明为主体、中华文化为基础，以汉族为主要民族的统一多民族国家，通用汉语。", "亚洲"));
        list.add(country("fr", "fr", "法国", "法兰西共和国，简称法国，是一个本土位于西欧的总统共和制国家，海外领土包括南美洲和南太平洋的一些地区。", "欧洲"));
        list.add(country("us", "us", "美国", "美利坚合众国，简称美国，是由华盛顿哥伦比亚特区、50个州和关岛等众多海外领土组成的联邦共和立宪制国家。", "美洲"));
        list.add(country("england", "england", "英国", "英国，全称大不列颠及北爱尔兰联合王国，本土位于欧洲大陆西北面的不列颠群岛，被北海、英吉利海峡、凯尔特海、爱尔兰海和大西洋包围。", "欧洲"));
        list.add(country("it", "it", "意大利", "意大利，全称意大利共和国，是一个欧洲国家，主要由南欧的亚平宁半岛及两个位于地中海中的岛屿西西里岛与萨丁岛所组成。", "欧洲"));
        list.add(country("ca", "ca", "加拿大", "加拿大，为北美洲最北的国家，西抵太平洋，东迄大西洋，北至北冰洋，南方与美国本土接壤。领土面积为998万平方千米，位居世界第二。", "美洲"));
        return list;
    }

    /** 模拟国家列表 2（4 项，用于「重新数据绑定」示例，Id 带 2 后缀、Image 复用同一批旗帜）。 */
    public static List<Map<String, Object>> getCountryTable2() {
        List<Map<String, Object>> list = new java.util.ArrayList<>();
        list.add(country("us2", "us", "美国 - 2", "美利坚合众国，简称美国，是由华盛顿哥伦比亚特区、50个州和关岛等众多海外领土组成的联邦共和立宪制国家。", "美洲"));
        list.add(country("it2", "it", "意大利 - 2", "意大利，全称意大利共和国，是一个欧洲国家，主要由南欧的亚平宁半岛及两个位于地中海中的岛屿西西里岛与萨丁岛所组成。", "欧洲"));
        list.add(country("fr2", "fr", "法国 - 2", "法兰西共和国，简称法国，是一个本土位于西欧的总统共和制国家，海外领土包括南美洲和南太平洋的一些地区。", "欧洲"));
        list.add(country("england2", "england", "英国 - 2", "英国，全称大不列颠及北爱尔兰联合王国，本土位于欧洲大陆西北面的不列颠群岛，被北海、英吉利海峡、凯尔特海、爱尔兰海和大西洋包围。", "欧洲"));
        return list;
    }
}
