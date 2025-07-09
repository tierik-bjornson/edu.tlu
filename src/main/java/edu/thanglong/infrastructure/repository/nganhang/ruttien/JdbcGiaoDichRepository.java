package edu.thanglong.infrastructure.repository.nganhang.ruttien;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.thanglong.domain.model.nganhang.ruttien.GiaoDich;
import edu.thanglong.domain.repository.nganhang.ruttien.GiaoDichRepository;

@Repository
public class JdbcGiaoDichRepository implements GiaoDichRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcGiaoDichRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void luuGiaoDich(GiaoDich giaoDich) {
        String sql = "INSERT INTO giao_dich(ma_tai_khoan, so_tien, loai_giao_dich) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
            giaoDich.getMaTaiKhoan(),
            giaoDich.getSoTien(),
            giaoDich.getLoaiGiaoDich()
        );
    }
}
