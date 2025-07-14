package edu.thanglong.interfaces.restful.nganhang.laysodu;

import edu.thanglong.domain.usecase.nganhang.laysodu.LaySoDuUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nganhang")
public class LaySoDuController {

    private final LaySoDuUseCase useCase;

    public LaySoDuController(LaySoDuUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/laysodu/{maTaiKhoan}")
    public ResponseEntity<Double> laySoDu(@PathVariable Long maTaiKhoan) {
        Double soDu = useCase.execute(maTaiKhoan);
        return ResponseEntity.ok(soDu);
    }
}
