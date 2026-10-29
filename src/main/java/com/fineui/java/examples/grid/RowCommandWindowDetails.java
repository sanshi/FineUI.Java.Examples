package com.fineui.java.examples.grid;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/** 展示行命令传入的客户标识。 */
@FineUIPage("grid/row-command-window-details")
public class RowCommandWindowDetails extends PageBase {

    /** 当前客户标识来自网址的 slug 参数；未传入时返回空字符串。 */
    public String getCustomerSlug() {
        String slug = getQueryValue("slug");
        return slug == null ? "" : slug;
    }
}
