package com.dibyatech.engine;

import com.dibyatech.model.internal.ModernizationExplanation;
import com.dibyatech.service.LlamaModernizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class ExplanationEngine {
    private final LlamaModernizationService llamaService;

    public ModernizationExplanation explain(String oldCode, String newCode) {

        String explanation =
                llamaService.explainChanges(oldCode, newCode);

        return new ModernizationExplanation(
                explanation,
                extractUrls(explanation)
        );
    }

    public static List<String> extractUrls(String text) {
        List<String> urls = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            return urls;
        }

        // Regex pattern for URLs
        String regex = "\\b((?:https?|ftp)://[\\w\\-?=&./%+#]+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            urls.add(matcher.group(1));
        }

        return urls;
    }
}
