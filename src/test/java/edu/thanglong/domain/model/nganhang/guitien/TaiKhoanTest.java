package edu.thanglong.domain.model.nganhang.guitien;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TaiKhoanTest {
    @Test
    void testNapTien() {
        TaiKhoan tk = new TaiKhoan("123", BigDecimal.valueOf(1000));
        tk.napTien(BigDecimal.valueOf(300));
        assertEquals(BigDecimal.valueOf(1300), tk.getSoDu());
    }
} 