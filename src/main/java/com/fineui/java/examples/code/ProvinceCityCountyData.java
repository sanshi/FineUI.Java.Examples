package com.fineui.java.examples.code;

import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.RadioButtonList;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 六端示例共用同一套固定省市县编码；表单保存编码，名称只用于显示。 */
public final class ProvinceCityCountyData {

    private static final Catalog DATA = load();
    private static final List<ProvinceCityCountyItem> PROVINCES = options(DATA.provinces);

    private ProvinceCityCountyData() {
    }

    /** 全部省份，顺序由数据文件决定。 */
    public static List<ProvinceCityCountyItem> provinces() {
        return PROVINCES;
    }

    /** 省份编码对应的城市；无效编码返回空列表。 */
    public static List<ProvinceCityCountyItem> cities(String provinceCode) {
        return options(DATA.cities.get(provinceCode));
    }

    /** 城市编码对应的县区；无效编码返回空列表。 */
    public static List<ProvinceCityCountyItem> counties(String cityCode) {
        return options(DATA.counties.get(cityCode));
    }

    public static String provinceName(String code) {
        return name(PROVINCES, code);
    }

    /** 显示名称的文本框再次打开选省窗口时查回编码；找不到则不预选。 */
    public static String provinceCode(String name) {
        for (ProvinceCityCountyItem province : PROVINCES) {
            if (province.name().equals(name)) {
                return province.code();
            }
        }
        return "";
    }

    public static String cityName(String code) {
        return code == null || code.length() < 3 ? "" : name(cities(code.substring(0, 3)), code);
    }

    public static String countyName(String code) {
        return code == null || code.length() < 6 ? "" : name(counties(code.substring(0, 6)), code);
    }

    /** 表单控件读取编码和值；如北京显示“北京”，回发值为“001”。 */
    public static void bind(DropDownList list, List<ProvinceCityCountyItem> options) {
        list.setDataTextField("name");
        list.setDataValueField("code");
        list.setDataSource(options);
        list.dataBind();
    }

    /** 单选列表与下拉列表使用同一组编码和值。 */
    public static void bind(RadioButtonList list, List<ProvinceCityCountyItem> options) {
        list.setDataTextField("name");
        list.setDataValueField("code");
        list.setDataSource(options);
        list.dataBind();
    }

    /** 客户端只读取省份和城市；县区列表留在服务端按需绑定。 */
    public static Map<String, Object> provinceCityData() {
        return Map.of("provinces", DATA.provinces, "cities", DATA.cities);
    }

    private static List<ProvinceCityCountyItem> options(List<List<String>> pairs) {
        if (pairs == null) {
            return List.of();
        }

        List<ProvinceCityCountyItem> result = new ArrayList<>(pairs.size());
        for (List<String> pair : pairs) {
            result.add(new ProvinceCityCountyItem(pair.get(0), pair.get(1)));
        }
        return List.copyOf(result);
    }

    private static String name(List<ProvinceCityCountyItem> options, String code) {
        for (ProvinceCityCountyItem option : options) {
            if (option.code().equals(code)) {
                return option.name();
            }
        }
        return "";
    }

    private static Catalog load() {
        try (InputStream stream = ProvinceCityCountyData.class.getClassLoader().getResourceAsStream("data/province-city-county.json")) {
            if (stream == null) {
                throw new IllegalStateException("省市县数据文件不存在");
            }
            return new ObjectMapper().readValue(stream, Catalog.class);
        } catch (Exception exception) {
            throw new IllegalStateException("加载省市县数据失败", exception);
        }
    }

    /** 数据文件的三层结构，编码都在 JSON 中显式给出。 */
    public static final class Catalog {
        public List<List<String>> provinces;
        public Map<String, List<List<String>>> cities;
        public Map<String, List<List<String>>> counties;
    }

    /** 下拉项保存的编码与显示名称。 */
    public record ProvinceCityCountyItem(String code, String name) {
        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }
}
