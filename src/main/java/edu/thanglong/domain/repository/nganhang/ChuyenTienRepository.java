package edu.thanglong.domain.repository.nganhang;

import java.util.Optional;

import edu.thanglong.domain.model.nganhang.GiaoDich;
import edu.thanglong.domain.model.nganhang.TaiKhoan;

public interface ChuyenTienRepository {
    Optional<TaiKhoan> findById(Long maTaiKhoan);
    void update(TaiKhoan taiKhoan);
    void saveGiaoDich(GiaoDich giaoDich);
}