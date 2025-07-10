package edu.thanglong.domain.repository.nganhang.tinhlai;

import edu.thanglong.domain.model.nganhang.tinhlai.GiaoDich;
import edu.thanglong.domain.model.nganhang.tinhlai.TaiKhoan;
import java.util.List;

public interface TinhLaiRepository {
    List<TaiKhoan> findAllTaiKhoan();
    void saveGiaoDichBatch(List<GiaoDich> danhSachGiaoDich);
    void updateTaiKhoanBatch(List<TaiKhoan> danhSachTaiKhoan);

    
}
