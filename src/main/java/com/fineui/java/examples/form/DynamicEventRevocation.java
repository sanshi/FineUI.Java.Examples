package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Panel;
import com.fineui.java.examples.code.PageBase;

/** 动态按钮只在首次加载时绑定事件，需要禁止时每轮明确撤回。 */
@FineUIPage("form/dynamic-event-revocation")
public class DynamicEventRevocation extends PageBase {
    private static final String COUNT_SESSION_KEY = "DynamicEventRevocation.Count";

    CheckBox cbxRevoke;
    Panel Panel1;
    Label labCount;
    Label labResult;
    Button btnDynamic;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            session().setAttribute(COUNT_SESSION_KEY, 0);
            loadData();
        } else if (cbxRevoke.isChecked() && btnDynamic != null) {
            // 即使本轮没有重新 setOnClick，也可以明确撤回 click，阻止浏览器保留的旧事件触发后台方法。
            btnDynamic.setOnClick(null);
        }
    }

    private void loadData() {
        btnDynamic = new Button();
        btnDynamic.setId("btnDynamic");
        btnDynamic.setText("触发动态按钮事件");
        btnDynamic.setOnClick("btnDynamic_Click");
        Panel1.addChild(btnDynamic, "items");
    }

    private int readCount() {
        // 计数从服务器会话读取，便于在拒绝回发后独立核对，而不是依赖浏览器标签上的文字。
        Object count = session().getAttribute(COUNT_SESSION_KEY);
        return count instanceof Integer ? (Integer) count : 0;
    }

    public void btnDynamic_Click(Object sender, EventArgs e) {
        int count = readCount() + 1;
        session().setAttribute(COUNT_SESSION_KEY, count);
        labCount.setText("服务器处理次数：" + count);
        labResult.setText("动态按钮事件已执行");
    }

    public void btnReadCount_Click(Object sender, EventArgs e) {
        labCount.setText("服务器处理次数：" + readCount());
        labResult.setText("已读取服务器处理次数");
    }
}
