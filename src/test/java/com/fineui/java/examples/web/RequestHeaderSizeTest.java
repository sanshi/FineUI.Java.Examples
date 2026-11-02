package com.fineui.java.examples.web;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** 验证多个本机项目共用 Cookie 时首页仍能刷新，同时保留请求头大小限制。 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RequestHeaderSizeTest {

    @LocalServerPort
    private int port;

    private final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    @Test
    void allowsHomePageRefreshWithSharedLocalhostCookies() throws Exception {
        // localhost 的 Cookie 不按端口隔离：多个 .NET 应用的防伪 Cookie 会一起发给 Java 示例。
        // 使用真实 HTTP 连接发送 12KB Cookie，确保请求经过 Tomcat 的请求头解析；MockMvc 无法覆盖此处。
        String cookie = "local-development=" + "x".repeat(12 * 1024);

        for (int attempt = 0; attempt < 2; attempt++) {
            HttpResponse<String> response = requestHomePage(cookie);

            assertEquals(200, response.statusCode(), "携带本机共享 Cookie 打开和刷新首页均应成功");
            assertTrue(response.body().contains("FineUIPageConfig"), "响应应包含实际的 FineUI 首页配置");
        }
    }

    @Test
    void rejectsRequestHeadersAboveTheConfiguredLimit() throws Exception {
        HttpResponse<String> response = requestHomePage("oversized=" + "x".repeat(40 * 1024));

        assertEquals(400, response.statusCode(), "超过 32KB 上限的请求头仍应由 Tomcat 拒绝");
    }

    private HttpResponse<String> requestHomePage(String cookie) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:" + port + "/"))
                .timeout(Duration.ofSeconds(10))
                .header("Cookie", cookie)
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
