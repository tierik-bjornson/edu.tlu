package edu.thanglong.domain.model.nganhang.chuyentien;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class TaiKhoanTest {
    @Test
    void testRutTien() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(1000));
        tk.rutTien(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(800), tk.getSoDu());
    }

    @Test
    void testNapTien() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(1000));
        tk.napTien(BigDecimal.valueOf(500));
        assertEquals(BigDecimal.valueOf(1500), tk.getSoDu());
    }

    @Test
    void testRutTienVuotQuaSoDu() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(100));
        tk.rutTien(BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(-100), tk.getSoDu());
    }

    @Test
    void testNapTienAm() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(100));
        tk.napTien(BigDecimal.valueOf(-50));
        assertEquals(BigDecimal.valueOf(50), tk.getSoDu());
    }

    @Test
    void testRutTienAm() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(100));
        tk.rutTien(BigDecimal.valueOf(-30));
        assertEquals(BigDecimal.valueOf(130), tk.getSoDu());
    }

    @Test
    void testNapTienZero() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(100));
        tk.napTien(BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(100), tk.getSoDu());
    }

    @Test
    void testRutTienZero() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(100));
        tk.rutTien(BigDecimal.ZERO);
        assertEquals(BigDecimal.valueOf(100), tk.getSoDu());
    }
} 