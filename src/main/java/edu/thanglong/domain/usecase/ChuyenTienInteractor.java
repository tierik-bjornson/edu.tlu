package edu.thanglong.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.thanglong.domain.model.GiaoDich;
import edu.thanglong.domain.model.TaiKhoan;
import edu.thanglong.infrastructure.repository.TaiKhoanRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ChuyenTienInteractor implements ChuyenTienUseCase {
    private final TaiKhoanRepository repo;

    @Override
    @Transactional
    public void execute(Long tuTaiKhoan, Long denTaiKhoan, BigDecimal soTien) {
        TaiKhoan from = repo.findById(tuTaiKhoan)
            .orElseThrow(() -> new IllegalArgumentException("Tài khoản nguồn không tồn tại."));
        TaiKhoan to = repo.findById(denTaiKhoan)
            .orElseThrow(() -> new IllegalArgumentException("Tài khoản đích không tồn tại"));

        from.rutTien(soTien);
        to.napTien(soTien);

        repo.update(from);
        repo.update(to);
        repo.saveGiaoDich(new GiaoDich(tuTaiKhoan, denTaiKhoan, soTien, "CHUYEN_TIEN"));
    }
}