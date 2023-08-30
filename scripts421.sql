ALTER TABLE student
ADD CONSTRAINT check_age CHECK(age >= 16);

ALTER TABLE student
ADD CONSTRAINT unique_name UNIQUE(name),
ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty
ADD CONSTRAINT faculty_name_and_color_unique UNIQUE (name, color);

ALTER TABLE student
ALTER age SET DEFAULT 20;


