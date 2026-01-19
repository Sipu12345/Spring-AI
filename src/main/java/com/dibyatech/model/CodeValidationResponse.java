package com.dibyatech.model;

import java.util.List;

public record CodeValidationResponse(
        boolean valid,
        List<String> errors
) {}
