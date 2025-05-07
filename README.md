

## 🛠️ Local Setup Instructions

Follow the steps below to set up and run the **Loan Management System** locally.

### ✅ Prerequisites

Ensure the following are installed on your system:

- Java 17+
- Maven 3.6+
- MySQL Server
- Postman (optional, for API testing)
- IntelliJ IDEA / Eclipse / any Java IDE
- Git

### 📦 Clone the Repository

```bash
git clone https://github.com/RupendraJaiswal/loan-management-system-.git
cd loan-management-system-
```

### ⚙️ Database Setup

1. Start your MySQL server.
2. Create a new database:
   ```sql
   CREATE DATABASE loan_management_system;
   ```
3. Update `application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/lms
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```
4. import the database.

### 🚀 Run the Application

You can run the application in two ways:

#### Using Maven:

```bash
mvn spring-boot:run
```

#### Or from your IDE:

- Open the project.
- Run `LoanManagementSystemApplication.java` as a Spring Boot application.

### 🔐 Authentication

This project uses **JWT-based authentication**.

- Use `/api/auth/signin` to generate token.
- Include the token as a Bearer token in the `Authorization` header for subsequent requests.

### 📬 Email Configuration (Optional for Overdue Reminder)

To enable email sending:
1. Set up a Gmail App Password or SMTP server.
2. Update your `application.properties`:
   ```properties
   spring.mail.username=your_email@gmail.com
   spring.mail.password=your_app_password
   ```

### 📫 API Testing

Use Postman or any REST client.

- Import the Postman collection (if provided) or manually hit endpoints.
- Use the JWT token for protected endpoints.

### ✅ Verify Features

- Customer registration/login
- Apply for loan
- Admin approval
- Repayment handling
- Loan status tracking
- Overdue reminder via email

---



# 💰 Loan Management System – Backend

## 🧭 Flowchart

```
                    +------------------+
                    |   User Login     |
                    |   (JWT Auth)     |
                    +--------+---------+
                             |
                             v
                 +-----------+-------------+
                 |         Customer         |
                 +-----------+-------------+
                             |
     +-----------------------+-------------------------+
     |                       |                         |
     v                       v                         v
+------------+       +----------------+        +----------------+
| Apply Loan |       | View Loan List |        | Make Repayment |
+------------+       +----------------+        +----------------+
       |                      |                         |
       v                      v                         v
+--------------+     +-------------------+     +-----------------------+
|  Save Loan   |<--->| View Loan Details |     | Update Loan Balance   |
|  (PENDING)   |     | (Status, EMI, etc)|     | and Repayment History |
+--------------+     +-------------------+     +-----------------------+
       |                                                    |
       v                                                    v
+----------------+                                   +------------------+
| Admin Approval |                                   | Overdue Checker  |
| (APPROVE/REJECT)|<------------------------------->| Scheduler + Email |
+----------------+                                   +------------------+
       |
       v
+----------------+
| Interest Calc  |
+----------------+
```
---

## 📁 Project Structure & Module Description

### 1. Controller Layer (`controller/`)
Handles incoming HTTP requests and maps them to service methods.
- `LoanController.java`: APIs to apply for loans, get details, and update status.
- `CustomerController.java`: APIs to manage customer details.
- `RepaymentController.java`: APIs to handle EMI payments and histories.

### 2. Model Layer (`model/`)
Defines entity classes mapped to database tables.
- `Loan.java`: Fields like amount, interest, status, remaining balance.
- `Customer.java`: User data including contact and login info.
- `Repayment.java`: Tracks payment date, amount, and status.

### 3. Repository Layer (`repository/`)
Data access layer using Spring Data JPA.
- `LoanRepository.java`
- `CustomerRepository.java`
- `RepaymentRepository.java`

### 4. Service Layer (`service/`)
Contains business logic.
- `LoanService.java`: Loan application, updates, and business rules.
- `CustomerService.java`: Customer registration and updates.
- `RepaymentService.java`: Handles repayments and overdue checking.

### 5. Application Bootstrap
- `LoanManagementApplication.java`: Entry point for the Spring Boot app.

### 6. Resources
- `application.properties`: Configuration file (DB, port, etc).

### 7. Postman Collection
- Provided under `postman collection/` folder for API testing.

