package edu.thanglong.domain.usecase.nganhang.guitien;

import edu.thanglong.domain.model.nganhang.guitien.TaiKhoan;
import edu.thanglong.domain.model.nganhang.guitien.GiaoDich;
import edu.thanglong.domain.repository.nganhang.guitien.TaiKhoanRepository;
import edu.thanglong.domain.repository.nganhang.guitien.GiaoDichRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GuiTienUseCaseTest {
    private TaiKhoanRepository taiKhoanRepo;
    private GiaoDichRepository giaoDichRepo;
    private GuiTienUseCase useCase;

    @BeforeEach
    void setUp() {
        taiKhoanRepo = mock(TaiKhoanRepository.class);
        giaoDichRepo = mock(GiaoDichRepository.class);
        useCase = new GuiTienUseCase(taiKhoanRepo, giaoDichRepo);
    }

    @Test
    void testExecute_GuiTienThanhCong() {
        TaiKhoan tk = mock(TaiKhoan.class);
        when(taiKhoanRepo.findByMaTaiKhoan("123")).thenReturn(Optional.of(tk));
        BigDecimal soTien = BigDecimal.valueOf(500);
        useCase.execute("123", soTien);
        verify(tk).napTien(soTien);
        verify(taiKhoanRepo).update(tk);
        verify(giaoDichRepo).save(any(GiaoDich.class));
    }

    @Test
    void testExecute_TaiKhoanKhongTonTai() {
        when(taiKhoanRepo.findByMaTaiKhoan("999")).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> useCase.execute("999", BigDecimal.TEN));
    }
} 