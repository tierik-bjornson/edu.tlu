package edu.thanglong.domain.model.nganhang.tinhlai;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class GiaoDichTest {
    @Test
    void testFromLaiSuat() {
        GiaoDich gd = GiaoDich.fromLaiSuat(1L, BigDecimal.valueOf(100));
        assertEquals(1L, gd.getMaTaiKhoan());
        assertEquals(BigDecimal.valueOf(100), gd.getSoTien());
        assertEquals("LAI_SUAT", gd.getLoaiGiaoDich());
        assertNotNull(gd.getThoiGian());
    }
} 