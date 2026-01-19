package com.dibyatech.engine;

import com.dibyatech.analyzer.JavaCodeAnalyzer;
import com.dibyatech.model.internal.ModernizationResult;
import com.dibyatech.service.LlamaModernizationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ModernizationEngine {
    private final JavaCodeAnalyzer analyzer;
    private final LlamaModernizationService llamaService;

    public ModernizationResult modernize(String code) {

        var deprecatedApis = analyzer.findDeprecatedApis(code);
        var suggestions = analyzer.modernizationOpportunities(code);

        String modernizedCode = llamaService.modernizeCode(code);
        String explanation = llamaService.explainChanges(code, modernizedCode);

        return new ModernizationResult(
                code,
                modernizedCode,
                deprecatedApis,
                suggestions,
                explanation);
    }
}
