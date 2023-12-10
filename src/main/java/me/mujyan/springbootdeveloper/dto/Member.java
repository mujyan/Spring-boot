package me.mujyan.springbootdeveloper.dto;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity // 엔티티 정의
@Table(name="user_table")
@Getter
@Setter
public class Member {
    @Id
    @NonNull
    @Column(unique = true, length=15) // 유일하고 최대 길이가 10.
    private String id;
    @NonNull
    @Column
    private String password;
    @NonNull
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private String tel;
    @Column
    private String createdTime;

    public String getId() {
        return id;
    }
}