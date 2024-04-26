package com.wilczek;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main2 {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
    
}
