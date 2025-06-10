CREATE DATABASE IF NOT EXISTS phrase_crafter_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE phrase_crafter_db;

CREATE TABLE IF NOT EXISTS authors (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_name (name)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS phrases (
    id BIGINT NOT NULL AUTO_INCREMENT,
    text TEXT NOT NULL,
    author_id BIGINT NOT NULL,
    category ENUM('INSPIRATIONAL', 'JOKES', 'LOREM_IPSUM') NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_text (text(255)),
    CONSTRAINT fk_phrases_author FOREIGN KEY (author_id) REFERENCES authors(id)
) ENGINE=InnoDB;