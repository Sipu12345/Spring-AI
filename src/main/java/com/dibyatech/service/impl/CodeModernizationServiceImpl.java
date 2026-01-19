package com.dibyatech.service.impl;

import com.dibyatech.engine.CodeAnalysisEngine;
import com.dibyatech.engine.ExplanationEngine;
import com.dibyatech.engine.ValidationEngine;
import com.dibyatech.model.*;
import com.dibyatech.model.internal.CodeAnalysisResult;
import com.dibyatech.model.internal.CodeValidationResult;
import com.dibyatech.model.internal.ModernizationExplanation;
import com.dibyatech.model.internal.ModernizationResult;
import com.dibyatech.service.CodeModernizationService;
import com.dibyatech.engine.ModernizationEngine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CodeModernizationServiceImpl implements CodeModernizationService {
    private final ModernizationEngine modernizationEngine;
    private final CodeAnalysisEngine codeAnalysisEngine;
    private final ExplanationEngine explanationEngine;
    private final ValidationEngine validationEngine;

    @Override
    public JavaModernizationResponse modernize(JavaModernizationRequest request) {

        log.info("Request sent to Model");
        ModernizationResult result = modernizationEngine.modernize(request.code());
        log.info("Response received");

        return new JavaModernizationResponse(
                result.modernizedCode(),
                result.explanation(),
                result.suggestions().stream()
                        .map(s -> new CodeChange(ChangeType.SYNTAX_UPGRADE, s))
                        .toList(),
                List.of("Manual review recommended")
        );
    }

    @Override
    public CodeAnalysisResponse analyze(CodeAnalysisRequest request) {
        CodeAnalysisResult result =
                codeAnalysisEngine.analyze(request.code());

        return new CodeAnalysisResponse(
                result.deprecatedApis(),
                result.suggestions(),
                result.complexityScore(),
                result.riskLevel()
        );
    }

    @Override
    public ModernizationExplanationResponse explain(ModernizationExplanationRequest request) {
        ModernizationExplanation explanation = explanationEngine.explain(
                request.originalCode(),
                request.modernizedCode()
        );

        return new ModernizationExplanationResponse(
                List.of(explanation.explanation()),
                explanation.references()
        );
    }

    @Override
    public CodeValidationResponse validate(CodeValidationRequest request) {
        CodeValidationResult result = validationEngine.validate(request.code());

        return new CodeValidationResponse(
                result.valid(),
                result.errors()
        );
    }


}
