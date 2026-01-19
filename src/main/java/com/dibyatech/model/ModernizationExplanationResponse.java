package com.dibyatech.model;

import java.util.List;

public record ModernizationExplanationResponse(
        List<String> explanation,
        List<String> learningLinks
) {}
