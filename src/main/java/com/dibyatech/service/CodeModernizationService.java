package com.dibyatech.service;

import com.dibyatech.model.CodeAnalysisRequest;
import com.dibyatech.model.CodeAnalysisResponse;
import com.dibyatech.model.CodeValidationRequest;
import com.dibyatech.model.CodeValidationResponse;
import com.dibyatech.model.JavaModernizationRequest;
import com.dibyatech.model.JavaModernizationResponse;
import com.dibyatech.model.ModernizationExplanationRequest;
import com.dibyatech.model.ModernizationExplanationResponse;


public interface CodeModernizationService {

    JavaModernizationResponse modernize(JavaModernizationRequest request);
    CodeAnalysisResponse analyze(CodeAnalysisRequest request);
    ModernizationExplanationResponse explain(ModernizationExplanationRequest request);
    CodeValidationResponse validate(CodeValidationRequest request);
}
