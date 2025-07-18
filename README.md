# 📚 Book Management REST API

This is a Spring Boot-based REST API developed for Hexaware’s API Coding Challenge. While the basic requirement was to manage a collection of books, I went a step further and added **JWT-based authentication**, **role-protected routes**, and **unit tests**, all wrapped in a clean, modular structure.

##  What This Project Offers

-  **Secure Login with JWT**
  - Users can log in and get a token
  - Token is required for all protected endpoints
-  **Book Management (CRUD)**
  - Add, update, delete, view all books
  - Search by ISBN
-  **Unit Tests**
  - Written using `@SpringBootTest`
  - Tests actual controller methods, not just mocks
-  **Organized Codebase**
  - Separated into packages like `controller`, `service`, `repo`, and `entity`
-  **Validation & Error Handling**
  - Helpful error messages with proper HTTP responses
   **Postman Verified**
  - All endpoints were tested thoroughly with real-world request flows

---

##  Book Structure

Each book record contains the following:

- `title` (String): Title of the book
- `author` (String): Name of the author
- `isbn` (String): Unique identifier for each book
- `publicationYear` (Integer): Year the book was published

---

