USE phrase_crafter_db;

INSERT IGNORE INTO `authors` (`id`, `name`) VALUES
(1, 'Steve Jobs'),
(2, 'Albert Einstein'),
(3, 'Mark Twain'),
(4, 'Lorem Generator'),
(5, 'J.R.R. Tolkien'),
(6, 'J.K. Rowling');

INSERT IGNORE INTO `phrases` (`id`, `text`, `author_id`, `category`) VALUES
(1, 'Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work.', 1, 'INSPIRATIONAL'),
(2, 'Stay hungry, stay foolish.', 1, 'INSPIRATIONAL'),
(3, 'Try not to become a person of success, but rather try to become a person of value.', 2, 'INSPIRATIONAL'),
(4, 'Imagination is more important than knowledge.', 2, 'INSPIRATIONAL'),
(5, 'Even the smallest person can change the course of the future.', 5, 'INSPIRATIONAL'),
(6, 'It does not do to dwell on dreams and forget to live.', 6, 'INSPIRATIONAL'),
(7, 'The only way to do great work is to love what you do.', 1, 'INSPIRATIONAL'),
(8, 'Age is an issue of mind over matter. If you do not mind, it does not matter.', 3, 'JOKES'),
(9, 'I can live for two months on a good compliment.', 3, 'JOKES'),
(10, 'Reality is merely an illusion, albeit a very persistent one.', 2, 'JOKES'),
(11, 'Never put off till tomorrow what you can do the day after tomorrow just as well.', 3, 'JOKES'),
(12, 'Why couldn''t Harry tell the difference between his cooking pot and his best friend? Because they were both cauld Ron!', 6, 'JOKES'),
(13, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 4, 'LOREM_IPSUM'),
(14, 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 4, 'LOREM_IPSUM'),
(15, 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 4, 'LOREM_IPSUM'),
(16, 'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 4, 'LOREM_IPSUM'),
(17, 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.', 4, 'LOREM_IPSUM');

