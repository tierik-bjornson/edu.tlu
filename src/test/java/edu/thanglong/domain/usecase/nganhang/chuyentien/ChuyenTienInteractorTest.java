package edu.thanglong.domain.usecase.nganhang.chuyentien;

import edu.thanglong.domain.model.nganhang.chuyentien.TaiKhoan;
import edu.thanglong.domain.model.nganhang.chuyentien.GiaoDich;
import edu.thanglong.domain.repository.nganhang.chuyentien.ChuyenTienRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChuyenTienInteractorTest {
    private ChuyenTienRepository repo;
    private ChuyenTienInteractor interactor;

    @BeforeEach
    void setUp() {
        repo = mock(ChuyenTienRepository.class);
        interactor = new ChuyenTienInteractor(repo);
    }

    @Test
    void testExecute_ChuyenTienThanhCong() {
        TaiKhoan from = mock(TaiKhoan.class);
        TaiKhoan to = mock(TaiKhoan.class);
        when(repo.findById(1L)).thenReturn(Optional.of(from));
        when(repo.findById(2L)).thenReturn(Optional.of(to));
        BigDecimal soTien = BigDecimal.valueOf(100);

        String result = interactor.execute(1L, 2L, soTien);

        verify(from).rutTien(soTien);
        verify(to).napTien(soTien);
        verify(repo).update(from);
        verify(repo).update(to);
        verify(repo).saveGiaoDich(any(GiaoDich.class));
        assertEquals("Chuyển tiền thành công", result);
    }

    @Test
    void testExecute_TaiKhoanNguonKhongTonTai() {
        when(repo.findById(1L)).thenReturn(Optional.empty());
        String result = interactor.execute(1L, 2L, BigDecimal.TEN);
        assertEquals("Tài khoản nguồn không tồn tại", result);
    }

    @Test
    void testExecute_TaiKhoanDichKhongTonTai() {
        TaiKhoan from = mock(TaiKhoan.class);
        when(repo.findById(1L)).thenReturn(Optional.of(from));
        when(repo.findById(2L)).thenReturn(Optional.empty());
        String result = interactor.execute(1L, 2L, BigDecimal.TEN);
        assertEquals("Tài khoản đích không tồn tại", result);
    }
} 