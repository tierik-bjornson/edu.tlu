package edu.thanglong.interfaces.restful.nganhang.chuyentien;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.thanglong.domain.model.nganhang.chuyentien.ChuyenTien;
import edu.thanglong.domain.model.nganhang.chuyentien.TransferResponse;
import edu.thanglong.domain.usecase.nganhang.chuyentien.ChuyenTienUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChuyenTienController.class)
class ChuyenTienControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ChuyenTienUseCase chuyenTienUseCase;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testTransfer_Post() throws Exception {
        Mockito.when(chuyenTienUseCase.execute(anyLong(), anyLong(), any(BigDecimal.class)))
                .thenReturn("Chuyển tiền thành công");
        ChuyenTien req = new ChuyenTien(1L, 2L, BigDecimal.valueOf(1000));
        mockMvc.perform(post("/api/nganhang/chuyentien")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Chuyển tiền thành công"));
    }

    @Test
    void testTransfer_Get() throws Exception {
        Mockito.when(chuyenTienUseCase.execute(anyLong(), anyLong(), any(BigDecimal.class)))
                .thenReturn("Chuyển tiền thành công");
        mockMvc.perform(get("/api/nganhang/chuyentien"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Chuyển tiền thành công"));
    }
} 