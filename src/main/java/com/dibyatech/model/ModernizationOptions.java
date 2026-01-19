package com.dibyatech.model;

public record ModernizationOptions(
    boolean removeDeprecatedApis,
    boolean useRecords,
    boolean useSwitchExpressions,
    boolean enablePatternMatching
){}
