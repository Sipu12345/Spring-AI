package com.dibyatech.controller;

import com.dibyatech.service.PythonApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/java-smell")
public class JavaSmellController {
    private final PythonApiService pythonApiService;

    public JavaSmellController(PythonApiService pythonApiService) {
        this.pythonApiService = pythonApiService;
    }

    @PostMapping("/predict")
    public ResponseEntity<Map<String,Object>> predict(@RequestBody String code) {
        Map<String,Object> result = pythonApiService.predictCodeSmell(code);
        return ResponseEntity.ok(result);
    }
}
