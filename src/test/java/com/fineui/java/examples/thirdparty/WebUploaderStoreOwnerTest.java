package com.fineui.java.examples.thirdparty;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 回归：{@code WebUploaderStore} 的 owner 前缀守卫。
 *
 * <p>为什么要有它：页面调的是 {@code deleteRow(owner, rowId)}，两个形参都是 String，
 * 写反编译器不报错。守卫加上之前，未知 owner 只会「顺手建个空列表 → 什么都不删 → 不抛异常」，
 * 表现为「点删除没反应」，日志无痕，很难联想到是实参顺序。
 *
 * <p>三个带 owner 的入口（records / save / delete）都要挡，缺一个就留了条绕过的路。
 */
class WebUploaderStoreOwnerTest {

    /** 行标识长这样：写反时它会被当成会话键传进去。 */
    private static final String ROW_ID = "3f2b1c40-9d11-4d2e-8a77-0c5b6e4a1234";

    @Test
    void 前缀常量与各页的会话键一致() {
        assertEquals("webuploader.", WebUploaderStore.OWNER_PREFIX);
        assertTrue(WebUploaderStore.FIXED_OWNER.startsWith(WebUploaderStore.OWNER_PREFIX));
    }

    @Test
    void records_传进行标识当owner时立刻抛异常() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> WebUploaderStore.records(null, ROW_ID));
        assertTrue(e.getMessage().contains("owner 必须是以"), "错误信息要点明实参顺序：" + e.getMessage());
    }

    @Test
    void delete_传进行标识当owner时立刻抛异常() {
        assertThrows(IllegalArgumentException.class,
                () -> WebUploaderStore.delete(null, ROW_ID, "webuploader.webuploader"));
    }

    @Test
    void save_传进行标识当owner时立刻抛异常() {
        assertThrows(IllegalArgumentException.class,
                () -> WebUploaderStore.save(null, ROW_ID, null));
    }

    @Test
    void save_固定槽的owner不带井号行标识时抛异常() {
        //:: 固定槽的会话键只能从「FIXED_OWNER#行标识」那条分支写。
        //:: 光判前缀会有两种坏结果：带 +1 的 substring 越界；或落进普通分支，
        //:: 给只认自己那几个播种槽的固定页凭空多出一条记录。
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> WebUploaderStore.save(null, WebUploaderStore.FIXED_OWNER, null));
        assertTrue(e.getMessage().contains("#行标识"), "错误信息要点明正确形态：" + e.getMessage());
    }

    @Test
    void owner为null时也抛异常() {
        assertThrows(IllegalArgumentException.class, () -> WebUploaderStore.records(null, null));
    }
}
