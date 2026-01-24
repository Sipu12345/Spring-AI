package com.dibyatech.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.Map;

@Service
public class PythonApiService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String pythonApiUrl = "http://localhost:8000/predict";

    public Map<String, Object> predictCodeSmell(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> payload = Map.of("code", code);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl, request, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();  // Returns full JSON as Map
        }

        return Map.of("error", "No response");
    }
}
