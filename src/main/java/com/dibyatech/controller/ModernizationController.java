package com.dibyatech.controller;

import com.dibyatech.model.CodeAnalysisRequest;
import com.dibyatech.model.CodeAnalysisResponse;
import com.dibyatech.model.CodeValidationRequest;
import com.dibyatech.model.CodeValidationResponse;
import com.dibyatech.model.HealthResponse;
import com.dibyatech.model.JavaModernizationRequest;
import com.dibyatech.model.JavaModernizationResponse;
import com.dibyatech.model.ModernizationExplanationRequest;
import com.dibyatech.model.ModernizationExplanationResponse;
import com.dibyatech.service.CodeModernizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/modernizer")
@AllArgsConstructor
public class ModernizationController {

    private final CodeModernizationService codeModernizationService;

    /**
     * Modernize Java 8 code to Java 17+
     */
    @PostMapping("/java")
    public ResponseEntity<JavaModernizationResponse> modernizeJavaCode(@Valid @RequestBody JavaModernizationRequest request){
        JavaModernizationResponse response =
                codeModernizationService.modernize(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Analyze legacy Java code without modifying it
     */
    @PostMapping("/analyze")
    public ResponseEntity<CodeAnalysisResponse> analyzeCode(
            @Valid @RequestBody CodeAnalysisRequest request) {

        CodeAnalysisResponse response =
                codeModernizationService.analyze(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Explain modernization changes in human-readable form
     */
    @PostMapping("/explain")
    public ResponseEntity<ModernizationExplanationResponse> explainChanges(
            @Valid @RequestBody ModernizationExplanationRequest request) {

        ModernizationExplanationResponse response =
                codeModernizationService.explain(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Validate Java source code
     */
    @PostMapping("/validate")
    public ResponseEntity<CodeValidationResponse> validateCode(
            @Valid @RequestBody CodeValidationRequest request) {

        CodeValidationResponse response =
                codeModernizationService.validate(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Health check
     */
    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(
                new HealthResponse("UP", "llama2", "JAVA_17")
        );
    }

}
