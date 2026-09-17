package com.fineui.java.examples.gridbigdata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/** GridBigData 的网址数据源。 */
@RestController
public class BigDataDataController {

    @GetMapping("/grid-big-data/big-data-url-data")
    public Object bigDataUrlData(@RequestParam int total,
                                 @RequestParam(required = false) String resultType) {
        // 契约：页面 URL 用 type=simple（未绑定到 resultType，仍返回对象行数组）；
        // 仅 resultType=simple 请求紧凑 {fields,data} 形态。
        return "simple".equalsIgnoreCase(resultType)
                ? BigDataData.simple(total)
                : BigDataData.rows(total);
    }

    @GetMapping("/grid-big-data/big-data-url-paging-database-data")
    public Map<String, Object> bigDataUrlPagingDatabaseData(@RequestParam int total,
                                                             @RequestParam(defaultValue = "0") int pageIndex,
                                                             @RequestParam(defaultValue = "20") int pageSize) {
        int count = BigDataData.normalizeTotal(total);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("recordCount", count);
        result.put("data", BigDataData.paged(count, pageIndex, pageSize));
        return result;
    }
}
