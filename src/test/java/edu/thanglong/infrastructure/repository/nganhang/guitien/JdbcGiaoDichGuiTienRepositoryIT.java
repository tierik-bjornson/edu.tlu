package edu.thanglong.infrastructure.repository.nganhang.guitien;

import edu.thanglong.domain.model.nganhang.guitien.GiaoDich;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcGiaoDichGuiTienRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(statements = {
        "CREATE TABLE giao_dich (ma_tai_khoan VARCHAR(50), so_tien DECIMAL, loai_giao_dich VARCHAR(50), NGAY_GIAO_DICH TIMESTAMP);"
})
class JdbcGiaoDichGuiTienRepositoryIT {
    @Autowired
    private JdbcGiaoDichGuiTienRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testSave() {
        GiaoDich gd = new GiaoDich("123", BigDecimal.valueOf(500), "NAP_TIEN");
        repository.save(gd);
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM giao_dich WHERE ma_tai_khoan = '123' AND so_tien = 500", Integer.class);
        assertEquals(1, count);
    }
} 