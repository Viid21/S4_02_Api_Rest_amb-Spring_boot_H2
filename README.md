# 🧑‍💻 Task S4.02. CRUD APIs with Spring Boot (H2, MySQL, MongoDB)  
**Author:** David Rey  

## 📄 Description  
In this task you will develop **three independent Spring Boot applications**, each exposing a **REST API** that implements a full CRUD (Create, Read, Update, Delete) over different entities. Each application will use a different database technology:  

- **H2** (in-memory SQL database, ideal for development and testing).  
- **MySQL** (relational database, widely used in production environments).  
- **MongoDB** (NoSQL document database, flexible and scalable).  

Through these exercises you will learn to:  
- Build REST APIs with Spring Boot.  
- Manage persistence with **Spring Data JPA** and **Spring Data MongoDB**.  
- Apply HTTP verbs correctly (**GET, POST, PUT, DELETE**) and return appropriate status codes.  
- Implement dynamic routes with **Path Params** and **Query Params**.  
- Write and execute automated tests following **TDD (Test-Driven Development)**.  
- Handle exceptions globally with a **GlobalExceptionHandler**.  
- Structure projects following the **MVC pattern** (Model-View-Controller).  
- Create relationships between entities using JPA.  
- Introduce **DTOs** and validate input data with **Bean Validation annotations**.  
- Create a **Dockerfile** with multi-stage builds for production-ready images.  
- Configure database connections via environment variables.  

---

## ⭐ Level 1 — CRUD with H2  
The first application will manage the stock of a fruit store.  

- **Entity:** `Fruit`  
  - `Long id`  
  - `String name`  
  - `int weightInKilos`  

- **Database:** H2 (in-memory, auto-generated schema via JPA).  
- **Architecture:** MVC with packages:  
  - `controllers`  
  - `model`  
  - `services`  
  - `repository`  
  - `exception`  

- **Endpoints:**  
  - `POST /fruits` → Create fruit  
  - `PUT /fruits/{id}` → Update fruit  
  - `DELETE /fruits/{id}` → Delete fruit by id  
  - `GET /fruits/{id}` → Get fruit by id  
  - `GET /fruits` → Get all fruits  

- **Acceptance criteria:**  
  - Valid data → `201 Created` or `200 OK` with JSON details.  
  - Invalid data → `400 Bad Request`.  
  - Non-existent ID → `404 Not Found`.  
  - Successful deletion → `204 No Content`.  

---

## 📖 User Stories (Fruit API with H2)  
1. **Register a new fruit** → Add fruit with name and weight.  
2. **Consult all fruits** → Return list of all fruits.  
3. **Consult a specific fruit** → Return details by ID.  
4. **Modify an existing fruit** → Update name or weight.  
5. **Delete a fruit** → Remove fruit by ID.  

---

## ⚙️ Project Configuration  
Generate project via [Spring Initializr](https://start.spring.io/) with:  

- **Project:** Maven or Gradle  
- **Language:** Java  
- **Spring Boot:** 3.x.x (latest stable)  
- **Group:** `cat.itacademy.s04.t02.n01`  
- **Artifact/Name:** `fruit-api-h2`  
- **Description:** REST API for managing fruit stock with H2  
- **Package name:** `cat.itacademy.s04.s02.n01.fruit`  
- **Packaging:** Jar  
- **Java:** 21 (LTS)  

**Dependencies:**  
- Spring Boot DevTools  
- Spring Web  
- Spring Data JPA  
- H2 Database  
- Validation  
- Lombok (optional, reduces boilerplate code)  

---

## 🧩 Technical Requirements  
- Use **DTOs** to avoid exposing JPA entities directly in controllers.  
- Validate input with annotations like `@NotBlank`, `@NotNull`, `@Positive`.  
- Implement a **GlobalExceptionHandler** to manage errors consistently.  
- Follow **TDD**: write tests before implementing functionality.  
- Use **@SpringBootTest** with **MockMvc** or **RestAssured** for endpoint testing.  
- Use **Mockito** for isolated unit tests of services.  

---

## 🐳 Dockerfile (multi-stage build)  
- **Build stage:** Compile application and generate `.jar`.  
- **Final stage:** Copy only the `.jar` into a lightweight image (e.g., `openjdk:21-jdk-slim`) for production.  
- Configure database connection via **environment variables**.  

---

## 💻 Technologies Used  
- **Java SE 21 (LTS)**  
- **Spring Boot 3.x**  
- **Spring Web**  
- **Spring Data JPA**  
- **Spring Data MongoDB**  
- **H2 Database**  
- **MySQL**  
- **MongoDB**  
- **JUnit 5** and **Mockito** for testing  
- **Docker** for containerization  
- **Git & GitHub** for version control  

---

## 📋 Requirements  
- JDK 21 or higher  
- Maven 3.9+ or Gradle 8+  
- MySQL and MongoDB installed locally or accessible via Docker containers  
- Postman for manual API testing  
- Docker installed for building and running images  

---

## 🛠️ Installation  
- Clone the repository:  
```bash
git clone https://github.com/Viid21/S4_02_SpringBoot_CRUD.git
cd S4_02_SpringBoot_CRUD
```

- Compile and run with Maven:  
```bash
mvn clean install
mvn spring-boot:run
```

- Generate and run the `.jar`:  
```bash
mvn package
java -jar target/fruit-api-h2-0.0.1-SNAPSHOT.jar
```

- Build Docker image:  
```bash
docker build -t fruit-api-h2 .
docker run -p 8080:8080 fruit-api-h2
```
