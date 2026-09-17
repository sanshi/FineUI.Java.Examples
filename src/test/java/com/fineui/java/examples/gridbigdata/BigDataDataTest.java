package com.fineui.java.examples.gridbigdata;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BigDataDataTest {

    @Test
    void keepsTheBaseUserPairsBeforeAddingLoopSuffix() {
        List<Map<String, Object>> rows = BigDataData.rows(206);

        assertEquals("童光喜", rows.get(0).get("Name"));
        assertEquals("刘海云", rows.get(24).get("Name"));
        assertEquals("尹玲", rows.get(204).get("Name"));
        assertEquals("童光喜（1）", rows.get(205).get("Name"));
        assertEquals(0, rows.get(185).get("Gender")); // 王玉姝
        assertEquals(1, rows.get(186).get("Gender")); // 郑伟
    }

    @Test
    void oversizedDatabasePageIsAnEmptyPageRatherThanAnOverflow() {
        assertTrue(BigDataData.paged(10_000, 2, Integer.MAX_VALUE).isEmpty());
    }
}
