package me.mujyan.springbootdeveloper.controller;

import lombok.Getter;
import me.mujyan.springbootdeveloper.repository.MemberRepository;
import me.mujyan.springbootdeveloper.service.ReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
public class fourFunctionController {
    private final MemberRepository memberRepository;
    @Autowired
    public fourFunctionController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @GetMapping("/functions")
    public String fourFunctionPage(@CookieValue("id") String userId) { //
        if(memberRepository.existsById(userId)) {
            String html;
            try {
                html = ReadFile.readfile("main.html");
            }catch (IOException e) {
                html = "Error Occured";
            }
            return html;
        }
        else {
            return "error 401";
        }
    }

    @GetMapping("/functions/menu")
    public String menuPage(@CookieValue("id") String userId) { //
        if(memberRepository.existsById(userId)) {
            String html;
            try {
                html = ReadFile.readfile("menu.html");
            }catch (IOException e) {
                html = "Error Occured";
            }
            return html;
        }
        else {
            return "error 401";
        }
    }

    @GetMapping("/functions/alarm")
    public String alarmPage(@CookieValue("id") String userId) { //
        if(memberRepository.existsById(userId)) {
            String html;
            try {
                html = ReadFile.readfile("alarm.html");
            }catch (IOException e) {
                html = "Error Occured";
            }
            return html;
        }
        else {
            return "error 401";
        }
    }

    @GetMapping("/functions/voice")
    public String voicePage(@CookieValue("id") String userId) { //
        if(memberRepository.existsById(userId)) {
            String html;
            try {
                html = ReadFile.readfile("voice.html");
            }catch (IOException e) {
                html = "Error Occured";
            }
            return html;
        }
        else {
            return "error 401";
        }
    }

    @GetMapping("/functions/room")
    public String roomPage(@CookieValue("id") String userId) { //
        if(memberRepository.existsById(userId)) {
            String html;
            try {
                html = ReadFile.readfile("room.html");
            }catch (IOException e) {
                html = "Error Occured";
            }
            return html;
        }
        else {
            return "error 401";
        }
    }
}

@Getter
class IDonly {
    String id;
}