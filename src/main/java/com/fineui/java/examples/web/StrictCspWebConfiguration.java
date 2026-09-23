package com.fineui.java.examples.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 为不经过 FineUI 页面控制器的最终错误响应补充脚本策略。 */
@Configuration(proxyBeanMethods = false)
public class StrictCspWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ErrorResponseCspInterceptor());
    }

    private static final class ErrorResponseCspInterceptor implements HandlerInterceptor {

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response,
                               Object handler, ModelAndView modelAndView) {
            applyErrorPolicy(response);
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                    Object handler, Exception exception) {
            // 未注册的 FineUI 路由会以异常结束，不会进入 postHandle，因此在请求完成阶段再兜底一次。
            applyErrorPolicy(response);
        }

        private void applyErrorPolicy(HttpServletResponse response) {
            if (response.getStatus() < 400 || response.containsHeader("Content-Security-Policy")) {
                return;
            }

            // 错误页不运行脚本，使用 none 避免给浏览器留下没有必要的执行入口。
            response.setHeader("Content-Security-Policy", "script-src 'none';");
        }
    }
}
