package edu.thanglong.infrastructure.repository.nganhang.chuyentien;

import edu.thanglong.domain.model.nganhang.chuyentien.GiaoDich;
import edu.thanglong.domain.model.nganhang.chuyentien.TaiKhoan;
import edu.thanglong.domain.repository.nganhang.chuyentien.ChuyenTienRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcChuyenTienRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(statements = {
        "CREATE TABLE tai_khoan (ma_tai_khoan BIGINT PRIMARY KEY, so_du DECIMAL);",
        "CREATE TABLE giao_dich (ma_tai_khoan BIGINT, tai_khoan_lien_quan BIGINT, so_tien DECIMAL, loai_giao_dich VARCHAR(50), ngay_giao_dich TIMESTAMP);",
        "INSERT INTO tai_khoan VALUES (1, 1000), (2, 2000);"
})
class JdbcChuyenTienRepositoryIT {
    @Autowired
    private JdbcChuyenTienRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testFindById() {
        Optional<TaiKhoan> tk = repository.findById(1L);
        assertTrue(tk.isPresent());
        assertEquals(BigDecimal.valueOf(1000), tk.get().getSoDu());
    }

    @Test
    void testFindById_NotFound() {
        Optional<TaiKhoan> tk = repository.findById(999L);
        assertFalse(tk.isPresent());
    }

    @Test
    void testUpdate() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(500));
        repository.update(tk);
        BigDecimal soDu = jdbcTemplate.queryForObject("SELECT so_du FROM tai_khoan WHERE ma_tai_khoan = 1", BigDecimal.class);
        assertEquals(BigDecimal.valueOf(500), soDu);
    }

    @Test
    void testUpdate_SoDuAm() {
        TaiKhoan tk = new TaiKhoan(1L, BigDecimal.valueOf(-500));
        repository.update(tk);
        BigDecimal soDu = jdbcTemplate.queryForObject("SELECT so_du FROM tai_khoan WHERE ma_tai_khoan = 1", BigDecimal.class);
        assertEquals(BigDecimal.valueOf(-500), soDu);
    }

    @Test
    void testSaveGiaoDich() {
        GiaoDich gd = new GiaoDich(1L, 2L, BigDecimal.valueOf(100), "CHUYEN_TIEN");
        repository.saveGiaoDich(gd);
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM giao_dich WHERE ma_tai_khoan = 1 AND tai_khoan_lien_quan = 2", Integer.class);
        assertEquals(1, count);
    }

    @Test
    void testSaveGiaoDich_SoTienAm() {
        GiaoDich gd = new GiaoDich(1L, 2L, BigDecimal.valueOf(-100), "CHUYEN_TIEN");
        repository.saveGiaoDich(gd);
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM giao_dich WHERE ma_tai_khoan = 1 AND so_tien = -100", Integer.class);
        assertEquals(1, count);
    }
} 