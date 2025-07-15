package edu.thanglong.interfaces.restful.nganhang.ruttien;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.thanglong.domain.model.nganhang.ruttien.RutTienRequest;
import edu.thanglong.domain.usecase.nganhang.ruttien.RutTienUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RutTienController.class)
class RutTienControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RutTienUseCase rutTienUseCase;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRutTien() throws Exception {
        RutTienRequest req = new RutTienRequest(1L, 100.0);
        Mockito.doNothing().when(rutTienUseCase).thucHienRutTien(anyLong(), anyDouble());
        mockMvc.perform(post("/api/nganhang/ruttien")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Rút tiền thành công")));
    }
} 