package edu.thanglong.interfaces.restful.nganhang.tinhlai;

import edu.thanglong.domain.usecase.nganhang.tinhlai.TinhLaiUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/nganhang")
public class TinhLaiController {
    private final TinhLaiUseCase useCase;

    public TinhLaiController(TinhLaiUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/tinhlai")
    public ResponseEntity<String> tinhLai(@RequestBody Map<String, BigDecimal> request) {
        try {
            BigDecimal tyLe = request.get("tyLe");
            if (tyLe == null) {
                return ResponseEntity.badRequest().body("Thiếu tỷ lệ lãi (tyLe)");
            }
            String result = useCase.tinhLai(tyLe);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(500).body("Lỗi nội bộ: " + e.getMessage());
        }
    }
    
}
