package edu.thanglong.domain;

import java.util.Optional;

import edu.thanglong.domain.entity.GiaoDich;
import edu.thanglong.domain.entity.TaiKhoan;

public interface TaiKhoanRepository {
    Optional<TaiKhoan> findById(Long maTaiKhoan);
    void update(TaiKhoan taiKhoan);
    void saveGiaoDich(GiaoDich giaoDich);
}