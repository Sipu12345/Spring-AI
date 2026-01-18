package com.dibyatech.controller;

import com.dibyatech.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ChatService chatService;

    @Test
    void shouldReturnChatResponse() throws Exception {
        String prompt = "Hello AI";
        String response = "Hello Human";

        when(chatService.ask(prompt)).thenReturn(response);

        mockMvc.perform(get("/api/ai/chat")
                        .param("prompt", prompt))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }
}
