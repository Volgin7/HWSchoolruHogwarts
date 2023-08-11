select * from student
where age>18 and age<55;

select name from student;

select * from student
where name like '%o%';

select * from student
where age - 20 < id;  // -20 to find smth

select * from student
ORDER by age;

select * from student as s, faculty as f
where s.faculty_id = f.id
ORDER by age;