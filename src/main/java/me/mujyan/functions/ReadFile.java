package me.mujyan.functions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile {
    public static String readfile() throws IOException {
        String html = Files.readString(Paths.get(".\\src\\main\\resources\\templates\\mainPage.html"));
        return html;
    }
}