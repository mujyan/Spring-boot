package me.mujyan.springbootdeveloper.repository;

import me.mujyan.springbootdeveloper.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsById(String id);
    boolean existsByPassword(String password);
}