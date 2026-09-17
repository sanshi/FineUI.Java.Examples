package com.fineui.java.examples.traditional;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 传统 Spring MVC + Thymeleaf 登录示例——用来和 FineUI.Java 的 {@code basic/login}
 * （{@link com.fineui.java.examples.basic.Login}）做「同一功能、两种写法」的直接对照。
 *
 * <p>对应开发指南《从传统 Spring MVC 到 FineUI.Java 开发模式》所述的「四处割裂」：
 * <ol>
 *   <li>首屏渲染：GET 方法往 {@link Model} 塞数据，交模板渲染（下方 {@link #loginPage}）；</li>
 *   <li>参数收集：模板里手写 JavaScript 从 DOM 抠值、拼请求、发 AJAX（见 templates/traditional/login.html）；</li>
 *   <li>回发处理：另写一个 POST 方法，用 {@code @RequestBody} 把参数逐个接回来（下方 {@link #doLogin}）；</li>
 *   <li>两条路各管各的：GET 走 Model、POST 走 @RequestBody，是两个方法、两套传参机制——
 *       都在同一个 Controller 里，但彼此不共享任何界面状态。</li>
 * </ol>
 *
 * <p>两个方法都在本 Controller 内，但这恰恰是对照点：它们只是「碰巧写在一起」的两段独立代码，
 * 中间靠手写的参数名（{@code userName}/{@code password}）与 DOM id 字符串连接，全靠人肉保证一致。
 *
 * <p>运行：启动示例站后打开
 * <ul>
 *   <li>传统写法：{@code http://localhost:8080/traditional/login}</li>
 *   <li>FineUI.Java：{@code http://localhost:8080/basic/login}</li>
 * </ul>
 * 用户名/密码均为 admin/admin。
 */
@Controller
public class TraditionalLoginController {

    /** 一、首屏：写一个 Controller 方法，把要显示的数据塞进 Model，再交给模板。 */
    @GetMapping("/traditional/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "登录表单（传统 Spring MVC）");
        return "traditional/login";
    }

    /**
     * 三、回发：另写一个方法，用 {@code @RequestBody Map} 把前端 JavaScript 拼进来的参数接回来。
     * map 的键名（userName/password）必须和前端手写的 JSON 键一字不差，写错了编译不报错、运行时静默拿到 null。
     */
    @PostMapping("/traditional/login")
    @ResponseBody
    public Map<String, Object> doLogin(@RequestBody Map<String, String> form) {
        boolean success = "admin".equals(form.get("userName"))
                && "admin".equals(form.get("password"));

        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        return result;
    }
}
