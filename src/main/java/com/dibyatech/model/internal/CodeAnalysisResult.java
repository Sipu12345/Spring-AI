package com.dibyatech.model.internal;

import com.dibyatech.model.RiskLevel;

import java.util.List;


public record CodeAnalysisResult(
        List<String> deprecatedApis,
        List<String> suggestions,
        int complexityScore,
        RiskLevel riskLevel
) {
}
