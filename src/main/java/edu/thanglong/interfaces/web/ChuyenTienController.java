package edu.thanglong.interfaces.web;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.thanglong.domain.usecase.ChuyenTienUseCase;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transfer")
@RequiredArgsConstructor
public class ChuyenTienController {
    private final ChuyenTienUseCase chuyenTien;

    @PostMapping
    public ResponseEntity<Void> transfer(@RequestBody TransferRequest request) {
        chuyenTien.execute(request.getFrom(), request.getTo(), request.getAmount());
        return ResponseEntity.ok().build();
    }

    @Data
    public static class TransferRequest {
        private Long from;
        private Long to;
        private BigDecimal amount;
    }
}

