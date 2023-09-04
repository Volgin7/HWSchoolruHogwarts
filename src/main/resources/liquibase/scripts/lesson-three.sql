-- liquibase formatted sql

-- changeset avolgin:1
CREATE TABLE faculty (
    id  SERIAL,
    name VARCHAR,
    color VARCHAR,
    PRIMARY KEY(id)
);

CREATE TABLE student (
    id  SERIAL,
    name VARCHAR,
    age INTEGER,
    faculty_id INTEGER REFERENCES faculty (id),
    PRIMARY KEY(id)
    );


CREATE TABLE avatar (
    id  SERIAL,
    filePath VARCHAR,
    mediaType VARCHAR,
    fileSize INTEGER,
    data bytea,
    student_id INTEGER REFERENCES student (id),
    PRIMARY KEY(id)
    );