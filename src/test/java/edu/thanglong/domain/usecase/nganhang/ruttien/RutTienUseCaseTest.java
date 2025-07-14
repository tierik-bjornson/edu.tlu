package edu.thanglong.domain.usecase.nganhang.ruttien;

import edu.thanglong.domain.model.nganhang.ruttien.GiaoDich;
import edu.thanglong.domain.repository.nganhang.ruttien.GiaoDichRepository;
import edu.thanglong.domain.repository.nganhang.ruttien.TaiKhoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class RutTienUseCaseTest {
    private TaiKhoanRepository taiKhoanRepository;
    private GiaoDichRepository giaoDichRepository;
    private RutTienUseCase useCase;

    @BeforeEach
    void setUp() {
        taiKhoanRepository = mock(TaiKhoanRepository.class);
        giaoDichRepository = mock(GiaoDichRepository.class);
        useCase = new RutTienUseCase(taiKhoanRepository, giaoDichRepository);
    }

    @Test
    void testThucHienRutTien() {
        useCase.thucHienRutTien(1L, 100.0);
        verify(taiKhoanRepository).truTien(1L, 100.0);
        verify(giaoDichRepository).luuGiaoDich(any(GiaoDich.class));
    }
} 