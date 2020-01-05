CREATE TABLE "account"(
    id UUID PRIMARY KEY,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "vehicle"(
    id UUID PRIMARY KEY,
    make VARCHAR,
    model VARCHAR,
    year INTEGER,
    registration_number VARCHAR
);

CREATE TABLE "transmitter"(
    id UUID PRIMARY KEY,
    account_id UUID REFERENCES account(id),
    vehicle_id UUID REFERENCES vehicle(id),
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "location"(
    id UUID PRIMARY KEY,
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    transmitter_id UUID REFERENCES transmitter(id),
    account_id UUID REFERENCES account(id),
    gps_timestamp TIMESTAMP NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
