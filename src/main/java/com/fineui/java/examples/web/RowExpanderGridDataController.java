package com.fineui.java.examples.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 行扩展列「嵌套 HTML 表格」示例的数据接口：按行 id 返回该学生三次考试的五科成绩，
 * 供页面展开行时用 jQuery ajax 拉取、拼成 HTML 成绩表显示。
 *
 * <p>返回二维数组：{@code [["入学",语,数,英,物,化],["期中",...],["期末",...]]}。分数按 rowId 奇偶落在不同区间，稳定可复现。
 */
@RestController
public class RowExpanderGridDataController {

    private static final String[] EXAM_NAMES = {"入学", "期中", "期末"};

    // GET /grid/row-expander-grid-data?rowId=101（REST 端点是精确路径匹配，不像页面路由那样大小写/连字符归一化，
    // 故显式登记页面 dataUrl 用的 kebab 路径与直接访问用的另一种拼写，两者都命中同一数据接口）
    @GetMapping("/grid/row-expander-grid-data")
    public List<List<Object>> gridData(@RequestParam(defaultValue = "0") int rowId) {
        // 偶数行落低分段、奇数行落高分段；用确定性算法代替随机，避免测试抖动。
        int base = (rowId % 2 == 0) ? 45 : 82;
        List<List<Object>> result = new ArrayList<>();
        for (int i = 0; i < EXAM_NAMES.length; i++) {
            List<Object> item = new ArrayList<>();
            item.add(EXAM_NAMES[i]);
            for (int s = 0; s < 5; s++) {
                item.add(base + (rowId + i * 5 + s * 3) % 15);   // 稳定分布在 [base, base+14]
            }
            result.add(item);
        }
        return result;
    }
}
