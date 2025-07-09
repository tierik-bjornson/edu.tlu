package edu.thanglong.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDich {
    private Long maGiaoDich;
    private Long maTaiKhoan;
    private Long taiKhoanLienQuan;
    private BigDecimal soTien;
    private String loaiGiaoDich;
    private LocalDateTime ngayGiaoDich;

    public GiaoDich(Long maTaiKhoan, Long taiKhoanLienQuan, BigDecimal soTien, String loaiGiaoDich) {
        this.maTaiKhoan = maTaiKhoan;
        this.taiKhoanLienQuan = taiKhoanLienQuan;
        this.soTien = soTien;
        this.loaiGiaoDich = loaiGiaoDich;
        this.ngayGiaoDich = LocalDateTime.now();
    }
}