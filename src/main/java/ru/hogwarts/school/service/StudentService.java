package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(Long id) {
        return students.get(id);
    }

    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> ageStudentFilter(int age) {

        List<Student> studentsList = new ArrayList<>(students.values());
        List<Student> studentsAtAge = studentsList.stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
        return studentsAtAge;
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }
}
