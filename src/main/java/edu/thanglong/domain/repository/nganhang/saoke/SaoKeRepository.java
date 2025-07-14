package edu.thanglong.domain.repository.nganhang.saoke;

import java.util.List;

import edu.thanglong.domain.model.nganhang.saoke.GiaoDich;

public interface SaoKeRepository {
    List<GiaoDich> laySaoKeTheoThang(long taiKhoan, int thang, int nam);
}
