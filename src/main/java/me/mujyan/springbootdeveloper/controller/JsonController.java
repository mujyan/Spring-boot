package me.mujyan.springbootdeveloper.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/api")
public class JsonController {

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJson(@RequestParam String filename) throws IOException {
        Resource resource = new ClassPathResource("json/" + filename + ".json");
        if (resource.exists()) {
            String jsonContent = new String(Files.readAllBytes(Path.of(resource.getURI())));
            return ResponseEntity.ok(jsonContent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
