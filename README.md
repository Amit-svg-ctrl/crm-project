
# CRM Project

This is a Customer Relationship Management (CRM) project built with **Spring Boot** and uses an **H2 in-memory database**.

## 1. Requirements
To run this project, ensure you have the following installed:
- **Java 17** or higher
- **Maven 3.6+** for building the project
- **Spring Boot 3.3.5** or the version you're using
- **H2 Database** for in-memory data storage

## 2. Build and Run Instructions
Follow these steps to build and run the CRM project:

### Step 1: Clone the repository
Clone the project repository from GitHub:
```bash
git clone <repository_url>
```

### Step 2: Build the project
Navigate to the project directory and use Maven to build the project:
```bash
mvn clean install
```

### Step 3: Run the application
Once the project is built, you can run the application using:
```bash
mvn spring-boot:run
```
By default, the application will run on `http://localhost:8080`.

## 3. Sample API Requests
Here are the sample API requests to test each endpoint:

### 1. **Create a new customer** (POST request)
```bash
POST http://localhost:8080/customer
```
**Request Body**:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "annualSpend": 10000
}
```

### 2. **Get a customer by ID** (GET request)
```bash
GET http://localhost:8080/customer/{id}
```
Replace `{id}` with the actual UUID of the customer.

### 3. **Get customers by name or email** (GET request)
```bash
GET http://localhost:8080/customers?name=John
```
or
```bash
GET http://localhost:8080/customers?email=john.doe@example.com
```

### 4. **Update a customer** (PUT request)
```bash
PUT http://localhost:8080/customer/{id}
```
**Request Body**:
```json
{
  "name": "John Doe Updated",
  "email": "john.doe.updated@example.com",
  "annualSpend": 12000
}
```

### 5. **Delete a customer** (DELETE request)
```bash
DELETE http://localhost:8080/customer/{id}
```

### 6. **Get all customers** (GET request)
```bash
GET http://localhost:8080/customer/all
```

## 4. Accessing the H2 Database Console
To access the H2 database console:
1. Open your browser and navigate to:
   ```bash
   http://localhost:8080/h2-console
   ```
2. In the login page:
   - **JDBC URL**: `jdbc:h2:mem:testdb`
   - **Username**: `sa`
   - **Password**: `password`
3. Click **Connect** to open the database console.

## 5. Assumptions
- The **tier calculation logic** categorizes customers as **Silver**, **Gold**, or **Platinum** based on their **annualSpend**.
- The application uses **in-memory H2 database** for data storage, meaning the data will reset every time the application restarts.
- API endpoints follow standard CRUD operations for customer data.

## 6. OpenAPI Documentation
To access the OpenAPI documentation via Swagger UI:
1. Open your browser and go to:
   ```bash
   http://localhost:8080/swagger-ui.html
   ```
2. This page will list all the available API endpoints and allow you to test them directly from the UI.

---

Thank you for using this CRM project!
