package edu.thanglong.infrastructure.repository.nganhang.laysodu;

import edu.thanglong.domain.repository.nganhang.laysodu.LaySoDuRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcLaySoDuRepository implements LaySoDuRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLaySoDuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Double laySoDu(Long maTaiKhoan) {
        String sql = "SELECT so_du FROM tai_khoan WHERE ma_tai_khoan = ?";
        return jdbcTemplate.queryForObject(sql, Double.class, maTaiKhoan);
    }
}
