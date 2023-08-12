package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Optional;


public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Collection<Faculty> findAllByNameIgnoreCase(String name);
    Collection<Faculty> findAllByColorIgnoreCase(String color);


    Faculty findByStudent_Id(long id);

}
