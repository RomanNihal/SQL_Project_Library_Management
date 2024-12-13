# Library Management System

This repository contains a **Library Management System** developed in Java, created as part of my **Database System course**. The system is designed to manage books and users in a library, offering functionalities such as adding, deleting, and viewing books, as well as handling user sign-ins, sign-ups, and borrowing processes. The system uses SQL for data storage and retrieval.

## Features

### 1. **User Interface**
- **Home Page**:  
  The main interface where users can navigate to different sections like viewing books or managing their account.

- **User Sign-In and Sign-Up**:  
  Users can create new accounts and sign in to access the library features.

- **Book List**:  
  View all available books in the library with detailed information such as title, author, and availability.

- **Add Book**:  
  Admin users can add new books to the library catalog with necessary details.

- **Delete Book**:  
  Admin users can remove books from the catalog when necessary.

- **Borrowed Books**:  
  Users can view a list of the books they have borrowed.

### 2. **Admin Functionality**
- **Admin Home Page**:  
  A dedicated page for the admin to manage books, including adding and deleting books.

- **Book Management**:  
  Admins can easily manage the library’s book collection, ensuring books are properly added or removed.

- **Database Management**:  
  The system uses SQL queries for performing database operations like adding, deleting, and updating book information.

### 3. **Database Integration**
- **SQL Database**:  
  The system is connected to an SQL database for storing and retrieving data about users, books, and borrowed books.

- **Database Operations**:  
  The database operations for adding, deleting, and managing books are handled in the `DbOperations.java` class.

### 4. **Data Models**
- **Book Model**:  
  The `BookModel.java` class represents the structure of a book in the library, including attributes like title, author, and availability.

- **User Model**:  
  The `UserModel.java` class represents a library user, storing user details like name, email, and borrowed books.

- **Borrowed Book Model**:  
  The `BorrowedBookListModel.java` class tracks the books borrowed by users, managing the lending process.

## Technologies Used

- **Language**: Java
- **Database**: SQL (for storing book and user data)
- **Swing**: Java Swing for GUI components
- **JDBC**: Java Database Connectivity for interacting with the SQL database
- **ConnectionProvider**: A class responsible for providing the database connection.
