package com.dibyatech.model;

import jakarta.validation.constraints.NotBlank;

public record ModernizationExplanationRequest(
        @NotBlank String originalCode,
        @NotBlank String modernizedCode
) {}
