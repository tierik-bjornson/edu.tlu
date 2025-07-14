package edu.thanglong.domain.model.nganhang.tinhlai;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TaiKhoanTest {
    @Test
    void testCongLai() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(1000));
        tk.congLai(BigDecimal.valueOf(10)); // 10% lãi
        assertEquals(BigDecimal.valueOf(1100), tk.getSoDu());
    }

    @Test
    void testCongLaiTyLeAm() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(1000));
        tk.congLai(BigDecimal.valueOf(-10));
        assertEquals(BigDecimal.valueOf(900), tk.getSoDu());
    }

    @Test
    void testCongLaiTyLeZero() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(1000));
        tk.congLai(BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(1000), tk.getSoDu());
    }

    @Test
    void testCongLaiSoDuAm() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(-1000));
        tk.congLai(BigDecimal.valueOf(10));
        assertEquals(BigDecimal.valueOf(-1100), tk.getSoDu());
    }
} 