package com.dibyatech.ai;

import org.springframework.stereotype.Component;

@Component
public class LlamaPromptBuilder {
    public String buildModernizationPrompt(String code) {
        return """
        You are a senior Java language expert.

        Strict rules (must follow):
        - Do NOT introduce Lombok or any third-party libraries
        - Do NOT change class or method names
        - Do NOT change public APIs
        - Preserve existing business logic exactly
        - Replace deprecated Java 8 APIs with Java 17 equivalents only
        - Prefer java.time over java.util.Date when applicable
        - Use records ONLY if the class is a pure data carrier
        - Do NOT add explanations or comments
        - Do NOT use markdown formatting

        Task:
        Convert the following Java 8 code to Java 17 compatible code.

        Java code:
        %s

        Return ONLY valid Java source code.
        """.formatted(code);
    }

    public String buildExplanationPrompt(String original, String modernized) {
        return """
        You are a senior Java engineer.

        Task:
        - Explain what the following Java code does
        - Do NOT suggest refactoring
        - Do NOT modify the code
        - Do NOT add new features
        - Explain in simple, clear bullet points
        - Assume the reader is a mid-level Java developer
        - Original Code is for lower Java version and Modernized Code for upgraded Java Version

        Original code:
        %s

        Modernized code:
        %s

        Output format:
        - Purpose of the class
        - Key logic explained step-by-step
        - Any deprecated APIs used (if applicable)
        - Provide reference links for Modernized Code related if available

        Do NOT include code blocks.
        """.formatted(original, modernized);
    }

}
