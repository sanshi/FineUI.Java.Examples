package com.fineui.java.examples.form;

import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.ControlBase;
import com.fineui.java.core.controls.RadioButton;
import com.fineui.java.core.controls.RealTextField;
import com.fineui.java.core.controls.SelectionListField;
import org.springframework.web.util.HtmlUtils;

/**
 * 表单提交后的字段值汇总（可信 HTML）：把若干列表控件的选中值拼成 {@code 表单字段值：<ul><li>id: 值</li>…</ul>}，
 * 供各示例页的「提交表单」按钮用 {@code showNotify(new RawHtml(...))} 展示。
 * CheckBoxList（多选）与 RadioButtonList（单选）
 * 均为 {@code SelectionListField} 子类，故都可传入（各自的选中值经 {@code getSelectedValueList} 读出）。
 */
public final class FormSummary {

    private FormSummary() {
    }

    public static String of(SelectionListField... lists) {
        StringBuilder sb = new StringBuilder("表单字段值：<ul class=\"result\">");
        for (SelectionListField list : lists) {
            String text = String.join(", ", list.getSelectedValueList());
            if (!text.isEmpty()) {
                append(sb, list.getId(), text);
            }
        }
        sb.append("</ul>");
        return sb.toString();
    }

    /** 输入框类字段（NumberBox/TextBox/TextArea 等）的值汇总，同款 {@code <ul class="result">} 格式、跳过空值字段。 */
    public static String ofFields(RealTextField... fields) {
        StringBuilder sb = new StringBuilder("表单字段值：<ul class=\"result\">");
        for (RealTextField f : fields) {
            String v = f.getValue();
            if (v != null && !v.isEmpty()) {
                append(sb, f.getId(), v);
            }
        }
        sb.append("</ul>");
        return sb.toString();
    }

    /** 递归汇总一个或多个表单（或任意容器）内全部字段的值，同款 {@code <ul class="result">} 格式。 */
    public static String ofForm(ControlBase... roots) {
        StringBuilder sb = new StringBuilder("表单字段值：<ul class=\"result\">");
        for (ControlBase root : roots) {
            appendFields(root, sb);
        }
        sb.append("</ul>");
        return sb.toString();
    }

    private static void appendFields(ControlBase container, StringBuilder sb) {
        for (ControlBase child : container.children()) {
            if (child instanceof SelectionListField list) {
                String v = String.join(", ", list.getSelectedValueList());
                if (!v.isEmpty()) {
                    append(sb, child.getId(), v);
                }
            } else if (child instanceof CheckBox cb) {
                append(sb, child.getId(), String.valueOf(cb.isChecked()));
            } else if (child instanceof RadioButton rb) {
                append(sb, child.getId(), String.valueOf(rb.isChecked()));
            } else if (child instanceof RealTextField f) {
                String v = f.getValue();
                if (v != null && !v.isEmpty()) {
                    append(sb, child.getId(), v);
                }
            }
            appendFields(child, sb);   // 递归下探容器（表单行 / 面板）内嵌字段
        }
    }

    private static void append(StringBuilder sb, String id, String value) {
        // 列表标签由示例固定提供，字段名和值只作为文字，避免输入中的标签变成页面元素。
        sb.append("<li>")
                .append(HtmlUtils.htmlEscape(id, "UTF-8"))
                .append(": ")
                .append(HtmlUtils.htmlEscape(value, "UTF-8"))
                .append("</li>");
    }
}
