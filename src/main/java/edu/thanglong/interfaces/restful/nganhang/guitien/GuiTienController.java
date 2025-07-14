package edu.thanglong.interfaces.restful.nganhang.guitien;


import edu.thanglong.domain.usecase.nganhang.guitien.GuiTienUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/nganhang")
public class GuiTienController {

    private final GuiTienUseCase useCase;

    public GuiTienController(GuiTienUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/guitien")
    public ResponseEntity<HashMap<String,String>> guiTien(@RequestBody GuiTienRequest rq) {
        useCase.execute(rq.getMaTaiKhoan(), rq.getSoTien());
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Gửi tiền thành công"); 
        response.put("maTaiKhoan", rq.getMaTaiKhoan());
        response.put("soTien", rq.getSoTien().toString());
        return ResponseEntity.ok().body(response);            
    }

    public static class GuiTienRequest {
        private String maTaiKhoan;
        private BigDecimal soTien;
        // getters & setters
        public String getMaTaiKhoan() { return maTaiKhoan; }
        public void setMaTaiKhoan(String maTaiKhoan) { this.maTaiKhoan = maTaiKhoan; }
        public BigDecimal getSoTien() { return soTien; }
        public void setSoTien(BigDecimal soTien) { this.soTien = soTien; }
    }
}
