# 🎨 Phrase Crafter

A Spring Boot console application for managing inspirational phrases, jokes, and lorem ipsum text. This application provides a rich command-line interface for creating, reading, updating, and deleting phrases with authors and categories.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [Application Flow](#application-flow)
- [Data Model](#data-model)
- [Service Endpoints](#service-endpoints)
- [Error Handling](#error-handling)
- [Database Schema](#database-schema)
- [Project Structure](#project-structure)
- [Contributing](#contributing)

## ✨ Features

- **Interactive Console Menu**: User-friendly command-line interface with colored output
- **CRUD Operations**: Create, Read, Update, Delete phrases
- **Category Management**: Organize phrases by categories (Inspirational, Jokes, Lorem Ipsum)
- **Author Management**: Automatic author creation and association
- **Advanced Search**: Search phrases by text content, author, or category
- **Input Validation**: Comprehensive error handling with user-friendly messages
- **Duplicate Prevention**: Prevents insertion of duplicate phrases
- **Graceful Exit**: Clean application termination

## 🛠 Technology Stack

- **Java 21**: Programming language
- **Spring Boot 3.5.0**: Application framework
- **Spring Data JPA**: Data persistence layer
- **MySQL**: Database
- **Maven**: Build automation tool
- **Spring Dotenv**: Environment variable management

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher
- Git

## 🚀 Installation & Setup

### 1. Clone the repository

```bash
git clone <repository-url>
cd phrase-crafter
```

### 2. Database Setup

Create a MySQL database:

```sql
CREATE DATABASE phrase_crafter;
```

### 3. Environment Configuration

Create a `.env` file in the root directory:

```env
DB_URL=jdbc:mysql://localhost:3306/phrase_crafter
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

### 4. Application Properties

The application uses the following configuration in `application.properties`:

```properties
spring.application.name=phrase-crafter
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 5. Build and Run

```bash
# Compile the project
mvn clean compile

# Run the application
mvn spring-boot:run
```

## 🎯 Usage

When you run the application, you'll see an interactive menu:

```
=================================
  🎨 WELCOME TO PHRASE CRAFTER 📝
=================================

=== 📋 MAIN MENU ===
1. 📖 Show all phrases
2. 🔍 Show phrase by ID
3. ➕ Add new phrase
4. ✏️ Edit phrase
5. 🗑️ Delete phrase
6. 🏷️ Show phrases by category
7. 👤 Show phrases by author
8. 🔎 Search phrases by text
0. 🚪 Exit

Your choice:
```

### Available Operations

1. **View All Phrases**: Display all stored phrases with their details
2. **Search by ID**: Find a specific phrase by its unique identifier
3. **Add New Phrase**: Create a new phrase with text, author, and category
4. **Edit Phrase**: Update existing phrase information
5. **Delete Phrase**: Remove a phrase from the database
6. **Browse by Category**: Filter phrases by category type
7. **Search by Author**: Find phrases by author name
8. **Text Search**: Search phrases containing specific text
9. **Exit**: Gracefully terminate the application

## 🔄 Application Flow

### Main Application Flow

```
Start Application
    ↓
Initialize Spring Context
    ↓
Display Welcome Message
    ↓
Show Main Menu
    ↓
User Input Processing
    ↓
Execute Selected Operation
    ↓
Return to Main Menu (or Exit)
```

### Add New Phrase Flow

```
Select "Add New Phrase"
    ↓
Enter Phrase Text
    ↓
Enter Author Name
    ↓
Select Category (1-3)
    ↓
Validate Input
    ↓
Check for Duplicates
    ↓
Save to Database
    ↓
Display Success Message
```

### Edit Phrase Flow

```
Select "Edit Phrase"
    ↓
Enter Phrase ID
    ↓
Validate ID Exists
    ↓
Display Current Phrase
    ↓
Enter New Values (or keep current)
    ↓
Update Database
    ↓
Display Updated Phrase
```

## 📊 Data Model

### Entities

#### Phrase

- `id` (Long): Primary key, auto-generated
- `text` (String): The phrase content (TEXT column)
- `author` (Author): Many-to-One relationship with Author
- `category` (Category): Enum value (INSPIRATIONAL, JOKES, LOREM_IPSUM)

#### Author

- `id` (Long): Primary key, auto-generated
- `name` (String): Author's name

#### Category (Enum)

- `INSPIRATIONAL`: Motivational and inspiring phrases
- `JOKES`: Humorous content
- `LOREM_IPSUM`: Placeholder text

### Relationships

- **Phrase ↔ Author**: Many-to-One (Many phrases can have the same author)
- **Phrase ↔ Category**: Many-to-One (Many phrases can have the same category)

### Repository Methods

#### PhraseRepository

- `findByCategoryIgnoreCase(String categoryName)`: Find phrases by category (case-insensitive)
- `findByAuthor_NameContainingIgnoreCase(String authorName)`: Find phrases by author name (partial match)
- `findByTextContainingIgnoreCase(String text)`: Find phrases containing text (case-insensitive)
- `existsByTextIgnoreCase(String text)`: Check if phrase with exact text exists

#### AuthorRepository

- `findByName(String name)`: Find author by exact name

## ⚠️ Error Handling

The application implements comprehensive error handling:

### Input Validation

- **Empty Fields**: Prevents empty text or author names
- **Invalid IDs**: Validates positive numeric IDs
- **Category Selection**: Ensures valid category choices (1-3)
- **Duplicate Prevention**: Prevents insertion of phrases with identical text

### User-Friendly Messages

- **Duplicate Phrase**: "A phrase with this text already exists. Please try a different phrase."
- **Invalid ID**: "Please enter a valid positive number."
- **Not Found**: "Phrase with ID X not found. Please try again."
- **Invalid Category**: "Please enter a number from 1 to 3."

### Graceful Degradation

- Users can cancel operations by typing 'q' or 'quit'
- Invalid inputs prompt for retry without returning to main menu
- Application handles NumberFormatException gracefully

## 🗄️ Database Schema

```sql
-- Authors table
CREATE TABLE authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Phrases table
CREATE TABLE phrases (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text TEXT NOT NULL,
    author_id BIGINT NOT NULL,
    category VARCHAR(50) NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(id)
);
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── femcoders/java/phrase_crafter/
│   │       ├── PhraseCrafterApplication.java
│   │       ├── controller/
│   │       │   └── PhraseController.java
│   │       ├── dto/
│   │       ├── model/
│   │       │   ├── Author.java
│   │       │   ├── Category.java
│   │       │   └── Phrase.java
│   │       ├── repository/
│   │       │   ├── AuthorRepository.java
│   │       │   └── PhraseRepository.java
│   │       ├── service/
│   │       │   └── PhraseService.java
│   │       ├── util/
│   │       │   └── ColorConstants.java
│   │       └── view/
│   │           └── PhraseView.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── femcoders/java/phrase_crafter/
            └── PhraseCrafterApplicationTests.java
```

### Layer Description

- **Model**: Entity classes representing database tables
- **Repository**: Data access layer using Spring Data JPA
- **Service**: Business logic layer
- **View**: User interface layer (Console-based)
- **Util**: Utility classes (Color constants for console output)
- **Controller**: Application coordination layer

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is created for educational purposes as part of the FemCoders bootcamp.

---

**Developed with ❤️ by Nadiia Alaieva from FemCoders**
