package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

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

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();

    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
/*
    public Collection<Student> ageStudentFilter(int age) {

        List<Student> studentsList = new ArrayList<>(students.values());
        List<Student> studentsAtAge = studentsList.stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
        return studentsAtAge;
    }
*/
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
