package edu.thanglong.infrastructure.repository;

import java.util.Optional;

import edu.thanglong.domain.model.GiaoDich;
import edu.thanglong.domain.model.TaiKhoan;

public interface TaiKhoanRepository {
    Optional<TaiKhoan> findById(Long maTaiKhoan);
    void update(TaiKhoan taiKhoan);
    void saveGiaoDich(GiaoDich giaoDich);
}