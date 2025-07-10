package edu.thanglong.domain.usecase.nganhang.tinhlai;

import edu.thanglong.domain.model.nganhang.tinhlai.TaiKhoan;
import edu.thanglong.domain.model.nganhang.tinhlai.GiaoDich;
import edu.thanglong.domain.repository.nganhang.tinhlai.TinhLaiRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class TinhLaiUseCase {
    private final TinhLaiRepository repository;

    public TinhLaiUseCase(TinhLaiRepository repository) {
        this.repository = repository;
    }

    
    public String tinhLai(BigDecimal tyLe) {
        List<TaiKhoan> all = repository.findAllTaiKhoan();
        List<GiaoDich> dsGD = new ArrayList<>();

        for (TaiKhoan tk : all) {
            BigDecimal lai = tk.getSoDu().multiply(tyLe).divide(BigDecimal.valueOf(100));
            tk.setSoDu(tk.getSoDu().add(lai));
            dsGD.add(GiaoDich.fromLaiSuat(tk.getMaTaiKhoan(), lai));
        }

        // ✅ Batch update
        repository.updateTaiKhoanBatch(all);
        repository.saveGiaoDichBatch(dsGD);

        return "Đã tính lãi toàn bộ: " + all.size() + " tài khoản";
    }

    

}