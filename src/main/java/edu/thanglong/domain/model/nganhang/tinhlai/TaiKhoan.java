package edu.thanglong.domain.model.nganhang.tinhlai;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoan {
    private Long maTaiKhoan;
    private BigDecimal soDu;


    public void congLai(BigDecimal tyLe) {
        BigDecimal lai = soDu.multiply(tyLe).divide(BigDecimal.valueOf(100));
        soDu = soDu.add(lai);
    }


}
