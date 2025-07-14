package edu.thanglong.domain.usecase.nganhang.guitien;

import edu.thanglong.domain.model.nganhang.guitien.TaiKhoan;
import edu.thanglong.domain.model.nganhang.guitien.GiaoDich;
import edu.thanglong.domain.repository.nganhang.guitien.TaiKhoanRepository;
import edu.thanglong.domain.repository.nganhang.guitien.GiaoDichRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class GuiTienUseCase {
    private final TaiKhoanRepository taiKhoanRepo;
    private final GiaoDichRepository giaoDichRepo;

    public GuiTienUseCase(TaiKhoanRepository taiKhoanRepo,
                          GiaoDichRepository giaoDichRepo) {
        this.taiKhoanRepo = taiKhoanRepo;
        this.giaoDichRepo = giaoDichRepo;
    }

    /**
     * Thực hiện tương đương PROCEDURE gui_tien(tai_khoan, so_tien)
     */
    @Transactional
    public void execute(Long maTaiKhoan, BigDecimal soTien) {
        TaiKhoan tk = taiKhoanRepo.findByMaTaiKhoan(maTaiKhoan)
            .orElseThrow(() -> new IllegalArgumentException("Tài khoản không tồn tại"));
        // UPDATE tai_khoan SET so_du = so_du + so_tien
        tk.napTien(soTien);
        taiKhoanRepo.update(tk);

        // INSERT INTO giao_dich(...)
        GiaoDich gd = new GiaoDich(maTaiKhoan, soTien, "NAP_TIEN");
        giaoDichRepo.save(gd);
    }
}
