package edu.thanglong.interfaces.restful.nganhang.chuyentien;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.thanglong.domain.model.nganhang.chuyentien.TransferResponse;
import edu.thanglong.domain.model.nganhang.chuyentien.ChuyenTien;
import edu.thanglong.domain.usecase.nganhang.chuyentien.ChuyenTienUseCase;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/nganhang")
@RequiredArgsConstructor
public class ChuyenTienController {
    private final ChuyenTienUseCase chuyenTien;

    @PostMapping("/chuyentien")
    public ResponseEntity<TransferResponse> transfer(@RequestBody ChuyenTien request) {
        String resp = chuyenTien.execute(request.getFrom(), request.getTo(), request.getAmount());
        TransferResponse response = new TransferResponse(resp);
        return ResponseEntity.ok(response);
    }
}

