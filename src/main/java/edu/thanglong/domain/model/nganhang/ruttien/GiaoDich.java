package edu.thanglong.domain.model.nganhang.ruttien;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDich {
    private Long maTaiKhoan;
    private BigDecimal soTien;
    private String loaiGiaoDich; // "RUT_TIEN"
    // getters/setters
}