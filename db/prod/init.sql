CREATE TABLE IF NOT EXISTS environment_marker (
    id INTEGER PRIMARY KEY,
    environment VARCHAR(32) NOT NULL,
    message VARCHAR(255) NOT NULL
);

INSERT INTO environment_marker (id, environment, message)
VALUES (1, 'production', 'Connected to the MySQL production database')
ON DUPLICATE KEY UPDATE
    environment = VALUES(environment),
    message = VALUES(message);
