package com.fineui.java.examples.gridbigdata;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** GridBigData 示例共用的数据生成器。数据按需生成，避免数据库分页和网址数据源预先持有整表。 */
public final class BigDataData {

    public static final int MAX_TOTAL = 10_000;

    private record User(String name, int gender) {
    }

    // 205 组姓名/性别基础数据，重复姓名从第 206 行才开始追加组号。
    // 每一项自包含姓名和性别，禁止用平行数组以避免下标错位。
    private static final User[] USERS = {
            new User("童光喜", 1), new User("方原柏", 1), new User("祝春亚", 0), new User("涂辉", 1),
            new User("舒兆国", 1), new User("熊忠文", 1), new User("徐吉琳", 1), new User("方金海", 1),
            new User("包卫峰", 1), new User("靖小燕", 0), new User("杨习斌", 1), new User("徐长旺", 1),
            new User("聂建雄", 1), new User("周敦友", 1), new User("陈友庭", 1), new User("陆静芳", 0),
            new User("袁国柱", 1), new User("骆新桂", 0), new User("许治国", 1), new User("马先加", 1),
            new User("赵恢川", 1), new User("柯常胜", 1), new User("黄国鹏", 1), new User("柯尊北", 1),
            new User("刘海云", 1), new User("罗清波", 1), new User("张业权", 1), new User("丁溯鋆", 0),
            new User("吴俊", 1), new User("郑江", 1), new User("李亚华", 1), new User("石光富", 1),
            new User("谭志洪", 1), new User("胡中生", 1), new User("董龙剑", 1), new User("陈红", 1),
            new User("汪海平", 1), new User("彭道洲", 1), new User("尹莉君", 0), new User("占耀玲", 1),
            new User("付杰", 1), new User("王红艳", 1), new User("邝兴", 1), new User("饶玮", 1),
            new User("王方胜", 1), new User("陈劲松", 1), new User("邓庆华", 1), new User("王石林", 1),
            new User("胡俊明", 1), new User("索相龙", 1), new User("陈海军", 1), new User("吴文涛", 1),
            new User("熊望梅", 0), new User("段丽华", 0), new User("胡莎莎", 0), new User("徐友安", 1),
            new User("肖诗涛", 1), new User("王闯", 1), new User("余兴龙", 1), new User("芦荫杰", 1),
            new User("丁金富", 1), new User("谭军令", 1), new User("鄢旭燕", 0), new User("田坤", 1),
            new User("夏德胜", 1), new User("喻显发", 1), new User("马兴宝", 1), new User("孙学涛", 1),
            new User("陶云成", 1), new User("马远健", 1), new User("田华", 1), new User("聂子森", 1),
            new User("郑永军", 1), new User("余昌平", 1), new User("陶俊华", 1), new User("李小林", 1),
            new User("李荣宝", 1), new User("梅盈凯", 1), new User("张元群", 1), new User("郝新华", 1),
            new User("刘红涛", 1), new User("向志强", 1), new User("伍小峰", 1), new User("胡勇民", 1),
            new User("黄定祥", 1), new User("高红香", 0), new User("刘军", 1), new User("叶松", 1),
            new User("易俊林", 1), new User("张威", 1), new User("刘卫华", 1), new User("李浩", 1),
            new User("李寿庚", 1), new User("涂洋", 1), new User("曹晶", 1), new User("陈辉", 1),
            new User("彭博", 0), new User("严雪冰", 1), new User("刘青", 1), new User("印媛", 0),
            new User("吴道雄", 1), new User("邓旻", 1), new User("陈骏", 1), new User("崔波", 1),
            new User("韩静颐", 1), new User("严安勇", 1), new User("刘攀", 1), new User("刘艳", 0),
            new User("孙昕", 0), new User("郑新", 0), new User("徐睿", 0), new User("李月杰", 0),
            new User("吕焱鑫", 1), new User("刘沈", 0), new User("朱绍军", 1), new User("马茜", 0),
            new User("唐蕾", 0), new User("刘姣", 0), new User("于芳", 0), new User("吴健", 1),
            new User("张丹梅", 0), new User("王燕", 0), new User("贾兆梅", 0), new User("程柏漠", 1),
            new User("程辉", 1), new User("任明慧", 0), new User("焦莹", 0), new User("马淑娟", 0),
            new User("徐涛", 1), new User("孙庆国", 1), new User("刘胜", 1), new User("傅广凤", 0),
            new User("袁弘", 1), new User("高令旭", 1), new User("栾树权", 1), new User("申霞", 0),
            new User("韩文萍", 0), new User("隋艳", 0), new User("邢海洲", 1), new User("王宁", 0),
            new User("陈晶", 0), new User("吕翠", 0), new User("刘少敏", 0), new User("刘少君", 0),
            new User("孔鹏", 1), new User("张冰", 0), new User("王芳", 0), new User("万世忠", 1),
            new User("徐凡", 0), new User("张玉梅", 0), new User("何莉", 0), new User("时会云", 0),
            new User("王玉杰", 0), new User("谭素英", 0), new User("李艳红", 0), new User("刘素莉", 0),
            new User("王旭海", 1), new User("安丽梅", 0), new User("姚露", 0), new User("贾颖", 0),
            new User("曹微", 0), new User("黄经华", 1), new User("陈玉华", 0), new User("姜媛", 0),
            new User("魏立平", 0), new User("张萍", 0), new User("来辉", 1), new User("陈秀玫", 0),
            new User("石岩", 1), new User("王洪捍", 1), new User("张树军", 1), new User("李亚琴", 0),
            new User("王凤", 0), new User("王珊华", 0), new User("杨丹丹", 0), new User("教黎明", 0),
            new User("修晶", 0), new User("丁晓霞", 0), new User("张丽", 0), new User("郭素兰", 0),
            new User("徐艳丽", 0), new User("任子英", 0), new User("胡雁", 0), new User("彭洪亮", 0),
            new User("高玉珍", 0), new User("王玉姝", 0), new User("郑伟", 1), new User("姜春玲", 0),
            new User("张伟", 0), new User("王颖", 0), new User("金萍", 0), new User("孙望", 1),
            new User("闫宝东", 1), new User("周相永", 1), new User("杨美娜", 0), new User("欧立新", 0),
            new User("刘宝霞", 0), new User("刘艳杰", 0), new User("宋艳平", 0), new User("李克", 1),
            new User("梁翠", 0), new User("宗宏伟", 0), new User("刘国伟", 0), new User("敖志敏", 0),
            new User("尹玲", 0)
    };
    private static final String[] MAJORS = {
            "数学系", "计算与应用数学系", "概率统计系", "物理系", "近代物理系", "光学与光学工程系",
            "天文学系", "化学物理系", "材料科学与工程系", "化学系", "高分子科学与工程系", "近代力学系",
            "精密机械与精密仪器系", "热科学和能源工程系", "安全科学与工程系", "电子工程与信息科学系",
            "自动化系", "电子科学与技术系", "外语系", "工商管理系", "管理科学系", "统计与金融系"
    };
    private static final List<String> FIELDS = List.of("Id", "Name", "Gender", "EntranceYear", "AtSchool", "Major", "Group");

