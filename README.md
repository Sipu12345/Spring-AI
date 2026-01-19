# Java AI Legacy Code Modernizer

[![Java](https://img.shields.io/badge/Java-17+-blue)](https://www.oracle.com/java/)  
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.9-green)](https://spring.io/projects/spring-boot)  
[![Ollama](https://img.shields.io/badge/Ollama-LLM-orange)](https://ollama.com/)

---

## Overview

**Java AI Legacy Code Modernizer** is a Spring Boot application that leverages **LLaMA models via Ollama** to automatically modernize legacy Java 8 code to Java 17+.

The tool helps developers:

- Convert old APIs to modern equivalents (`Date` → `LocalDate`)
- Transform loops into **Stream API**
- Suggest **records** for immutable classes
- Suggest **pattern matching** for `instanceof`
- Analyze code complexity and risk
- Provide AI-generated explanations for modernization changes

This is particularly useful for teams maintaining large legacy Java codebases and aiming to adopt modern Java best practices.

---

## Features

- **Java 8 → 17 modernization** with AI assistance
- **Static code analysis** to detect deprecated APIs and modernization opportunities
- **Cyclomatic complexity** calculation
- **Risk assessment** for risky constructs (`synchronized`, `Thread.stop()`)
- **Global REST API** for integration with CI/CD pipelines or IDE plugins
- **Swagger/OpenAPI** support for interactive API documentation
- **Docker + Ollama integration** for running LLaMA models locally
- **Detailed explanations** for all modernization changes with links to learning resources

---

## REST APIs

Base URL: `/api/v1/modernizer`

| Endpoint | Method | Request | Response | Description |
|----------|--------|---------|----------|-------------|
| `/java` | POST | `JavaModernizationRequest` | `JavaModernizationResponse` | Modernize legacy Java code |
| `/analyze` | POST | `CodeAnalysisRequest` | `CodeAnalysisResponse` | Analyze code for deprecated APIs, loops, and modernization opportunities |
| `/explain` | POST | `ModernizationExplanationRequest` | `ModernizationExplanationResponse` | Generate human-readable explanation of modernization changes |
| `/validate` | POST | `CodeValidationRequest` | `CodeValidationResponse` | Validate Java source code for compilation and structural correctness |
| `/health` | GET | - | `HealthResponse` | Check service status and AI model availability |

---

## Technologies Used

- **Java 17**
- **Spring Boot 3.5**
- **Spring AI** for LLaMA/Ollama integration
- **JavaParser** for static code analysis
- **JUnit 5** + **Mockito** for unit and integration testing
- **Swagger/OpenAPI** for API documentation
- **Docker** for running local Ollama LLaMA models

---

## Setup & Installation

### Prerequisites

- Java 17+
- Maven/Gradle
- Ollama installed locally ([https://ollama.com/](https
