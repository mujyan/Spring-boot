package me.mujyan.springbootdeveloper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPage {
    @GetMapping("/")
    public String mp() {
        return "<p>main page<p>";
    }
}

