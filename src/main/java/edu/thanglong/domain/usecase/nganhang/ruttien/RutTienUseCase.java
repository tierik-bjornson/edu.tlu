package edu.thanglong.domain.usecase.nganhang.ruttien;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.thanglong.domain.model.nganhang.ruttien.GiaoDich;
import edu.thanglong.domain.repository.nganhang.ruttien.GiaoDichRepository;
import edu.thanglong.domain.repository.nganhang.ruttien.TaiKhoanRepository;

@Service
public class RutTienUseCase {

    private final TaiKhoanRepository taiKhoanRepository;
    private final GiaoDichRepository giaoDichRepository;

    public RutTienUseCase(TaiKhoanRepository taiKhoanRepository, GiaoDichRepository giaoDichRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.giaoDichRepository = giaoDichRepository;
    }

    @Transactional
    public void thucHienRutTien(Long maTaiKhoan, BigDecimal soTien) {
        try {
            System.out.println("➡️ Thực hiện rút tiền: tài khoản = " + maTaiKhoan + ", số tiền = " + soTien);

            taiKhoanRepository.truTien(maTaiKhoan, soTien);

            GiaoDich giaoDich = new GiaoDich();
            giaoDich.setMaTaiKhoan(maTaiKhoan);
            giaoDich.setSoTien(soTien);
            giaoDich.setLoaiGiaoDich("RUT_TIEN");

            giaoDichRepository.luuGiaoDich(giaoDich);

            System.out.println("✅ Rút tiền thành công");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Lỗi khi rút tiền: " + e.getMessage(), e);
        }
        
    }

}
