package com.dibyatech.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private ChatClient chatClient; // this will be returned by builder

    @Mock
    private ChatClient.Builder chatClientBuilder;

    private ChatService chatService;

    @BeforeEach
    void setUp() {
        // Make sure builder.build() returns the mocked ChatClient
        when(chatClientBuilder.build()).thenReturn(chatClient);

        // Inject mocked builder into ChatService
        chatService = new ChatService(chatClientBuilder);
    }

    @Test
    void shouldReturnResponseFromChatClient() {
        // given
        String prompt = "Hello AI";
        String response = "Hello Human";

        // Fluent API mock
        when(chatClient.prompt(prompt).call().content()).thenReturn(response);

        // when
        String result = chatService.ask(prompt);

        // then
        assertThat(result).isEqualTo(response);

        verify(chatClientBuilder).build();
        verify(chatClient,times(2)).prompt(prompt);
    }
}
