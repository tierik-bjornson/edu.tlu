package edu.thanglong.infrastructure.repository.nganhang.tinhlai;

import edu.thanglong.domain.model.nganhang.tinhlai.TaiKhoan;
import edu.thanglong.domain.model.nganhang.tinhlai.GiaoDich;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcTinhLaiRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(statements = {
        "CREATE TABLE tai_khoan (ma_tai_khoan BIGINT PRIMARY KEY, so_du DECIMAL);",
        "CREATE TABLE giao_dich (ma_tai_khoan BIGINT, so_tien DECIMAL, loai_giao_dich VARCHAR(50), ngay_giao_dich TIMESTAMP);",
        "INSERT INTO tai_khoan VALUES (1, 1000), (2, 2000);"
})
class JdbcTinhLaiRepositoryIT {
    @Autowired
    private JdbcTinhLaiRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testFindAllTaiKhoan() {
        List<TaiKhoan> list = repository.findAllTaiKhoan();
        assertEquals(2, list.size());
    }

    @Test
    void testUpdateTaiKhoanBatch() {
        TaiKhoan tk1 = new TaiKhoan(1L, BigDecimal.valueOf(1500));
        TaiKhoan tk2 = new TaiKhoan(2L, BigDecimal.valueOf(2500));
        repository.updateTaiKhoanBatch(Arrays.asList(tk1, tk2));
        BigDecimal soDu1 = jdbcTemplate.queryForObject("SELECT so_du FROM tai_khoan WHERE ma_tai_khoan = 1", BigDecimal.class);
        BigDecimal soDu2 = jdbcTemplate.queryForObject("SELECT so_du FROM tai_khoan WHERE ma_tai_khoan = 2", BigDecimal.class);
        assertEquals(BigDecimal.valueOf(1500), soDu1);
        assertEquals(BigDecimal.valueOf(2500), soDu2);
    }

    @Test
    void testSaveGiaoDichBatch() {
        GiaoDich gd1 = new GiaoDich(1L, BigDecimal.valueOf(100), "LAI", LocalDateTime.now());
        GiaoDich gd2 = new GiaoDich(2L, BigDecimal.valueOf(200), "LAI", LocalDateTime.now());
        repository.saveGiaoDichBatch(Arrays.asList(gd1, gd2));
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM giao_dich WHERE loai_giao_dich = 'LAI'", Integer.class);
        assertEquals(2, count);
    }
} 