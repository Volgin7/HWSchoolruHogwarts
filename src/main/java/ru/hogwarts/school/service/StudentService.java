package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.constant.ConstantDescs.NULL;


@Service
public class StudentService {
    public Integer count = 0;
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

    public List<String> getStudentsA() {
        logger.info("Was invoked method for find students starts A");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(st->st.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAverageAge() {
        logger.info("Was invoked method for average age");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(-1);
    }

    public void printStudents() {
        logger.info("Was invoked method for print students");
        List<Student> allStudents = studentRepository.findAll();

        System.out.println(allStudents.get(0));
        System.out.println(allStudents.get(1));

        Thread threadOne = new Thread (() -> {
            System.out.println(allStudents.get(2));
            System.out.println(allStudents.get(3));
        });

        Thread threadTwo = new Thread (() -> {
            System.out.println(allStudents.get(4));
            System.out.println(allStudents.get(5));
        });

        threadOne.start();
        threadTwo.start();

    }

    public void printStudentsSync() {
        logger.info("Was invoked method for print students sync");
        List<Student> allStudents = studentRepository.findAll();

        printStudent(allStudents.get(0));
        printStudent(allStudents.get(1));

        Thread threadOne = new Thread (() -> {
            printStudent(allStudents.get(2));
            printStudent(allStudents.get(3));
        });

        Thread threadTwo = new Thread (() -> {
            printStudent(allStudents.get(4));
            printStudent(allStudents.get(5));
        });

        threadOne.start();
        threadTwo.start();

    }

    private synchronized void printStudent(Student student) {
        System.out.println(student);
        count++;
    }

}
