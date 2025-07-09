package edu.thanglong.infrastructure.repository.nganhang.chuyentien;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.thanglong.domain.model.nganhang.chuyentien.TaiKhoan;
import edu.thanglong.domain.model.nganhang.chuyentien.GiaoDich;
import edu.thanglong.domain.repository.nganhang.chuyentien.ChuyenTienRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcChuyenTienRepository implements ChuyenTienRepository {
    private final JdbcTemplate jdbc;

    private static final RowMapper<TaiKhoan> TAI_KHOAN_MAPPER = (rs, rowNum) -> new TaiKhoan(
        rs.getLong("ma_tai_khoan"),
        rs.getBigDecimal("so_du")
    );

    @Override
    public Optional<TaiKhoan> findById(Long maTaiKhoan) {
        return jdbc.query("SELECT ma_tai_khoan, so_du FROM tai_khoan WHERE ma_tai_khoan = ?",
            new Object[]{maTaiKhoan}, TAI_KHOAN_MAPPER)
            .stream().findFirst();
    }

    @Override
    public void update(TaiKhoan taiKhoan) {
        jdbc.update("UPDATE tai_khoan SET so_du = ? WHERE ma_tai_khoan = ?",
            taiKhoan.getSoDu(), taiKhoan.getMaTaiKhoan());
    }

    @Override
    public void saveGiaoDich(GiaoDich giaoDich) {
        jdbc.update(
            "INSERT INTO giao_dich (ma_tai_khoan, tai_khoan_lien_quan, so_tien, loai_giao_dich, ngay_giao_dich)"
            + " VALUES (?, ?, ?, ?, ?)",
            giaoDich.getMaTaiKhoan(), giaoDich.getTaiKhoanLienQuan(),
            giaoDich.getSoTien(), giaoDich.getLoaiGiaoDich(),
            LocalDateTime.now()
        );
    }
}