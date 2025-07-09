package edu.thanglong.domain.repository.nganhang.chuyentien;

import java.util.Optional;

import edu.thanglong.domain.model.nganhang.chuyentien.GiaoDich;
import edu.thanglong.domain.model.nganhang.chuyentien.TaiKhoan;

public interface ChuyenTienRepository {
    Optional<TaiKhoan> findById(Long maTaiKhoan);
    void update(TaiKhoan taiKhoan);
    void saveGiaoDich(GiaoDich giaoDich);
}