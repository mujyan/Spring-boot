package me.mujyan.springbootdeveloper.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import me.mujyan.springbootdeveloper.repository.MemberRepository;
import me.mujyan.springbootdeveloper.service.ReadFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class LoginController {
    private final MemberRepository memberRepository;
    public LoginController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/login")
    public String LoginPage() {
        String html;
        try {
            html = ReadFile.readfile("login.html");
        }catch (IOException e) {
            html = "Error Occured";
        }
        return html;
    }

    @PostMapping(value = "login/processing", consumes = "application/json")
    public ResponseEntity<String> LoginProcess(@RequestBody IDPW idpw, HttpServletRequest request, HttpServletResponse response) {

        if (authenticate(idpw)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", idpw.id);

            // 세션 쿠키 생성
            Cookie sessionCookie = new Cookie("id", idpw.id);
            sessionCookie.setMaxAge(-1); // 브라우저가 닫힐 때까지 유지되는 세션 쿠키
            sessionCookie.setPath("/");
            // 쿠키를 응답 헤더에 추가
            response.addCookie(sessionCookie);

            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }

    public boolean authenticate(IDPW idpw) {
        if(!memberRepository.existsById(idpw.id)) {
            // 아이디 틀림
            return false;
        }
        else if(!memberRepository.existsByPassword(idpw.password)) {
            // 패스워드 틀ㄹ림
            return false;
        }
        else {
            return true;
        }
    }

    @GetMapping("/info")
    public void homeLogin(@CookieValue(name = "JSESSIONID", required = false) String memberId,
                          Model model) {
        //model.addAttribute("");

    }
}

@Getter
class IDPW {
    String id;
    String password;
    String email;
}