package com.fineui.java.examples.multilang;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.MultilangPageBase;
import org.springframework.context.MessageSource;

import java.util.List;

/**
 * 多语言树控件（复选框）（路由 {@code multi-lang/tree-check-box}）：树标题与节点文本走
 * 多语言资源（切换语言随之变化）；点击按钮把选中的复选框节点按多语言格式输出到标签。
 */
@FineUIPage("multi-lang/tree-check-box")
public class TreeCheckBox extends MultilangPageBase {

    Tree Tree1;
    Label labResult;

    public TreeCheckBox(MessageSource messageSource) {
        super(messageSource);
    }

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnGetCheckedValues_Click(Object sender, EventArgs e) {
        List<TreeNode> nodes = Tree1.getCheckedNodes();
        if (!nodes.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(_R("multilang.tree.checkedPrefix"));
            sb.append("<ul>");
            for (TreeNode node : nodes) {
                sb.append(_R("multilang.tree.liFormat", node.getId(), node.getText()));
            }
            sb.append("</ul>");
            labResult.setText(sb.toString());
        } else {
            labResult.setText(_R("multilang.tree.noChecked"));
        }
    }
}
