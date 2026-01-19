package com.dibyatech.engine;

import com.dibyatech.model.internal.CodeValidationResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ValidationEngine {

    public CodeValidationResult validate(String code) {

        // Later: compile checks, formatting, security rules
        boolean valid = !code.contains("Thread.stop");

        return new CodeValidationResult(
                valid,
                valid ? List.of() : List.of("Usage of Thread.stop() detected")
        );
    }
}
