package edu.thanglong.interfaces.restful.nganhang.ruttien;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.thanglong.domain.model.nganhang.ruttien.RutTienRequest;
import edu.thanglong.domain.usecase.nganhang.ruttien.RutTienUseCase;

@RestController
@RequestMapping("/api/nganhang")
public class RutTienController {

    private final RutTienUseCase rutTienUseCase;

    public RutTienController(RutTienUseCase rutTienUseCase) {
        this.rutTienUseCase = rutTienUseCase;
    }

    @PostMapping("/ruttien")
    public ResponseEntity<String> rutTien(@RequestBody RutTienRequest rutTienRequest) {
        rutTienUseCase.thucHienRutTien(rutTienRequest.getMaTaiKhoan(), rutTienRequest.getSoTien());
        return ResponseEntity.ok("Rút tiền thành công từ tài khoản "+rutTienRequest.getMaTaiKhoan()+", số tiền " +rutTienRequest.getSoTien());
    }
}