package com.dibyatech.model.internal;

import java.util.List;

public record CodeValidationResult(
        boolean valid,
        List<String> errors) {
}
