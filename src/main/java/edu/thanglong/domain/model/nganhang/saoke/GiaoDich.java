package edu.thanglong.domain.model.nganhang.saoke;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDich {
    private LocalDate ngayGiaoDich;
    private String loaiGiaoDich;
    private BigDecimal soTien;

    // Constructors, getters, setters
}
