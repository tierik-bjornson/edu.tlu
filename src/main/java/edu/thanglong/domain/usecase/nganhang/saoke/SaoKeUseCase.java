package edu.thanglong.domain.usecase.nganhang.saoke;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.thanglong.domain.model.nganhang.saoke.GiaoDich;
import edu.thanglong.domain.repository.nganhang.saoke.SaoKeRepository;

@Service
public class SaoKeUseCase {

    private final SaoKeRepository repository;

    public SaoKeUseCase(SaoKeRepository repository) {
        this.repository = repository;
    }

    public List<GiaoDich> inSaoKeThang(long taiKhoan, int thang, int nam) {
        return repository.laySaoKeTheoThang(taiKhoan, thang, nam);
    }
}
