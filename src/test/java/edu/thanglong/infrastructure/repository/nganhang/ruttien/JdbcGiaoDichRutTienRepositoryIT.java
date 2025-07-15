package edu.thanglong.infrastructure.repository.nganhang.ruttien;

import edu.thanglong.domain.model.nganhang.ruttien.GiaoDich;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcGiaoDichRutTienRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(statements = {
        "CREATE TABLE giao_dich (ma_tai_khoan BIGINT, so_tien DOUBLE, loai_giao_dich VARCHAR(50));"
})
class JdbcGiaoDichRutTienRepositoryIT {
    @Autowired
    private JdbcGiaoDichRutTienRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testLuuGiaoDich() {
        GiaoDich gd = new GiaoDich();
        gd.setMaTaiKhoan(1L);
        gd.setSoTien(100.0);
        gd.setLoaiGiaoDich("RUT_TIEN");
        repository.luuGiaoDich(gd);
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM giao_dich WHERE ma_tai_khoan = 1 AND so_tien = 100.0", Integer.class);
        assertEquals(1, count);
    }
} 