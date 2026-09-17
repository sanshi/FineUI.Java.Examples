package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIControl;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.Student;
import com.fineui.java.examples.datamodel.model.StudentStore;
import org.springframework.web.util.HtmlUtils;

/**
 * 展示型字段的值跨回发保持（路由 {@code data-model/display-field-value}）。
 *
 * <p>回发时表单绑定根是框架新建的<b>空壳</b>，只承载表单里 {@code for} 声明过、且用户能改的那些值。
 * 于是同一个 {@code for}，两类控件的表现不同：
 * <ul>
 *   <li><b>提交型</b>（TextBox 等，用户能改）：值随回发带回，服务端读到的就是用户当前输入的值；</li>
 *   <li><b>展示型</b>（Label / HyperLink / Image / LinkButton，内容由服务端驱动、用户改不了）：
 *       用户改不了它，也就没有"当前输入"可回传；框架改为在首屏把它的初值记为服务端状态带回，
 *       所以事件处理器里读到的仍是首屏那个值，与浏览器上显示的一致。</li>
 * </ul>
 *
 * <p>本页把两者摆在一起，再把绑定根本身的值也打印出来，一次看清三者的区别。
 */
@FineUIPage("data-model/display-field-value")
public class DisplayFieldValue extends PageBase {

    @BindProperty
    private Student student;

    public Student getStudent() {
        return student;
    }

    // 控件 id 由 for 表达式派生（点 → 下划线），与 Java 字段名不同，故用 @FineUIControl 指定。
    @FineUIControl("student_group")
    Label lblGroup;

    @FineUIControl("student_name")
    TextBox tbxName;

    /** 首屏加载实体供 for 回显（回发不执行）。 */
    public void Page_Get(Object sender, EventArgs e) {
        student = StudentStore.find(101);
    }

    public void btnRead_Click(Object sender, EventArgs e) {
        // 本页只演示读值、不提交表单，故清掉自动绑定产生的校验错误
        getModelState().clear();

        // ⚠️ 三个值都来自客户端（随回发带回，用户能改），拼进可信 HTML 前必须逐个 HTML 编码——
        // RawHtml 挡的是"用户输入伪造出可信 HTML 实例"，挡不住"我们自己把用户输入拼进去"。
        showNotifyRaw(new RawHtml(
                "服务端此刻读到的值：<br/>"
                        + "① 展示型 Label（分组）：<b>%s</b><br/>"
                        + "② 提交型 TextBox（姓名）：<b>%s</b><br/>"
                        + "③ 绑定根 student.getGroup()：<b>%s</b>",
                HtmlUtils.htmlEscape(lblGroup.getValue()),
                HtmlUtils.htmlEscape(tbxName.getValue()),
                student.getGroup()), MessageBoxIcon.Information);
        // ③ 是 0（空壳的 int 默认值）——回发时绑定根只承载表单里回传得来的值，分组不在其中。
        // ① 却是首屏那个真实分组：Label 的初值在首屏被记为服务端状态，随 __FSTATE 往返保住了。
        // 换句话说，读展示型字段要读控件（①），不要读绑定根（③）。
    }

    public void btnChangeGroup_Click(Object sender, EventArgs e) {
        getModelState().clear();

        // 服务端改展示型字段：改动经本次响应下发客户端，并被客户端累积、随下次回发带回。
        // 所以再点「读取服务端当前值」，读到的是这个新值而不是首屏值。
        lblGroup.setValue("9");
        showNotify("已把分组改为 9。再点「读取服务端当前值」，服务端读到的就是 9。", MessageBoxIcon.Success);
    }
}
