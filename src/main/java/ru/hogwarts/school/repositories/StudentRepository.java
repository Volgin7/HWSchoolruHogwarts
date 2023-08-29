package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findByAgeBetween(int min, int max);
    Collection<Student> findAllByFaculty_Id(Long id);


    @Query(value = "SELECT count(*) FROM student", nativeQuery = true)
    public int countAllStudents();

    @Query(value = "SELECT min(age) FROM student", nativeQuery = true)
    public int minAgeOfStudents();

    @Query(value = "SELECT max(age) FROM student", nativeQuery = true)
    public int maxAgeOfStudents();

    @Query(value = "SELECT avg(age) FROM student", nativeQuery = true)
    public int avgAgeOfStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    public Collection<Student> findLastFiveStudents();




}
