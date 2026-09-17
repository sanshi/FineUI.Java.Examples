package com.fineui.java.examples.config;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.List;

/**
 * 内置加载动画展示页（路由 {@code config/loading}）：30 种 GIF 加载动画，纯静态展示、无交互脚本。
 */
@FineUIPage("config/loading")
public class Loading extends PageBase {

    /** 加载动画序号 1..30 → 内置图片文件夹名。 */
    private static final String[] IMAGE_FOLDERS = {
            "3", "1", "2", "67", "6", "53", "74", "13", "21", "25", "61", "62",
            "109", "72", "75", "76", "92", "93", "104", "8", "31", "43", "54", "68",
            "99", "102", "36", "40", "52", "66"};

    /** 30 个加载 GIF 的静态资源 URL（序号 1..30 顺序）。 */
    public List<String> getImageUrls() {
        List<String> urls = new ArrayList<>(IMAGE_FOLDERS.length);
        for (String folder : IMAGE_FOLDERS) {
            urls.add("/F/images/loading/_" + folder + "/ffffff_444444.gif");
        }
        return urls;
    }
}
