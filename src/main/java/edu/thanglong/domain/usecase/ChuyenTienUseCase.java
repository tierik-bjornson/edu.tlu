package edu.thanglong.domain.usecase;


import java.math.BigDecimal;

public interface ChuyenTienUseCase {
    void execute(Long tuTaiKhoan, Long denTaiKhoan, BigDecimal soTien);
}