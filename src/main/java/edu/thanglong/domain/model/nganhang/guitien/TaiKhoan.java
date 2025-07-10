package edu.thanglong.domain.model.nganhang.guitien;

import java.math.BigDecimal;

public class TaiKhoan {
    private final String maTaiKhoan;
    private BigDecimal soDu;

    public TaiKhoan(String maTaiKhoan, BigDecimal soDu) {
        this.maTaiKhoan = maTaiKhoan;
        this.soDu = soDu;
    }

    public String getMaTaiKhoan() { return maTaiKhoan; }
    public BigDecimal getSoDu() { return soDu; }

    /** Tăng số dư (gui tien) */
    public void napTien(BigDecimal amount) {
        this.soDu = this.soDu.add(amount);
    }
}
