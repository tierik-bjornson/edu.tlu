package edu.thanglong.infrastructure.repository.nganhang.ruttien;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcTaiKhoanRutTienRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(statements = {
        "CREATE TABLE tai_khoan (ma_tai_khoan BIGINT PRIMARY KEY, so_du DOUBLE);",
        "INSERT INTO tai_khoan VALUES (1, 1000.0);"
})
class JdbcTaiKhoanRutTienRepositoryIT {
    @Autowired
    private JdbcTaiKhoanRutTienRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testTruTien() {
        repository.truTien(1L, 200.0);
        Double soDu = jdbcTemplate.queryForObject("SELECT so_du FROM tai_khoan WHERE ma_tai_khoan = 1", Double.class);
        assertEquals(800.0, soDu);
    }
} 