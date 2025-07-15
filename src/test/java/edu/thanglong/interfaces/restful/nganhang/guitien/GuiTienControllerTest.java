package edu.thanglong.interfaces.restful.nganhang.guitien;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.thanglong.domain.usecase.nganhang.guitien.GuiTienUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.HashMap;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GuiTienController.class)
class GuiTienControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GuiTienUseCase guiTienUseCase;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGuiTien() throws Exception {
        GuiTienController.GuiTienRequest req = new GuiTienController.GuiTienRequest();
        req.setMaTaiKhoan("123");
        req.setSoTien(BigDecimal.valueOf(500));
        Mockito.doNothing().when(guiTienUseCase).execute(anyString(), any(BigDecimal.class));
        mockMvc.perform(post("/api/nganhang/guitien")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Gửi tiền thành công"))
                .andExpect(jsonPath("$.maTaiKhoan").value("123"))
                .andExpect(jsonPath("$.soTien").value("500"));
    }
} 