package com.dibyatech.model;

import jakarta.validation.constraints.NotBlank;

public record CodeAnalysisRequest(@NotBlank String code) {}
