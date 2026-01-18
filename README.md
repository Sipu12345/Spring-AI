# Spring AI

This project is a **Spring Boot application** demonstrating the use of **Spring AI** to build AI-powered features in Java.
It serves as a simple reference for integrating AI models into a modern Spring-based backend application.

---

## 🚀 Features

* Spring Boot–based application
* Integration with Spring AI
* REST API support
* Clean and extensible project structure
* Ready for experimentation and extension

---

## 🛠️ Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring AI**
* **Gradle**
* RESTful APIs

---

## 📦 Prerequisites

* Java 17 or higher
* Gradle installed (or use Gradle Wrapper)
* API credentials for your chosen AI provider (configured via environment variables)

---

## ⚙️ Configuration

Set required environment variables before running the application.

Example:

```bash
export AI_API_KEY=your-api-key
```

(Exact variables depend on the AI provider you integrate.)

---

## ▶️ Running the Application

```bash
./gradlew bootRun
```

The application will start on:

```
http://localhost:8080
```

---

## 📁 Project Structure

```
src/
 └── main/
     ├── java/
     └── resources/
 └── test/
```

---

## 🧪 Testing

```bash
./gradlew test
```

---

## 📌 Notes

* This project is intended for learning and experimentation.
* Extend it by adding vector stores, embeddings, or additional AI models.

---

## 📄 License

This project is licensed under the MIT License.
