package edu.thanglong.infrastructure.repository.nganhang.guitien;


import edu.thanglong.domain.model.nganhang.guitien.TaiKhoan;
import edu.thanglong.domain.repository.nganhang.guitien.TaiKhoanRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcTaiKhoanGuiTienRepository implements TaiKhoanRepository {
    private final JdbcTemplate jdbc;

    public JdbcTaiKhoanGuiTienRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<TaiKhoan> mapper = (rs, rn) ->
        new TaiKhoan(
            rs.getString("ma_tai_khoan"),
            rs.getBigDecimal("so_du")
        );

    @Override
    public Optional<TaiKhoan> findByMaTaiKhoan(Long maTaiKhoan) {
        String sql = "SELECT ma_tai_khoan, so_du FROM tai_khoan WHERE ma_tai_khoan = ?";
        return jdbc.query(sql, mapper, maTaiKhoan).stream().findFirst();
    }

    @Override
    public void update(TaiKhoan taiKhoan) {
        String sql = "UPDATE tai_khoan SET so_du = ? WHERE ma_tai_khoan = ?";
        jdbc.update(sql, taiKhoan.getSoDu(), taiKhoan.getMaTaiKhoan());
    }
}
