package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.enums.ButtonColor;
import com.fineui.java.examples.code.PageBase;

/**
 * 枚举属性“设回默认”回发演示（路由 {@code other/enum-reset-postback}），两组演示：
 *
 * <p><b>① handler 设回默认（setButtonColor 的累计语义）</b>：{@code color} 的 setter 与 {@code setText} 一致——
 * 「设置即累计」。两个按钮只差“模板是否声明过颜色”，但对同一句 {@code setButtonColor(Default)} 反应相同：
 * <ul>
 *   <li>模板声明过 {@code Primary}（present）→ 设回默认累计出 {@code color:"default"}；回发响应 {@code updates} 带该增量，
 *       客户端视觉复位为默认灰，服务端读回 {@link ButtonColor#Default}（{@code Primary} 不复活）。</li>
 *   <li>模板未声明颜色（absent）→ 同样累计出 {@code color:"default"}；回发响应 {@code updates} <b>也带</b>该增量
 *       （不因“原先缺失”而省略——这正是与 {@code setText} 一致的累计语义），服务端读回 {@link ButtonColor#Default}。</li>
 * </ul>
 *
 * <p><b>② Page_Load 设回默认</b>：{@code btnPageLoadDefault} 模板声明 {@code Primary}，但 {@code Page_Load} 首屏把它
 * 设回 {@link ButtonColor#Default}——首屏就渲染为默认灰（Page_Load 的 {@code color:"default"} 覆盖了模板声明），
 * 且此后任意回发读回仍是 {@code Default}，模板的 {@code Primary} 不复活。
 */
@FineUIPage("other/enum-reset-postback")
public class EnumResetPostback extends PageBase {

    Button btnColorDeclared;    // 模板 Primary；在 handler 里设回默认
    Button btnColorAbsent;      // 模板未声明颜色；在 handler 里设回默认
    Button btnPageLoadDefault;  // 模板 Primary；在 Page_Load 首屏设回默认
    Label lblResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 首屏把“模板声明了 Primary 的按钮”在服务端设回默认：首屏即渲染为 Default（覆盖模板声明），
            // 且此后任意回发都不会复活模板的 Primary（累计的 color:"default" 随 __FSTATE 带回、声明回填跳过已存在的键）。
            btnPageLoadDefault.setButtonColor(ButtonColor.Default);
        }
    }

    public void btnReset_Click(Object sender, EventArgs e) {
        // ① handler 设回默认：同属性 color、同句 setButtonColor(Default)，只差“模板是否声明过颜色”——
        //    但 color 的 setter 是「设置即累计」（对齐 Label text），故两者都累计出 color:"default"、都出现在响应 updates 里。
        btnColorDeclared.setButtonColor(ButtonColor.Default);   // present → 累计 color:"default"（复位、Primary 不复活）
        btnColorAbsent.setButtonColor(ButtonColor.Default);     // absent  → 同样累计 color:"default"（不省略）

        // 读回三者颜色：btnPageLoadDefault 的 Default 来自首屏 Page_Load，本次回发读回仍是 Default（不复活）
        lblResult.setValue("服务端读回颜色 → 声明按钮=" + btnColorDeclared.getButtonColor()
                + "；未声明按钮=" + btnColorAbsent.getButtonColor()
                + "；Page_Load按钮=" + btnPageLoadDefault.getButtonColor());
    }
}
