package edu.thanglong.domain.usecase.nganhang.tinhlai;

import edu.thanglong.domain.model.nganhang.tinhlai.TaiKhoan;
import edu.thanglong.domain.model.nganhang.tinhlai.GiaoDich;
import edu.thanglong.domain.repository.nganhang.tinhlai.TinhLaiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TinhLaiUseCaseTest {
    private TinhLaiRepository repository;
    private TinhLaiUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(TinhLaiRepository.class);
        useCase = new TinhLaiUseCase(repository);
    }

    @Test
    void testTinhLai() {
        TaiKhoan tk1 = mock(TaiKhoan.class);
        TaiKhoan tk2 = mock(TaiKhoan.class);
        when(tk1.getSoDu()).thenReturn(BigDecimal.valueOf(1000));
        when(tk2.getSoDu()).thenReturn(BigDecimal.valueOf(2000));
        when(tk1.getMaTaiKhoan()).thenReturn(1L);
        when(tk2.getMaTaiKhoan()).thenReturn(2L);
        List<TaiKhoan> all = Arrays.asList(tk1, tk2);
        when(repository.findAllTaiKhoan()).thenReturn(all);
        String result = useCase.tinhLai(BigDecimal.valueOf(10));
        verify(tk1).setSoDu(any(BigDecimal.class));
        verify(tk2).setSoDu(any(BigDecimal.class));
        verify(repository).updateTaiKhoanBatch(all);
        verify(repository).saveGiaoDichBatch(anyList());
        assertTrue(result.contains("Đã tính lãi toàn bộ"));
    }
} 