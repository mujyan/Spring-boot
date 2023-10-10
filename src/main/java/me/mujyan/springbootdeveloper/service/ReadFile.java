package me.mujyan.springbootdeveloper.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {
    public static String readfile(String fileName) throws IOException {
        String html = Files.readString(Paths.get(".\\src\\main\\resources\\template\\"+fileName));
        return html;
    }
}