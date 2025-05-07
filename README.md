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
