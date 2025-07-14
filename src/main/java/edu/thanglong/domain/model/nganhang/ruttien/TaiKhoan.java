package edu.thanglong.domain.model.nganhang.ruttien;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoan {
    private Long maTaiKhoan;
    private BigDecimal soDu;
    // getters/setters
}