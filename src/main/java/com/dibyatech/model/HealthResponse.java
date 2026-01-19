package com.dibyatech.model;

public record HealthResponse(
        String status,
        String model,
        String javaTarget
) {}
