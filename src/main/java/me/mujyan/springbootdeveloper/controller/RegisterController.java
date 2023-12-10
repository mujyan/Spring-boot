package me.mujyan.springbootdeveloper.controller;

import me.mujyan.springbootdeveloper.dto.Member;
import me.mujyan.springbootdeveloper.service.ReadFile;
import me.mujyan.springbootdeveloper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@RestController
public class RegisterController {
    private UserService userService;

    @Autowired
    public void DBController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String registerPage() {
        String html;
        try {
            html = ReadFile.readfile("signup.html");
        }catch (Exception e) {
            html = "Error Occured";
        }
        return html;
    }
    @PostMapping("/register/go")
    public ResponseEntity<String> registering(@RequestBody Member newUser) {
        if(userService.savePerson(newUser) != null)
            return new ResponseEntity<>("Done", HttpStatus.OK);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Registration failed: Duplicate user or invalid data.");
    }
}