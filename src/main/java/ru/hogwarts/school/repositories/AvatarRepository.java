package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Avatar;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
public interface AvatarRepository extends JpaRepository<Avatar,Long> {
    Optional<Avatar> findByStudentId(Long id);

}
