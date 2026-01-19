package com.dibyatech.model;

import java.util.List;

public record CodeAnalysisResponse(
        List<String> deprecatedApis,
        List<String> java17Opportunities,
        int complexityScore,
        RiskLevel riskLevel
) {}
