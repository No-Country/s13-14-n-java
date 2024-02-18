CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    name VARCHAR(255),
    address VARCHAR(255),
    gender VARCHAR(255),
    country_id BIGINT,
    phoneNumber VARCHAR(255) UNIQUE,
    profilePicture VARCHAR(255),
    role VARCHAR(255)
);
