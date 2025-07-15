package edu.thanglong.interfaces.restful.nganhang.tinhlai;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.thanglong.domain.usecase.nganhang.tinhlai.TinhLaiUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TinhLaiController.class)
class TinhLaiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TinhLaiUseCase tinhLaiUseCase;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testTinhLai_ThanhCong() throws Exception {
        Mockito.when(tinhLaiUseCase.tinhLai(any(BigDecimal.class))).thenReturn("Đã tính lãi toàn bộ: 2 tài khoản");
        Map<String, BigDecimal> req = new HashMap<>();
        req.put("tyLe", BigDecimal.valueOf(10));
        mockMvc.perform(post("/api/nganhang/tinhlai")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Đã tính lãi toàn bộ")));
    }

    @Test
    void testTinhLai_ThieuTyLe() throws Exception {
        Map<String, BigDecimal> req = new HashMap<>();
        mockMvc.perform(post("/api/nganhang/tinhlai")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Thiếu tỷ lệ lãi")));
    }
} 