package ru.hogwarts.school.controller;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testContextLoaded() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }
    @Test
    public void testGetAllFaculties() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty",String.class))
                .isNotEmpty();
    }
    @Test
    public void testGetFacultyByName() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty?name=geo",String.class))
                .isNotEmpty();
    }

    @Test
    public void testGetFacultyByColor() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty?color=red",String.class))
                .isNotEmpty();
    }

    @Test
    public void testGetFacultyByStudent() throws Exception {

        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/by-student?id=1L",String.class))
                .isNotEmpty();
    }

    @Test
    public void testCreateAndDeleteFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("bio");
        faculty.setColor("brown");

        String facultyString = this.restTemplate.postForObject("http://localhost:" + port + "/faculty",faculty, String.class);
        JSONObject facultyObject = new JSONObject(facultyString);

        Assertions.assertThat(faculty.getName()).isEqualTo(facultyObject.getString("name"));

        restTemplate.delete("http://localhost:" + port + "/faculty?id=" + (int) facultyObject.getLong("id"));
    }

    @Test
    public void testEditFaculty() throws Exception {
        String newColor = "black";
        Faculty faculty = new Faculty();
        faculty.setId(2L);
        faculty.setName("geo");
        faculty.setColor(newColor);

        this.restTemplate.put("http://localhost:" + port + "/faculty",faculty);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty?name=geo",String.class))
                .isNotEmpty();

    }

}
