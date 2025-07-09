package edu.thanglong.domain.model.nganhang.chuyentien;

import java.math.BigDecimal;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoan {
    private Long maTaiKhoan;
    private BigDecimal soDu;

    public void rutTien(BigDecimal amt) {
        this.soDu = this.soDu.subtract(amt);
    }
    public void napTien(BigDecimal amt) {
        this.soDu = this.soDu.add(amt);
    }
}