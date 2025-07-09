package edu.thanglong.infrastructure.repository.nganhang.guitien;

import edu.thanglong.domain.model.nganhang.guitien.GiaoDich;
import edu.thanglong.domain.repository.nganhang.guitien.GiaoDichRepository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcGiaoDichGuiTienRepository implements GiaoDichRepository {
    private final JdbcTemplate jdbc;

    public JdbcGiaoDichGuiTienRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(GiaoDich gd) {
        String sql = """
            INSERT INTO giao_dich
              (ma_tai_khoan, so_tien, loai_giao_dich, NGAY_GIAO_DICH)
            VALUES (?, ?, ?, ?)
            """;
        jdbc.update(sql,
            gd.getMaTaiKhoan(),
            gd.getSoTien(),
            gd.getLoaiGiaoDich(),
            java.sql.Timestamp.valueOf(gd.getThoiGian())
        );
    }
}
