USE phrase_crafter_db;

INSERT IGNORE INTO authors (name) VALUES ('Steve Jobs');
INSERT IGNORE INTO authors (name) VALUES ('Albert Einstein');
INSERT IGNORE INTO authors (name) VALUES ('Mark Twain');
INSERT IGNORE INTO authors (name) VALUES ('Lorem Generator');
INSERT IGNORE INTO authors (name) VALUES ('J.R.R. Tolkien');
INSERT IGNORE INTO authors (name) VALUES ('J.K. Rowling');


INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work.',
       a.id, 'INSPIRATIONAL'
FROM authors a WHERE a.name = 'Steve Jobs';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Stay hungry, stay foolish.',
       a.id, 'INSPIRATIONAL'
FROM authors a WHERE a.name = 'Steve Jobs';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Try not to become a person of success, but rather try to become a person of value.',
       a.id, 'INSPIRATIONAL'
FROM authors a WHERE a.name = 'Albert Einstein';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Imagination is more important than knowledge.',
       a.id, 'INSPIRATIONAL'
FROM authors a WHERE a.name = 'Albert Einstein';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Even the smallest person can change the course of the future.',
       a.id, 'INSPIRATIONAL'
FROM authors a WHERE a.name = 'J.R.R. Tolkien';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'It does not do to dwell on dreams and forget to live.',
       a.id, 'INSPIRATIONAL'
FROM authors a WHERE a.name = 'J.K. Rowling';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'The only way to do great work is to love what you do.',
       a.id, 'INSPIRATIONAL'
FROM authors a WHERE a.name = 'Steve Jobs';


INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Age is an issue of mind over matter. If you do not mind, it does not matter.',
       a.id, 'JOKES'
FROM authors a WHERE a.name = 'Mark Twain';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'I can live for two months on a good compliment.',
       a.id, 'JOKES'
FROM authors a WHERE a.name = 'Mark Twain';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Reality is merely an illusion, albeit a very persistent one.',
       a.id, 'JOKES'
FROM authors a WHERE a.name = 'Albert Einstein';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Never put off till tomorrow what you can do the day after tomorrow just as well.',
       a.id, 'JOKES'
FROM authors a WHERE a.name = 'Mark Twain';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Why couldn’t Harry tell the difference between his cooking pot and his best friend? Because they were both cauld Ron!',
       a.id, 'JOKES'
FROM authors a WHERE a.name = 'J.K. Rowling';


INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.',
       a.id, 'LOREM_IPSUM'
FROM authors a WHERE a.name = 'Lorem Generator';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
       a.id, 'LOREM_IPSUM'
FROM authors a WHERE a.name = 'Lorem Generator';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
       a.id, 'LOREM_IPSUM'
FROM authors a WHERE a.name = 'Lorem Generator';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
       a.id, 'LOREM_IPSUM'
FROM authors a WHERE a.name = 'Lorem Generator';

INSERT IGNORE INTO phrases (text, author_id, category)
SELECT 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.',
       a.id, 'LOREM_IPSUM'
FROM authors a WHERE a.name = 'Lorem Generator';

