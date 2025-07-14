package edu.thanglong.domain.repository.nganhang.guitien;


import edu.thanglong.domain.model.nganhang.guitien.TaiKhoan;
import java.util.Optional;

public interface TaiKhoanRepository {
    Optional<TaiKhoan> findByMaTaiKhoan(Long maTaiKhoan);
    void update(TaiKhoan taiKhoan);
}