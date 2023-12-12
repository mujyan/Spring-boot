package me.mujyan.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;


public class Exceldto {

    private String buildingName;
    private String time;
    private String classroomNumber;
    private String subjectName;
    private String instructor;

    public String getTime() {
        return time;
    }
    public String getClassroomNumber() {
        return classroomNumber;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public String getInstructor() {
        return instructor;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public Exceldto(String buildingName, String time, String classroomNumber, String subjectName, String instructor) {
        this.buildingName = buildingName;
        this.time = time;
        this.classroomNumber = classroomNumber;
        this.subjectName = subjectName;
        this.instructor = instructor;
    }

}