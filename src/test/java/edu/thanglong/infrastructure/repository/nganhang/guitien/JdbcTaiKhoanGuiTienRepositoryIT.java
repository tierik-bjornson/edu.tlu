package edu.thanglong.infrastructure.repository.nganhang.guitien;

import edu.thanglong.domain.model.nganhang.guitien.TaiKhoan;
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
@Import(JdbcTaiKhoanGuiTienRepository.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(statements = {
        "CREATE TABLE tai_khoan (ma_tai_khoan VARCHAR(50) PRIMARY KEY, so_du DECIMAL);",
        "INSERT INTO tai_khoan VALUES ('123', 1000);"
})
class JdbcTaiKhoanGuiTienRepositoryIT {
    @Autowired
    private JdbcTaiKhoanGuiTienRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testFindByMaTaiKhoan() {
        Optional<TaiKhoan> tk = repository.findByMaTaiKhoan("123");
        assertTrue(tk.isPresent());
        assertEquals(BigDecimal.valueOf(1000), tk.get().getSoDu());
    }

    @Test
    void testUpdate() {
        TaiKhoan tk = new TaiKhoan("123", BigDecimal.valueOf(500));
        repository.update(tk);
        BigDecimal soDu = jdbcTemplate.queryForObject("SELECT so_du FROM tai_khoan WHERE ma_tai_khoan = '123'", BigDecimal.class);
        assertEquals(BigDecimal.valueOf(500), soDu);
    }
} 