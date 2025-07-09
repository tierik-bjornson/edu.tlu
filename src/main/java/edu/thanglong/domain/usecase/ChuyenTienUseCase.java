package edu.thanglong.domain.usecase;


import java.math.BigDecimal;

public interface ChuyenTienUseCase {
    String execute(Long tuTaiKhoan, Long denTaiKhoan, BigDecimal soTien);
}