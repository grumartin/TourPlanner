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
    time INTEGER NULL,
    img_path VARCHAR(100) NULL
);

DROP TABLE IF EXISTS tour_log CASCADE;
CREATE TABLE tour_log
(
    l_id SERIAL PRIMARY KEY,
    creation_time TIMESTAMP NOT NULL,
    comment VARCHAR(100) NULL,
    difficulty INTEGER NULL,
    total_time INTEGER NULL,
    rating INTEGER NULL,
    tour_id INTEGER,
    CONSTRAINT fk_tour_id FOREIGN KEY (tour_id) REFERENCES tour (t_id)
);


CREATE OR REPLACE FUNCTION full_text_search(input_string VARCHAR)
RETURNS TABLE (
    t_id INT,
    name VARCHAR(50),
    t_description VARCHAR(50),
    t_start VARCHAR(75),
    t_end VARCHAR(75),
    transport_type VARCHAR(75),
    distance FLOAT,
    t_time INT
)
AS $$
BEGIN
    RETURN QUERY
    SELECT DISTINCT
        t.t_id,
        t.name,
        t.t_description,
        t.t_start,
        t.t_end,
        t.transport_type,
        t.distance,
        t.t_time
    FROM
        tour t
    JOIN
        tour_log tl ON t.t_id = tl.tour_id
    WHERE
        t.name ILIKE '%' || input_string || '%'
        OR t.t_description ILIKE '%' || input_string || '%'
        OR t.t_start ILIKE '%' || input_string || '%'
        OR t.t_end ILIKE '%' || input_string || '%'
        OR t.transport_type ILIKE '%' || input_string || '%'
        OR tl.comment ILIKE '%' || input_string || '%';
END;
$$ LANGUAGE plpgsql;
