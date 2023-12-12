package me.mujyan.springbootdeveloper.controller;

import me.mujyan.springbootdeveloper.dto.Exceldto;
import me.mujyan.springbootdeveloper.service.DataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeTableController {
    @PostMapping("/api/timetable")
    public List<Exceldto> TimeTableApi(@RequestBody Exceldto exceldto ) {
        DataService ds = new DataService(exceldto.getTime(), exceldto.getBuildingName());
        List<Exceldto> datas = ds.getAllData();
        return datas;
    }

}
