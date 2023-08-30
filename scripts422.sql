CREATE TABLE cars (
    id INTEGER PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER);

CREATE TABLE drivers (
    id INTEGER PRIMARY KEY,
    name TEXT,
    age INTEGER,
    car_id INTEGER REFERENCES cars (id));









