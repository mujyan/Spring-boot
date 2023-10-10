package me.mujyan.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "me.mujyan.springbootdeveloper.entity")
public class springBootDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(springBootDeveloperApplication.class, args);
    }
}

