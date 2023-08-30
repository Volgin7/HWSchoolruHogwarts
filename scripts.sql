select * from student
where age>=16 and age<55;

select name from student;

select * from student
where name like '%o%';

select * from student
where age < id;

select * from student
ORDER by age;

select s.name as student,f.name as faculty from student as s, faculty as f
where s.faculty_id = f.id
ORDER by s.name;


select * from student
ORDER by id desc
limit 5;

select * from avatar