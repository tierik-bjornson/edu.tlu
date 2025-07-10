package edu.thanglong.infrastructure.repository.nganhang.tinhlai;

import edu.thanglong.domain.model.nganhang.tinhlai.TaiKhoan;
import edu.thanglong.domain.model.nganhang.tinhlai.GiaoDich;
import edu.thanglong.domain.repository.nganhang.tinhlai.TinhLaiRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class JdbcTinhLaiRepository implements TinhLaiRepository {
    private final JdbcTemplate jdbc;

    public JdbcTinhLaiRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<TaiKhoan> findAllTaiKhoan() {
        return jdbc.query("SELECT ma_tai_khoan, so_du FROM tai_khoan ",
            (rs, rowNum) -> new TaiKhoan(rs.getLong(1), rs.getBigDecimal(2)));
    }

    
    @Override
    public void updateTaiKhoanBatch(List<TaiKhoan> danhSachTaiKhoan) {
        jdbc.batchUpdate(
            "UPDATE tai_khoan SET so_du = ? WHERE ma_tai_khoan = ?",
            danhSachTaiKhoan,
            100, // batch size
            (ps, tk) -> {
                ps.setBigDecimal(1, tk.getSoDu());
                ps.setLong(2, tk.getMaTaiKhoan());
            }
        );
    }

    
    @Override
    public void saveGiaoDichBatch(List<GiaoDich> danhSachGD) {
        jdbc.batchUpdate(
            "INSERT INTO giao_dich(ma_tai_khoan, so_tien, loai_giao_dich, ngay_giao_dich) VALUES (?, ?, ?, ?)",
            danhSachGD,
            100,
            (ps, gd) -> {
                ps.setLong(1, gd.getMaTaiKhoan());
                ps.setBigDecimal(2, gd.getSoTien());
                ps.setString(3, gd.getLoaiGiaoDich());
                ps.setTimestamp(4, Timestamp.valueOf(gd.getThoiGian()));
            }
        );
    }

}