### 8. Maven & Config Files
- `pom.xml`: Maven build and dependency configuration.
- `.gitignore`, `.project`, `mvnw`, `mvnw.cmd`: Project & build environment helpers.

---

## 🚀 Features
- JWT-based authentication
- Customer and admin roles
- Loan application & approval system
- Repayment tracking
- Overdue loan checker with email notification
- RESTful APIs for frontend integration

---

## 🛠 Technologies Used
- Java 17
- Spring Boot, Spring Security, Spring Data JPA
- MySQL
- Maven
- Postman (for API testing)

---








# Loan Management System API

A RESTful API for managing loans, customers, repayments, and generating reports. Below is the documentation for available endpoints and usage examples.

## Table of Contents

- [Authentication](#authentication)
- [Customers](#customers)
- [Loans](#loans)
- [Repayments](#repayments)
- [Reports](#reports)
- [Postman Collection](#postman-collection)

---

## Authentication

### Sign Up
Create a new user account.  
**Endpoint:** `POST /api/auth/signup`  
**Request Body:**
```json
{
  "username": "Rupendra",
  "email": "rupendra.demo@example.com",
  "password": "password",
  "role": ["admin"]
}
```

### Sign In
Authenticate and receive a JWT token.  
**Endpoint:** `POST /api/auth/signin`  
**Request Body:**
```json
{
  "username": "Rupendra",
  "password": "password"
}
```

**Response:** Includes a JWT token for authenticated requests.

---

## Customers

### Create Customer  
**Endpoint:** `POST /api/customers`  
**Headers:** `Authorization: Bearer <JWT_TOKEN>`  
**Request Body:**
```json
{
  "name": "Ravi Sharma",
  "email": "ravi.sharma@example.com",
  "contactNumber": "9876543210",
  "address": "Lucknow, Uttar Pradesh"
}
```

### Update Customer  
**Endpoint:** `PUT /api/customers/{customerId}`  
**Headers:** `Authorization: Bearer <JWT_TOKEN>`  
**Request Body:**
```json
{
  "name": "Ankit Kumar Verma",
  "email": "ankit.kumar@example.com",
  "contactNumber": "9876543211",
  "address": "MG Road, Indore"
}
```

### Fetch Customer  
**Endpoint:** `GET /api/customers/{customerId}`  
**Headers:** `Authorization: Bearer <JWT_TOKEN>`

---

## Loans

### Apply for a Loan  
**Endpoint:** `POST /api/loans/apply`  
**Headers:** `Authorization: Bearer <JWT_TOKEN>`  
**Request Body:**
```json
{
  "customerId": 1,
  "amount": 100000,
  "interestRate": 10,
  "durationMonths": 12,
  "purpose": "Home Renovation"
}
```

### Approve Loan  
**Endpoint:** `PUT /api/loans/{loanId}/approve`  
**Headers:** `Authorization: Bearer <JWT_TOKEN>`

### Reject Loan  
**Endpoint:** `PUT /api/loans/{loanId}/reject`  
**Headers:** `Authorization: Bearer <JWT_TOKEN>`

### Fetch Loan Details  
**Endpoint:** `GET /api/loans/{loanId}`  
**Headers:** `Authorization: Bearer <JWT_TOKEN>`

---

## Repayments

### Submit Repayment  
**Endpoint:** `POST /api/repayments`  
**Headers:** `Authorization: Bearer <JWT_TOKEN>`  
**Request Body:**
```json
{
  "amountPaid": 5000,
  "paymentDate": "2025-05-06",
  "paymentMode":"ONLINE",
  "loan": {
    "id": 3
  }
}
```

---

## Reports

### Pending Loans Report  
**Endpoint:** `GET /api/reports/pending`  
**Headers:** `Authorization: Bearer <JWT_TOKEN>`

### Loan History Report  
**Endpoint:** `GET /api/reports/history`

---

## Postman Collection

- Import the provided `Loan Management System API.postman_collection.json` into Postman.
- Update the base URL if the API is hosted elsewhere (default: `http://localhost:8080`).
- Use the **Sign In** endpoint to obtain a JWT token.
- Add the token to subsequent requests under the `Authorization` header as:
  ```
  Bearer <JWT_TOKEN>
  ```

**Note:** Replace `<JWT_TOKEN>` with the token received after signing in. Ensure the server is running locally on port `8080` or adjust the URLs accordingly.

---
