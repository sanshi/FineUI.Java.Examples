package com.fineui.java.examples.config;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.IpMacUtil;
import com.fineui.java.examples.code.PageBase;

/**
 * 服务器 IP+MAC 地址展示页（路由 {@code config/ipmac}）：只读表单展示本机 IP 与 MAC（本地访问显示 localhost）。
 */
@FineUIPage("config/ipmac")
public class IPMAC extends PageBase {

    private String[] cachedIpmac;

    private String[] loadIpmac() {
        if (cachedIpmac == null) {
            cachedIpmac = IpMacUtil.serverIpMac();
        }
        return cachedIpmac;
    }

    /** 服务器 IP（本机访问为 localhost）。 */
    public String getIp() {
        return loadIpmac()[0];
    }

    /** 服务器 MAC（本机访问为空串）。 */
    public String getMac() {
        return loadIpmac()[1];
    }
}
