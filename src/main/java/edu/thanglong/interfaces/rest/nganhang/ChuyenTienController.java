package edu.thanglong.interfaces.rest.nganhang;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.thanglong.domain.model.nganhang.ChuyenTien;
import edu.thanglong.domain.model.nganhang.TransferResponse;
import edu.thanglong.domain.usecase.nganhang.ChuyenTienUseCase;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transfer")
@RequiredArgsConstructor
public class ChuyenTienController {
    private final ChuyenTienUseCase chuyenTien;

    @PostMapping
    public ResponseEntity<TransferResponse> transfer(@RequestBody ChuyenTien request) {
        String resp = chuyenTien.execute(request.getFrom(), request.getTo(), request.getAmount());
        TransferResponse response = new TransferResponse(resp);
        return ResponseEntity.ok(response);
    }
}

