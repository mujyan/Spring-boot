package me.mujyan.springbootdeveloper.controller;

import me.mujyan.springbootdeveloper.service.ReadFile;
import me.mujyan.springbootdeveloper.service.Selenium;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class InfoPageController {
    @GetMapping("/")
    public String info() {
        String html;
        try {
            html = ReadFile.readfile("infoPage.html");
        }catch (IOException e) {
            html = "Error Occured";
        }
        return html;
    }
    @GetMapping("/docs")
    public String Docs() {
        String html;
        try {
            html = ReadFile.readfile("docsPage.html");
        }catch (IOException e) {
            html = "Error Occured";
        }
        return html;
    }
}