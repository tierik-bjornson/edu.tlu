package edu.thanglong.infrastructure.repository.nganhang.ruttien;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.thanglong.domain.repository.nganhang.ruttien.TaiKhoanRepository;


@Repository
public class JdbcTaiKhoanRutTienRepository implements TaiKhoanRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTaiKhoanRutTienRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void truTien(Long maTaiKhoan, Double soTien) {
        String sql = "UPDATE tai_khoan SET so_du = so_du - ? WHERE ma_tai_khoan = ?";
        jdbcTemplate.update(sql, soTien, maTaiKhoan);
    }
}