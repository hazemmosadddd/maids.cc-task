
# Library Management System (Maids.cc Task)

## Overview

The Library Management System manages books and patrons in a library. It provides functionalities to create, read, update, and delete books and patrons. The system also supports borrowing and returning books, uses JWT for authentication, and caches book data for performance.


### Authentication is handled by an external service. To authenticate requests:

1. **Obtain a JWT Token**: The token is provided by the external authentication service. you can use this token: 
authToken=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImhhenpvMTIzIiwiaWQiOiI2NmI1ZDZlYzRiNTFjZjUxYTlhZTU3YjYiLCJpYXQiOjE3MjMyMTQ1NDB9.2B3m5abk__y0c9anwooGSaI7Er9Jws0l3zV05JLsKOc; 
 "set it in the client cookies"
2. **Set the Token in a Cookie**: The token should be stored in a cookie on the client-side for subsequent requests. Ensure the cookie is sent with each request to access protected endpoints.


## Models

### `Patron`

Represents a library patron.

- **Fields**:
  - `id`: Unique identifier.
  - `name`: Patron's name.
  - `email`: Valid email address.
  - `phoneNumber`: Phone number (10 to 15 digits).
  - `address`: Address of the patron.
  - `books`: List of borrowed books (many-to-many with `Book`).

- **Validation**:
  - Required fields: `name`, `email`, `address`.
  - `email` must be valid.
  - `phoneNumber` must be between 10 and 15 digits.

### `Book`

Represents a book in the library.

- **Fields**:
  - `id`: Unique identifier.
  - `title`: Book title.
  - `author`: Book author.
  - `isbn`: ISBN (10 to 13 characters).
  - `totalCopies`: Total copies available.
  - `borrowedCopies`: Copies currently borrowed.
  - `patrons`: List of patrons who borrowed the book (many-to-many with `Patron`).
  - `publicationYear`: Year of publication.

- **Validation**:
  - Required fields: `title`, `author`, `isbn`.
  - `isbn` must be between 10 and 13 characters.

## Security

### `JwtUtil`

Handles JWT tokens for authentication.

- **Methods**:
  - `extractClaims(String jwt)`: Extracts claims from JWT.
  - `validateToken(String jwt)`: Validates the JWT.

## Controllers

### `BookController`

Manages book-related operations.

- **Endpoints**:
  - `POST /api/book`: Create a new book.
  - `GET /api/book`: Retrieve all books.
  - `GET /api/book/{id}`: Retrieve a book by ID.
  - `PUT /api/book/{id}`: Update a book by ID.
  - `DELETE /api/book/{id}`: Delete a book by ID.

### `BorrowController`

Handles borrowing and returning books.

- **Endpoints**:
  - `POST /api/borrow/{bookId}/borrow/{patronId}`: Borrow a book.
  - `POST /api/borrow/{bookId}/return/{patronId}`: Return a book.

### `PatronController`

Manages patron-related operations.

- **Endpoints**:
  - `POST /api/patrons`: Create a new patron.
  - `GET /api/patrons`: Retrieve all patrons.
  - `GET /api/patrons/{id}`: Retrieve a patron by ID.
  - `PUT /api/patrons/{id}`: Update a patron by ID.
  - `DELETE /api/patrons/{id}`: Delete a patron by ID.

## Caching

Books are cached to improve performance. This caching layer helps reduce database load and speeds up response times for book-related queries.


## Setup and Running

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/hazemmosadddd/maids.cc-task
   cd library_managment_system
   ```

2. **Build the Project**:
   For Maven:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**:
   The application will be available at `http://localhost:8080`.

