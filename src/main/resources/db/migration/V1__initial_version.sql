CREATE TABLE "app_user"(
    id SERIAL PRIMARY KEY,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "vehicle"(
    id SERIAL PRIMARY KEY,
    make VARCHAR,
    model VARCHAR,
    year INTEGER,
    registration_number VARCHAR
);

CREATE TABLE "transmitter"(
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES app_user(id),
    vehicle_id INTEGER REFERENCES vehicle(id),
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "location"(
    id BIGSERIAL PRIMARY KEY,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    transmitter_id INTEGER REFERENCES transmitter(id),
    user_id INTEGER REFERENCES app_user(id),
    gps_timestamp TIMESTAMP NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
