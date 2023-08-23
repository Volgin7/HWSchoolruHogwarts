package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testContextLoaded() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetAllStudents() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student",String.class))
                .isNotEmpty();

    }

    @Test
    public void testGetStudentsById() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?id=1",String.class))
                .isNotEmpty();
    }
    @Test
    public void testGetStudentsByAge() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student?min=0&max=256",String.class))
                .isNotEmpty();
    }

    @Test
    public void testGetStudentsByFaculty() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/by-faculty?id=1",String.class))
                .isNotEmpty();

    }

    @Test
    public void testCreateAndDeleteStudent() throws Exception {
        Student student = new Student();
        student.setName("Nastya");
        student.setAge(22);

        String studentString = this.restTemplate.postForObject("http://localhost:" + port + "/student",student, String.class);
        JSONObject studentObject = new JSONObject(studentString);

        Assertions.assertThat(student.getName()).isEqualTo(studentObject.getString("name"));

        restTemplate.delete("http://localhost:" + port + "/student?id=" + (int) studentObject.getLong("id"));

    }

    @Test
    public void testEditStudent() throws Exception {
        int newAge = 56;

        Student student = new Student();
        student.setId(1L);
        student.setName("Alex");
        student.setAge(newAge);

        this.restTemplate.put("http://localhost:" + port + "/student",student);
        String studentString = this.restTemplate.getForObject("http://localhost:" + port + "/student?id=1",String.class);
        JSONObject studentObject = new JSONObject(studentString);

        Assertions.assertThat(student.getAge()).isEqualTo(studentObject.getLong("age"));

    }

}
