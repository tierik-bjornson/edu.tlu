package edu.thanglong.domain.model.nganhang;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Setter
@Getter
public class ChuyenTien {
    private Long from;
    private Long to;
    private BigDecimal amount;
}
