package com.dibyatech.model;

import jakarta.validation.constraints.NotBlank;

public record CodeValidationRequest(@NotBlank String code) {}
