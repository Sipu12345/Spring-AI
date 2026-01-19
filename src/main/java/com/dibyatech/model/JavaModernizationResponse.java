package com.dibyatech.model;

import java.util.List;


public record JavaModernizationResponse(
     String modernizedCode,
     String summary,
     List<CodeChange> changes,
     List<String> warnings
){}
