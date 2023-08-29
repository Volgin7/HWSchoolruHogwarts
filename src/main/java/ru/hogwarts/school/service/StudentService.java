package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;

import static java.lang.constant.ConstantDescs.NULL;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();

    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(Long min, Long max) {
        return studentRepository.findByAgeBetween(Math.toIntExact(min), Math.toIntExact(max));
    }

    public Collection<Student> getAllStudentsByFacultyId(Long id) {
        return studentRepository.findAllByFaculty_Id(id);
    }

    public int countAllStudents() {
        return studentRepository.countAllStudents();
    }

    public int minAgeOfStudents() {
        return studentRepository.minAgeOfStudents();
    }

    public int maxAgeOfStudents() {
        return studentRepository.maxAgeOfStudents();
    }

    public int avgAgeOfStudents() {
        return studentRepository.avgAgeOfStudents();
    }

    public Collection<Student> findLastFiveStudents() {
        return studentRepository.findLastFiveStudents();
    }

}
