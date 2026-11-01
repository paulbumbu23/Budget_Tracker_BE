# 📊 Budget Tracker API - Backend

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

A robust, portfolio-ready RESTful API built with **Spring Boot 3** for managing personal finances. This backend system allows users to track their incomes and expenses, automatically generates monthly financial reports sent via email, and provides real-time multi-currency conversions.

---

## ✨ Key Features

* **🔒 Secure Authentication:** Stateless JWT (JSON Web Token) authentication with Spring Security. Role-based access and secure password hashing (BCrypt).
* **💰 Transaction Management:** Full CRUD operations for incomes and expenses.
* **🌍 Multi-Currency Support:** Real-time exchange rate integration via *Open Exchange Rates API* to display balances in RON, EUR, and USD.
* **📧 Automated Monthly Reports:** A Spring `@Scheduled` cron job automatically aggregates monthly data and emails personalized financial summaries to users.
* **🗑️ Account Lifecycle:** Secure account deletion with cascading cleanup of all associated user data.
* **📖 Interactive Documentation:** Auto-generated API documentation using OpenAPI 3 (Swagger UI).
* **🌐 CORS Configured:** Ready to be consumed by modern frontend frameworks (React, Angular, Vue).

---

## 📸 API Documentation (Swagger)

The API is fully documented and testable via Swagger UI.

<img width="1920" height="1020" alt="Screenshot 2026-11-01 180852" src="https://github.com/user-attachments/assets/71e6f639-6c10-4944-97d4-0165e91288fb" />
<img width="1920" height="1020" alt="Screenshot 2026-11-01 180908" src="https://github.com/user-attachments/assets/a698aad5-70bf-4ebd-9d0d-c0d8cda6ccf7" />
<img width="1920" height="1020" alt="Screenshot 2026-11-01 180934" src="https://github.com/user-attachments/assets/729dcdf2-ec94-4e6d-a8a7-df19e931ebea" />
<img width="1920" height="1020" alt="Screenshot 2026-11-01 181009" src="https://github.com/user-attachments/assets/5336728e-e8c7-4361-8d9a-7ddf49474b37" />
<img width="1170" height="1183" alt="WhatsApp Image 2026-11-01 at 18 14 06" src="https://github.com/user-attachments/assets/72447739-aa86-4a56-842e-ded163d76289" />


---

## 🛠️ Tech Stack

* **Core:** Java 17+, Spring Boot 3
* **Security:** Spring Security, JWT (JSON Web Tokens)
* **Database:** MySQL, Spring Data JPA / Hibernate
* **API Documentation:** Springdoc OpenAPI (Swagger)
* **External APIs:** Open Exchange Rates API
* **Email Service:** Spring Boot Starter Mail

---

## 🚀 Getting Started

### Prerequisites
* Java 17 or higher
* MySQL Server (running on default port 3306)
* Maven

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/budget-tracker-api.git
   cd budget-tracker-api
   ```

2. **Configure the Database and APIs:**
   Open `src/main/resources/application.properties` and update the following values:
   ```properties
   # MySQL Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/budget_db_name
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   
   # JWT Secret (Use a strong, 256-bit secure key)
   jwt.secret=your_super_secret_jwt_key
   
   # Email Configuration (e.g., Gmail App Password)
   spring.mail.username=your_email@gmail.com
   spring.mail.password=your_app_password
   
   # Open Exchange Rates API
   exchange.api.key=your_exchange_api_key
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API Documentation:**
   Open your browser and navigate to: `http://localhost:8080/swagger-ui.html`

---

## 🏗️ Architecture

The project follows a clean, layered architecture:
* **Controllers:** Handle incoming HTTP requests, validate inputs, and return appropriate JSON responses.
* **Services:** Contain the core business logic (e.g., calculating currency conversions, generating reports).
* **Repositories:** Manage database interactions using Spring Data JPA.
* **Security:** Custom filters for intercepting requests, parsing JWTs, and managing the Security Context.
* **DTOs:** Data Transfer Objects to ensure sensitive entity data (like passwords) is never exposed to the client.

---
*Developed as a portfolio project to demonstrate backend engineering, security practices, and third-party API integration.*
