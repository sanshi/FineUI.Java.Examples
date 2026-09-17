package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * Label 的 Text 属性回发演示（路由 {@code other/label-text-postback}），与 {@code enum-reset-postback} 结构完全对应。
 *
 * <p>Label 的 {@code text}（承载于 {@code value}）是<b>服务端驱动</b>的显示属性（客户端不导出、用户改不了），
 * 写入语义是 {@code set}「设置即累计」：无论键 present 还是 absent，{@code setText(...)} 都记脏并回传。
 * 因此把文本清空（{@code setText(null)}）时：
 * <ul>
 *   <li><b>① handler 清空</b>：声明标签（present）与未声明标签（absent）<b>都</b>记脏并回传 {@code value:null}
 *       —— 与 {@code enum-reset-postback} 的 {@code setButtonColor(Default)} 同构（{@code color} 也走 {@code set} 累计，
 *       present/absent 都产生增量；value 的「明确清空」在账本记 null、getter 归一空串）。</li>
 *   <li><b>② Page_Load 清空</b>：模板声明了文本、Page_Load 首屏清空 → 首屏即为空（覆盖模板声明），回发读回为空串，
 *       模板文本不复活。</li>
 * </ul>
 */
@FineUIPage("other/label-text-postback")
public class LabelTextPostback extends PageBase {

    Label lblDeclared;    // 模板 text="模板文本"；在 handler 里清空
    Label lblAbsent;      // 模板未声明 text；在 handler 里清空
    Label lblPageLoad;    // 模板 text="模板文本"；在 Page_Load 首屏清空
    Label lblResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 首屏把“模板声明了文本的标签”清空：首屏即为空（覆盖模板声明），此后回发不复活模板文本。
            lblPageLoad.setText(null);
        }
    }

    public void btnReset_Click(Object sender, EventArgs e) {
        // setText 走 set（明确清空）：无论键 present 还是 absent，都记脏 value:null
        //（与 color 的 setButtonColor 同构：两者都是「设置即累计」，present/absent 都产生增量）
        lblDeclared.setText(null);   // present（模板文本）→ 记脏 value:null（模板文本不复活）
        lblAbsent.setText(null);     // absent → 【仍】记脏 value:null（与 btnColorAbsent 同样都发增量）

        lblResult.setText("服务端读回文本 → 声明标签=[" + show(lblDeclared.getText())
                + "]；未声明标签=[" + show(lblAbsent.getText())
                + "]；Page_Load标签=[" + show(lblPageLoad.getText()) + "]");
    }

    private static String show(String s) {
        return s.isEmpty() ? "空串" : s;
    }
}
