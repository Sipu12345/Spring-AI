package com.dibyatech.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record JavaModernizationRequest (
    @NotNull String sourceVersion,
    @NotNull String targetVersion,
    @NotBlank(message = "Source code must not be empty")
    String code,
    ModernizationOptions options
)
{}
