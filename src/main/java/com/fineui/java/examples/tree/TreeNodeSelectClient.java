package com.fineui.java.examples.tree;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 节点选中（客户端事件）（路由 {@code tree/tree-node-select-client}）：多选树，纯客户端演示——
 * {@code nodeselect}/{@code nodedeselect}/{@code selectionchange} 三个监听器把事件追加到页面日志列表。
 *
 * <p>无后台逻辑（不触发回发），页面模型类为空体（与其它示例页一致地继承 {@link PageBase}）。
 */
@FineUIPage("tree/tree-node-select-client")
public class TreeNodeSelectClient extends PageBase {
}
