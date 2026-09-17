package com.fineui.java.examples.mobile;

import com.fineui.java.core.FineUIPage;

/**
 * 移动示例「手机边框预览器」壳页（路由 {@code mobile/index}）。
 *
 * <p>用途：桌面浏览器里用一个固定 320×568 的手机边框内嵌 IFrame 预览某个移动示例页。页面本身无
 * FineUI 控件，全部逻辑在模板的客户端脚本里完成：
 * <ul>
 *   <li>读地址栏 {@code ?file=} 查询参数（服务端不解析查询串，故在浏览器端读）。</li>
 *   <li>不带 {@code file} → 跳转到移动首页 {@code mobile/main}。</li>
 *   <li>带 {@code file} → 原样（保留大小写）作为内嵌 IFrame 的 {@code src}。</li>
 * </ul>
 *
 * <p>另有一个小重定向控制器 {@link MobileRedirectController} 把 {@code /mobile}、{@code /mobile/}
 * （带 {@code ?file=}）转到本页，使预置菜单里的 {@code /mobile/?file=...} 链接可用。
 */
@FineUIPage("mobile/index")
public class Index {
}
