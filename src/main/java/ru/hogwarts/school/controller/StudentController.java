package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RequestMapping("students")
@RestController

public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println("createStudent");
        System.out.println(student.toString());
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        System.out.println("getStudent");
        Student student = studentService.findStudent(id);
        if(student == null) {
            return ResponseEntity.notFound() .build();
        }
        return ResponseEntity.ok(student);

    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editedStudent = studentService.editStudent(student);
        if(editedStudent == null) {
            return ResponseEntity.notFound() .build();
        }
        return ResponseEntity.ok(editedStudent);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Student student = studentService.deleteStudent(id);
        if(student == null) {
            return ResponseEntity.notFound() .build();
        }
        return ResponseEntity.ok(student);
    }
    @GetMapping("{age}")
    public Collection<Student> getStudentsAtAge(@PathVariable int age) {

        return studentService.ageStudentFilter(age);
    }

    @GetMapping
    public Collection<Student> getAllStudents() {

        return studentService.getAllStudents();
    }
}