    private BigDataData() {
    }

    /** 将总行数限制在示例承诺的 0～10,000 范围内。 */
    public static int normalizeTotal(int total) {
        return Math.max(0, Math.min(MAX_TOTAL, total));
    }

    /** 生成整表对象行（内存表格和默认网址数据源响应使用）。 */
    public static List<Map<String, Object>> rows(int total) {
        return paged(total, 0, normalizeTotal(total));
    }

    /** 只生成指定页的对象行（数据库分页使用）。 */
    public static List<Map<String, Object>> paged(int total, int pageIndex, int pageSize) {
        int count = normalizeTotal(total);
        if (pageSize <= 0 || count == 0) {
            return new ArrayList<>();
        }
        long fromLong = (long) Math.max(0, pageIndex) * pageSize;
        if (fromLong >= count) {
            return new ArrayList<>();
        }
        int from = (int) fromLong;  // 已确认小于 count（最多 10,000），安全收窄
        int to = Math.min(count, from + pageSize);
        List<Map<String, Object>> result = new ArrayList<>(to - from);
        for (int index = from; index < to; index++) {
            result.add(row(index));
        }
        return result;
    }

    /** 返回 FineUI 紧凑网址数据源格式：{@code {fields:[...],data:[[...]]}}。 */
    public static Map<String, Object> simple(int total) {
        int count = normalizeTotal(total);
        List<List<Object>> data = new ArrayList<>(count);
        for (int index = 0; index < count; index++) {
            Map<String, Object> row = row(index);
            List<Object> values = new ArrayList<>(FIELDS.size());
            for (String field : FIELDS) {
                values.add(row.get(field));
            }
            data.add(values);
        }
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("fields", FIELDS);
        result.put("data", data);
        return result;
    }

    private static Map<String, Object> row(int index) {
        int userIndex = index % USERS.length;
        int loop = index / USERS.length;
        User user = USERS[userIndex];
        String name = user.name() + (loop == 0 ? "" : "（" + loop + "）");
        int entranceYear = 2000 + Math.floorMod(index * 7 + 3, 16);
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("Id", index + 1);
        row.put("Name", name);
        row.put("Gender", user.gender());
        row.put("EntranceYear", entranceYear);
        row.put("AtSchool", entranceYear >= 2008 ? 1 : 0);
        row.put("Major", MAJORS[Math.floorMod(index * 11 + 5, MAJORS.length)]);
        row.put("Group", Math.floorMod(index * 3 + 1, 5) + 1);
        return row;
    }
}
