package edu.thanglong.domain.model.nganhang.guitien;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class GiaoDich {
    private Long id;
    private final Long maTaiKhoan;
    private final BigDecimal soTien;
    private final String loaiGiaoDich;
    private final LocalDateTime thoiGian;

    public GiaoDich(Long maTaiKhoan, BigDecimal soTien, String loaiGiaoDich) {
        this.maTaiKhoan   = maTaiKhoan;
        this.soTien       = soTien;
        this.loaiGiaoDich = loaiGiaoDich;
        this.thoiGian     = LocalDateTime.now();
    }

    public Long getMaTaiKhoan() { return maTaiKhoan; }
    public BigDecimal getSoTien() { return soTien; }
    public String getLoaiGiaoDich() { return loaiGiaoDich; }
    public LocalDateTime getThoiGian() { return thoiGian; }
}
