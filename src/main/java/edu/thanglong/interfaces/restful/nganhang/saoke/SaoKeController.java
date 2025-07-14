package edu.thanglong.interfaces.restful.nganhang.saoke;

import edu.thanglong.domain.model.nganhang.saoke.GiaoDich;
import edu.thanglong.domain.usecase.nganhang.saoke.SaoKeUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nganhang")
public class SaoKeController {

    private final SaoKeUseCase useCase;

    public SaoKeController(SaoKeUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/saoke/{taiKhoan}")
    public List<GiaoDich> laySaoKeThang(
            @PathVariable long taiKhoan,
            @RequestParam int thang,
            @RequestParam int nam) {
        return useCase.inSaoKeThang(taiKhoan, thang, nam);
    }
}
