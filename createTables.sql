DROP TABLE IF EXISTS tour CASCADE;
CREATE TABLE tour
(
    t_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    t_description VARCHAR(50) NOT NULL,
    t_start VARCHAR(75) NULL,
    t_end VARCHAR(75) NULL,
    transport_type VARCHAR(75) NULL,
    distance FLOAT NULL,
    time TIME NULL,
    img_path VARCHAR(100) NULL
);

DROP TABLE IF EXISTS tour_log CASCADE;
CREATE TABLE tour_log
(
    l_id SERIAL PRIMARY KEY,
    creation_time TIMESTAMP NOT NULL,
    comment VARCHAR(100) NULL,
    difficulty INTEGER NULL,
    total_time TIME NULL,
    rating INTEGER NULL,
    tour_id INTEGER,
    CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES tour (t_id)
);