package edu.thanglong.domain.usecase.nganhang;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.thanglong.domain.model.nganhang.chuyentien.GiaoDich;
import edu.thanglong.domain.model.nganhang.chuyentien.TaiKhoan;
import edu.thanglong.domain.repository.nganhang.chuyentien.ChuyenTienRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChuyenTienInteractor implements ChuyenTienUseCase {

    private final ChuyenTienRepository repo;

    @Override
    @Transactional
    public String execute(Long tuTaiKhoan, Long denTaiKhoan, BigDecimal soTien) {
        Optional<TaiKhoan> fromOpt = repo.findById(tuTaiKhoan);
        if (fromOpt.isEmpty()) {
            return "Tài khoản nguồn không tồn tại";
        }

        Optional<TaiKhoan> toOpt = repo.findById(denTaiKhoan);
        if (toOpt.isEmpty()) {
            return "Tài khoản đích không tồn tại";
        }

        TaiKhoan from = fromOpt.get();
        TaiKhoan to = toOpt.get();

        from.rutTien(soTien);
        to.napTien(soTien);

        repo.update(from);
        repo.update(to);

        repo.saveGiaoDich(new GiaoDich(
                tuTaiKhoan,
                denTaiKhoan,
                soTien,
                "CHUYEN_TIEN"
        ));

        return "Chuyển tiền thành công";
    }
}
