package com.fineui.java.examples.code;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 服务器 IP + MAC 地址查询工具：本机访问（主机名 localhost/127.0.0.1）返回 {@code ["localhost", ""]}；
 * 否则枚举网卡取首个非回环 IPv4 及其 MAC（冒号分隔小写）。
 */
public final class IpMacUtil {

    private IpMacUtil() {
    }

    /** 返回 {@code [IP, MAC]}。 */
    public static String[] serverIpMac() {
        HttpServletRequest request = currentRequest();
        String host = request != null ? request.getServerName() : null;
        if (host == null || host.equals("127.0.0.1") || host.equals("localhost")) {
            return new String[]{"localhost", ""};
        }

        try {
            Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
            while (nics != null && nics.hasMoreElements()) {
                NetworkInterface nic = nics.nextElement();
                if (nic.isLoopback() || !nic.isUp() || nic.isVirtual()) {
                    continue;
                }
                byte[] hw = nic.getHardwareAddress();
                if (hw == null || hw.length == 0) {
                    continue;
                }
                String ipv4 = firstIpv4(nic);
                if (ipv4 != null) {
                    return new String[]{ipv4, formatMac(hw)};
                }
            }
        } catch (Exception e) {
            // 枚举失败：回落 localhost（宁可保守，也不误报）
        }
        return new String[]{"localhost", ""};
    }

    private static HttpServletRequest currentRequest() {
        ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs != null ? attrs.getRequest() : null;
    }

    private static String firstIpv4(NetworkInterface nic) {
        Enumeration<InetAddress> addrs = nic.getInetAddresses();
        while (addrs.hasMoreElements()) {
            InetAddress addr = addrs.nextElement();
            if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                return addr.getHostAddress().toLowerCase();
            }
        }
        return null;
    }

    /** MAC 字节 → 冒号分隔小写十六进制（如 {@code 00:1e:67:3e:dd:a2}）。 */
    private static String formatMac(byte[] hw) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hw.length; i++) {
            if (i > 0) {
                sb.append(':');
            }
            sb.append(String.format("%02x", hw[i] & 0xFF));
        }
        return sb.toString();
    }
}
