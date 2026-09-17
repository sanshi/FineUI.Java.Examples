package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.PageBase;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 跨 IFrame 传值（服务端版，路由 {@code iframe/pass-value}）：父页「从列表中选择」服务端
 * {@code Button1_Click} 用 {@code saveStateControlIds("tbxProvince")} 记住回填目标后打开窗体；
 * 子页 {@code RadioButtonList} 选择经服务端 {@code OnSelectedIndexChanged} -> {@code writeBackValue}
 * 回写父页 {@code tbxProvince} 并隐藏窗体（纯服务端回发回写，区别于 {@link PassValueScript} 的纯脚本回写）。
 */
@FineUIPage("iframe/pass-value")
public class PassValue extends PageBase {

    TextBox tbxProvince;
    Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button1_Click(Object sender, EventArgs e) {
        String openUrl = "/iframe/pass-value/iframe-window?selected="
                + URLEncoder.encode(tbxProvince.getValue(), StandardCharsets.UTF_8);
        Window1.saveStateControlIds("tbxProvince");
        Window1.show(openUrl, "编辑");
    }
}
