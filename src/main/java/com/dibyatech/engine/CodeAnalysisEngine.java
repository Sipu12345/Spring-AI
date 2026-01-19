package com.dibyatech.engine;

import com.dibyatech.analyzer.JavaCodeAnalyzer;
import com.dibyatech.model.internal.CodeAnalysisResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CodeAnalysisEngine {
    private final JavaCodeAnalyzer analyzer;

    public CodeAnalysisResult analyze(String code) {

        return new CodeAnalysisResult(analyzer.findDeprecatedApis(code),
                analyzer.modernizationOpportunities(code),
                analyzer.calculateComplexity(code),
                analyzer.assessRisk(code));
    }
}
