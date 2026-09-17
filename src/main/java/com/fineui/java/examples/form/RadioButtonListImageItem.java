package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RadioItemEventArgs;
import com.fineui.java.core.RawHtml;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 单选按钮列表 - 图标项（可信 HTML）演示页（路由 {@code form/radio-button-list-image-item}）：列表项文本为「图标 + 名称」
 * 的 HTML 片段，以列表项 {@code textRaw} 信任标记原样输出、不转义。
 *
 * <p>三个列表演示同一效果的三种等价数据供给方式：① 数据绑定 + {@code DataTextRaw}（整列文本按可信 HTML 输出）；
 * ② {@code OnItemDataBound}（逐项事件回调里 {@code setTextRawHtml}）；③ 手工构建 {@code addRadioItemRaw}。
 * 三者都只信任图标模板骨架与 URL，名称为安全字面量，杜绝 XSS。
 */
@FineUIPage("form/radio-button-list-image-item")
public class RadioButtonListImageItem extends PageBase {

    com.fineui.java.core.controls.RadioButtonList RadioButtonList1;
    com.fineui.java.core.controls.RadioButtonList RadioButtonList2;
    com.fineui.java.core.controls.RadioButtonList RadioButtonList3;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadDataBind();
            loadItemDataBound();
            loadManualItems();
        }
    }

    // 示例用国家数据（Code 为国家代码，对应 /res/icon/flag_{code}.png；Name 为显示名称）
    private List<Country> getCountries() {
        return new ArrayList<>(List.of(
                new Country("cn", "中国"),
                new Country("us", "美国"),
                new Country("ru", "俄罗斯"),
                new Country("de", "德国")
        ));
    }

    // ① DataBind + DataTextRaw（自动绑定：整列文本按可信 HTML 输出）
    private void loadDataBind() {
        List<Country> list = new ArrayList<>();
        for (Country c : getCountries()) {
            // 后端预拼好每一项的可信 HTML（图标 + 名称），放到 Display 字段；名称为安全字面量
            c.setDisplay("<img src=\"/res/icon/flag_" + c.getCode() + ".png\" style=\"vertical-align:middle;\" />&nbsp;" + c.getName());
            list.add(c);
        }

        RadioButtonList1.setDataValueField("code");
        RadioButtonList1.setDataTextField("display");
        RadioButtonList1.setDataTextRaw(true);      // 整列文本作为可信 HTML 原样输出（不转义）
        RadioButtonList1.setDataSource(list);
        RadioButtonList1.dataBind();

        RadioButtonList1.setSelectedValue("cn");
    }

    // ② OnItemDataBound（逐项事件回调；三种方式安全性等价）
    private void loadItemDataBound() {
        RadioButtonList2.setDataValueField("code");
        RadioButtonList2.setDataTextField("name");
        RadioButtonList2.setDataSource(getCountries());
        RadioButtonList2.dataBind();

        RadioButtonList2.setSelectedValue("us");
    }

    public void RadioButtonList2_ItemDataBound(Object sender, RadioItemEventArgs e) {
        Country c = (Country) e.getDataItem();

        // 只信任图标模板骨架 + URL；数据字段（名称）为安全字面量，杜绝 XSS
        e.getItem().setTextRawHtml(new RawHtml("<img src=\"/res/icon/flag_" + c.getCode() + ".png\" style=\"vertical-align:middle;\" />&nbsp;" + c.getName()));
    }

    // ③ 手工构建 Items（直接 addRadioItemRaw，TextRawHtml）
    private void loadManualItems() {
        for (Country c : getCountries()) {
            // 只信任图标模板骨架 + 图标 URL；名称为安全字面量。列表项 textRaw 信任标记 → 原样输出不转义
            String html = "<img src=\"/res/icon/flag_" + c.getCode() + ".png\" style=\"vertical-align:middle;\" />&nbsp;" + c.getName();
            RadioButtonList3.addRadioItemRaw(c.getCode(), html, true, false);
        }

        RadioButtonList3.setSelectedValue("ru");
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotify("① " + s(RadioButtonList1.getSelectedValue())
                + "　② " + s(RadioButtonList2.getSelectedValue())
                + "　③ " + s(RadioButtonList3.getSelectedValue()));
    }

    private static String s(String v) {
        return v == null ? "" : v;
    }

    /** 演示数据类：Code 为值字段、Name 为文本字段、Display 为预拼好的可信 HTML 文本字段。 */
    public static class Country {
        private final String code;
        private final String name;
        private String display;

        public Country(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getDisplay() {
            return display;
        }

        public void setDisplay(String display) {
            this.display = display;
        }
    }
}
