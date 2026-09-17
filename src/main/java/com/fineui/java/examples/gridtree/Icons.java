package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 自定义节点图标：客户端行绑定函数按文件扩展名设置节点图标（图片类用图标字体、其余用文件类型图标），
 * 并默认展开 basic(50)、res(60)、images(63)、logo(64)。数据服务端绑定。
 */
@FineUIPage("grid-tree/icons")
public class Icons extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.all());
            Grid1.dataBind();
        }
    }
}
