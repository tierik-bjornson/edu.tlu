package edu.thanglong.domain.usecase.nganhang.laysodu;

import edu.thanglong.domain.repository.nganhang.laysodu.LaySoDuRepository;
import org.springframework.stereotype.Service;

@Service
public class LaySoDuUseCase {

    private final LaySoDuRepository repository;

    public LaySoDuUseCase(LaySoDuRepository repository) {
        this.repository = repository;
    }

    public Double execute(Long maTaiKhoan) {
        return repository.laySoDu(maTaiKhoan);
    }
}
