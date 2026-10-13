CREATE TABLE IF NOT EXISTS environment_marker (
    id INTEGER PRIMARY KEY,
    environment VARCHAR(32) NOT NULL,
    message VARCHAR(255) NOT NULL
);

INSERT INTO environment_marker (id, environment, message)
VALUES (1, 'development', 'Connected to the PostgreSQL development database')
ON CONFLICT (id) DO UPDATE
SET environment = EXCLUDED.environment,
    message = EXCLUDED.message;
