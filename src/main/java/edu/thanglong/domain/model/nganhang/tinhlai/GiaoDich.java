package edu.thanglong.domain.model.nganhang.tinhlai;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDich {
    private Long maTaiKhoan;
    private BigDecimal soTien;
    private String loaiGiaoDich;
    private LocalDateTime thoiGian;

    public static GiaoDich fromLaiSuat(Long maTaiKhoan, BigDecimal soTien) {
        GiaoDich gd = new GiaoDich();
        gd.maTaiKhoan = maTaiKhoan;
        gd.soTien = soTien;
        gd.loaiGiaoDich = "LAI_SUAT";
        gd.thoiGian = LocalDateTime.now();
        return gd;
    }

    // Getters/setters
}
