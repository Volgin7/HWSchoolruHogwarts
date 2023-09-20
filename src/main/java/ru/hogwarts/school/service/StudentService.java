package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;

import static java.lang.constant.ConstantDescs.NULL;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        logger.info("Was invoked method for find student by id" + id);
        return studentRepository.findById(id).get();

    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Was invoked method for delete student by id" + id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for find all students");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(Long min, Long max) {
        logger.info("Was invoked method for find students by age");
        return studentRepository.findByAgeBetween(Math.toIntExact(min), Math.toIntExact(max));
    }

    public Collection<Student> getAllStudentsByFacultyId(Long id) {
        logger.info("Was invoked method for find all students by faculty id");
        return studentRepository.findAllByFaculty_Id(id);
    }

    public int countAllStudents() {
        logger.info("Was invoked method for count all students");
        return studentRepository.countAllStudents();
    }

    public int minAgeOfStudents() {
        logger.info("Was invoked method for min age of students");
        return studentRepository.minAgeOfStudents();
    }

    public int maxAgeOfStudents() {
        logger.info("Was invoked method for max age of students");
        return studentRepository.maxAgeOfStudents();
    }

    public int avgAgeOfStudents() {
        logger.info("Was invoked method for average age of students");
        return studentRepository.avgAgeOfStudents();
    }

    public Collection<Student> findLastFiveStudents() {
        logger.info("Was invoked method for find last five students");
        return studentRepository.findLastFiveStudents();
    }

}
