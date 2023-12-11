package me.mujyan.springbootdeveloper.repository;

import me.mujyan.springbootdeveloper.dto.Lecturedto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecturedto, Long> {
    //Optional<Lecturedto> jonjaeCheck(Lecturedto lecturedto);
}