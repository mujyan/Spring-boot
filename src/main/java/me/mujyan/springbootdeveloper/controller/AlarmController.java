package me.mujyan.springbootdeveloper.controller;

import me.mujyan.springbootdeveloper.dto.Lecturedto;
import me.mujyan.springbootdeveloper.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlarmController {
    @Autowired
    private LectureService lectureService;
    @PostMapping("/api/LectureData")
    public ResponseEntity<List<Lecturedto>> data() {
        lectureService.getLectureDatas();
        return new ResponseEntity<>(lectureService.getLectureDatas(), HttpStatus.OK);
    }
}

class alarmdto {
    private String id;
}
