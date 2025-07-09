package edu.thanglong.domain.repository.nganhang.ruttien;

import edu.thanglong.domain.model.nganhang.ruttien.GiaoDich;

public interface TaiKhoanRepository {
    void truTien(Long maTaiKhoan, Double soTien);
}