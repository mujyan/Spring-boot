package me.mujyan.springbootdeveloper.controller;

import lombok.Getter;
import me.mujyan.springbootdeveloper.service.FoodAPI;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class MenuController {
    @PostMapping("/api/menu")
    public ResponseEntity<String> MenuAPI(@RequestBody Menudto menudto) {
        JSONObject jsonobj = FoodAPI.getFood(menudto.getInputBuildingName(), menudto.getFormattedDate());
        System.out.println(jsonobj.toString());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE + ";charset=" + StandardCharsets.UTF_8).body(jsonobj.toString());
    }
}

@Getter
class Menudto {
    private String inputBuildingName;
    private String formattedDate;
}