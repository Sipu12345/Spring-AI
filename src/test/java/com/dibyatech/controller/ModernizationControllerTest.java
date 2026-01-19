package com.dibyatech.controller;

import com.dibyatech.model.CodeChange;
import com.dibyatech.model.JavaModernizationResponse;
import com.dibyatech.service.CodeModernizationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.dibyatech.model.ChangeType.SYNTAX_UPGRADE;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ModernizationController.class)
class ModernizationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CodeModernizationService modernizationService;



    @Test
    void testModernizeEndpoint() throws Exception {
        String codeSnippet = "Date d = new Date();";
        String modernizedCode = "LocalDate d = LocalDate.now();";

        JavaModernizationResponse mockResponse = new JavaModernizationResponse(
                modernizedCode,
                "Replaced Date with LocalDate",
                List.of(new CodeChange(SYNTAX_UPGRADE, "Replaced Date usage")),
                List.of("Manual review recommended")
        );

        when(modernizationService.modernize(any())).thenReturn(mockResponse);

        String requestJson = """
        {
            "sourceVersion": "8",
            "targetVersion": "17",
            "code": "%s",
            "options": null
        }
        """.formatted(codeSnippet);

        mockMvc.perform(post("/api/v1/modernizer/java")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modernizedCode").value(modernizedCode))
                .andExpect(jsonPath("$.summary").value("Replaced Date with LocalDate"));
    }

}