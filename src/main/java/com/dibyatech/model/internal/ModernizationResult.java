package com.dibyatech.model.internal;

import java.util.List;

public record ModernizationResult(
        String originalCode,
        String modernizedCode,
        List<String> deprecatedApis,
        List<String> suggestions,
        String explanation
) {
}
