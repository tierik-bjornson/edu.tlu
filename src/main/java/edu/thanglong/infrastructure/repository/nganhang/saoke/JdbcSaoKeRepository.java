package edu.thanglong.infrastructure.repository.nganhang.saoke;

import edu.thanglong.domain.model.nganhang.saoke.GiaoDich;
import edu.thanglong.domain.repository.nganhang.saoke.SaoKeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcSaoKeRepository implements SaoKeRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSaoKeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GiaoDich> laySaoKeTheoThang(long taiKhoan, int thang, int nam) {
        String sql = """
            SELECT ngay_giao_dich, loai_giao_dich, so_tien
            FROM giao_dich
            WHERE ma_tai_khoan = ?
              AND EXTRACT(MONTH FROM ngay_giao_dich) = ?
              AND EXTRACT(YEAR FROM ngay_giao_dich) = ?
            ORDER BY ngay_giao_dich
        """;

        return jdbcTemplate.query(sql, new Object[]{taiKhoan, thang, nam}, (rs, rowNum) -> mapRow(rs));
    }

    private GiaoDich mapRow(ResultSet rs) throws SQLException {
        GiaoDich gd = new GiaoDich();
        gd.setNgayGiaoDich(rs.getDate("ngay_giao_dich").toLocalDate());
        gd.setLoaiGiaoDich(rs.getString("loai_giao_dich"));
        gd.setSoTien(rs.getBigDecimal("so_tien"));
        return gd;
    }
}
