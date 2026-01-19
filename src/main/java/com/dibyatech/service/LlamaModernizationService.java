package com.dibyatech.service;

import com.dibyatech.ai.LlamaPromptBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LlamaModernizationService {
    private final OllamaChatModel chatModel;
    private final LlamaPromptBuilder promptBuilder;

    public String modernizeCode(String legacyCode) {

        return callLlamaModel(promptBuilder.buildModernizationPrompt(legacyCode));
    }

    public String explainChanges(String oldCode, String newCode) {

      return  callLlamaModel(promptBuilder.buildExplanationPrompt(oldCode, newCode));
    }

    private String callLlamaModel(String prompt){
        return chatModel.call(new Prompt(prompt, OllamaChatOptions.builder().model(OllamaModel.LLAMA2).build()))
                .getResult().getOutput().getText();
    }
}