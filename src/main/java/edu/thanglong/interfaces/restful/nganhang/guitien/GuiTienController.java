package edu.thanglong.interfaces.restful.nganhang.guitien;


import edu.thanglong.domain.usecase.nganhang.guitien.GuiTienUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/nganhang")
public class GuiTienController {

    private final GuiTienUseCase useCase;

    public GuiTienController(GuiTienUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/guitien")
    public ResponseEntity<Void> guiTien(@RequestBody GuiTienRequest rq) {
        useCase.execute(rq.getMaTaiKhoan(), rq.getSoTien());
        return ResponseEntity.ok().build();
    }

    public static class GuiTienRequest {
        private Long maTaiKhoan;
        private BigDecimal soTien;
        // getters & setters
        public Long getMaTaiKhoan() { return maTaiKhoan; }
        public void setMaTaiKhoan(Long maTaiKhoan) { this.maTaiKhoan = maTaiKhoan; }
        public BigDecimal getSoTien() { return soTien; }
        public void setSoTien(BigDecimal soTien) { this.soTien = soTien; }
    }
}
