package me.mujyan.springbootdeveloper.dto;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;

@Entity // 엔티티 정의
@Table(name="class_table")
@Data
public class Lecturedto {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String lecturename = null;
    @Column
    private String division = null;
    @Column
    private String professor = null;
    @Column
    private String announcement = null;
    @Column
    private String date = null;
}
