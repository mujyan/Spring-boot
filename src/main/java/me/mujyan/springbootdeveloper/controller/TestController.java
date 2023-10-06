package me.mujyan.springbootdeveloper.controller;

import me.mujyan.functions.ReadFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {
    @GetMapping("/")
    public String test() {
        String html;
        try {
            html = ReadFile.readfile();
        }catch (IOException e) {
            html = "Error Occured";
        }
        return html;

    }
}