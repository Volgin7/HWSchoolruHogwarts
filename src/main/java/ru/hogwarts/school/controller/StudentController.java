package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
import java.util.Collection;
import java.util.List;

@RequestMapping("/student")
@RestController

public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.toString());
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }


    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student editedStudent = studentService.editStudent(student);
        if(editedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editedStudent);
    }

    @DeleteMapping()
    public ResponseEntity<Student> deleteStudent(@RequestParam Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/by-faculty")
    public Collection<Student> getAllByFaculty(@RequestParam Long id) {
        return studentService.getAllStudentsByFacultyId(id);
    }

    @GetMapping()
    public ResponseEntity findStudents(@RequestParam(required = false) Long id,
                                        @RequestParam(required = false) Long min, @RequestParam(required = false) Long max) {
        // System.out.println("In GET Students");
        if(id != null) {
            return ResponseEntity.ok(studentService.findStudent(id));
        }
        if(min != null && max != null) {
            return ResponseEntity.ok(studentService.findByAge(min, max));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/count")
    public int countAllStudents() {
        return studentService.countAllStudents();
    }

    @GetMapping("/min-age")
    public int minAgeOfStudents() {
        return studentService.minAgeOfStudents();
    }

    @GetMapping("/max-age")
    public int maxAgeOfStudents() {
        return studentService.maxAgeOfStudents();
    }

    @GetMapping("/avg-age")
    public int avgAgeOfStudents() {
        return studentService.avgAgeOfStudents();
    }

    @GetMapping("/last-five")
    public ResponseEntity findLastFiveStudents() {
        return ResponseEntity.ok(studentService.findLastFiveStudents());
    }

    @GetMapping("/parStreams/astudents")
    public List<String> getStudentsA() {
        return studentService.getStudentsA();
    }

    @GetMapping("/parStreams/avg-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }
}

