package me.mujyan.springbootdeveloper.service;

import me.mujyan.springbootdeveloper.dto.Lecturedto;
import me.mujyan.springbootdeveloper.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureService {
    private final LectureRepository lectureRepository;
    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }
//    public Optional<Lecturedto> Deduplication(Lecturedto lecturedto) {
//        return lectureRepository.jonjaeCheck(lecturedto);
//    }
    public Lecturedto saveLecture(Lecturedto lecture) {
        return lectureRepository.save(lecture);
    }
}
