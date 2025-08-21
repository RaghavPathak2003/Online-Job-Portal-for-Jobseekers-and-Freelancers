# 💼 Online Job Portal for Job Seekers and Freelancers Backend

A comprehensive backend system for an **Online Job Portal for Job Seekers and Freelancers** that bridges the gap between **Job Seekers** and **Freelancers**. Developed using **Spring Boot**, this backend provides a clean, scalable, and RESTful architecture for managing users, job postings, applications, resumes and more.

---

## 📌 Table of Contentss

- [🚀 Features](#-features)
- [🛠️ Tech Stack](#️-tech-stack)
- [📂 Project Structure](#-project-structure)
- [🧩 Modules](#-modules)
- [🧪 API Testing with Postman](#-api-testing-with-postman)
- [📜 Swagger API Docs](#-swagger-api-docs)
- [⚙️ Setup Instructions](#️-setup-instructions)
- [💡 Usage Examples](#-usage-examples)
- [📌 Enums Used](#-enums-used)
- [🔒 Validation & Exception Handling](#-validation--exception-handling)
- [🎯 Future Improvements](#-future-improvements)

---

## 🚀 Features

- 🧍 User Registration for Job Seekers, Freelancers and Recruiters
- 📄 Resume Uploading and Job Posting
- ✅ Role-Based Enums for scalable user management
- 💡 DTO Layer with **ModelMapper** for clean API responses
- 🛡️ Input Validation using `@Valid` and custom validators
- 🔧 Centralized Exception Handling (`@RestControllerAdvice`)
- 📘 Swagger UI for API exploration
- 📥 API Testing with Postman
- 🐬 MySQL database integration with JPA & Hibernate
- ⛱️ Clean Codebase using **Lombok**

---

## 🛠️ Tech Stack

### 🧩 Backend
- Java 8+
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- ModelMapper
- Lombok
- Swagger/OpenAPI

### 🗃️ Database
- MySQL

### 🧪 Tools
- Postman (API Testing)
- Swagger UI (API Docs)
- Git & GitHub (Version Control)
- IntelliJ
- MySQL Workbench

---

## 📂 Project Structure

```
src/
├── config                       # Configuration beans (Swagger, ModelMapper, etc.)
├── controller                   # REST controllers
├── dto                          # Request and Response DTOs
├── model/entity                 # JPA entities
├── enums                        # Enum types (Role, Status, etc.)
├── exception                    # Custom exceptions and global handlers
├── repository                   # Spring Data JPA repositories
├── service                      # Service interfaces
├── serviceImpl                  # Service classes and implemented interfaces
└── JobPortalApplication.java    # Main Spring Boot app
```

---

## 🧩 Modules

### 1. **Admin Module**
- Manage recruiters, freelancers, and job seekers
- Oversee project postings and applications
- Monitor platform activity

### 2. **Freelancer Module**
- Register and manage freelancer profiles
- Upload resume and profile picture

### 3. **Job Seeker Module**
- Register and manage job seeker profiles
- Upload resume and profile picture

### 4. **Recruiter Module**
- Register and manage recruiter profiles
- View applications and contact candidates
- Collaborate with freelancers or job seekers

### 5. **Job/Project Post Module**
- Create job/project posts with category, budget, and description
- Update or close postings
- View listings by filters

### 6. **Job/Project Application Module**
- Apply to jobs/projects
- Track application status
- View applicant details

### 7. **User Module**
- Unified model for all users (admin, freelancer, recruiter, job seeker)
- Handle role-based logic and validations

---
## 🧪 API Testing with Postman

All endpoints are tested with Postman.  

You can import and test APIs like:
- `POST http://localhost:8084/jobseekers/save`
- `GET  http://localhost:8084/jobProjectPost/get/id`
- `PUT  http://localhost:8084/recruiters/update/id`
- `DEL  http://localhost:8084/freelancers/delete/id`

---

## 📜 Swagger API Docs

Interactive API documentation is auto-generated using **Swagger**.

> 📍 URL: `http://localhost:8084/swagger-ui/index.html`

With Swagger, you can:
- View all endpoints
- Try requests with real input
- Understand request/response schema

---

## ⚙️ Setup Instructions

### ✅ Prerequisites

- Java 8 or higher
- Maven
- MySQL Server

### 🔧 Clone & Configure

```bash
git clone https://github.com/RaghavPathak2003/Online-Job-Portal-for-Jobseekers-and-Freelancers.git
cd job-portal-backend
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/job_portal
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### ▶️ Run the Application

```bash
./mvnw spring-boot:run
```

---

## 💡 Usage Examples

### ✅ Register a Job Seeker

```json
POST http://localhost:8084/jobseekers/save

{
  "name": "Raghav Pathak",
  "email": "raghav@example.com",
  "password": "securePass123",
  "skills": ["Java", "Spring", "SQL"]
}
```

### 📝 Post a Job/Project

```json
POST http://localhost:8084/jobProjectPost/save

{
  "userId": 8,
  "title": "Java Developer",
  "description": "Looking for a Java developer with experience in Spring Boot.",
  "postFor": "JOBSEEKER",
  "mandatorySkills": "Java, Spring Boot, REST APIs",
  "qualifications": "B.Tech in Computer Science",
  "experienceRequired": "2 years",
  "companyName": "Tech Solutions Pvt Ltd",
  "ctc": 600000,
  "jobLocation": "Bangalore",
  "employmentType": "Full-time",
  "aboutCompany": "Tech Solutions is a leading software services company.",
  "workMode": "Hybrid"
}
```

---

## 📌 Enums Used

- `ApplicationStatus`: `APPLIED`, `SHORTLISTED`, `REJECTED`, `HIRED`
- `Gender`: `MALE`, `FEMALE`, `OTHER`
- `Role`: `ADMIN`, `RECRUITER`, `JOBSEEKER`, `FREELANCER`

---

## 🔒 Validation & Exception Handling

### ✅ Validations
- Email and phone no. format
- Required fields
- Resume file size & type
- Password length and strength

### ⚠️ Exception Handling
- `@RestControllerAdvice` + `@ExceptionHandler`
- Custom exceptions like `EntityNotFoundException`, `InvalidUserRoleException`, `ResourceNotFoundException`
- Standardized error response example:

``` Example json when a jobseeker/freelancer try to post a job/project
{
    "error": "Only recruiters are allowed to post jobs/projects."
}
```

---

## 🎯 Future Improvements

- Email Notification on job application
- Resume parsing and keyword matching
- JWT-based Authentication & Authorization

---

> Crafted with ❤️ by Raghav Pathak
