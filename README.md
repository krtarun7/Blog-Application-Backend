# Blog Application - Backend

Blog application is a RESTful backend service for a blog application, built using Spring Boot. It provides APIs for user management, blog post creation, comment handling, and more.

## Features

- User registration and login with JWT authentication
- CRUD operations for blog posts
- Comment system for posts
- Role-based access control (Admin/User)
- Pagination and sorting support
- Error handling and validation

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **MySQL/PostgreSQL** (configurable)
- **Lombok**
- **Maven**

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL/PostgreSQL
- IDE (e.g., IntelliJ, Eclipse)

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/blog-app-apis.git
   cd blog-app-apis-backend

2. Configure your database in (application.properties):
   
spring.datasource.url=jdbc:mysql://localhost:3306/blog-app-apis
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3. Run the application:
mvn spring-boot:run

### API Endpoints

Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Authenticate user
GET	/api/posts	Get all posts
POST	/api/posts	Create a new post
PUT	/api/posts/{id}	Update a post
DELETE	/api/posts/{id}	Delete a post
POST	/api/posts/{id}/comments	Add comment to post
