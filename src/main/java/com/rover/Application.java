package com.rover;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

//@Configuration
@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScans({@ComponentScan("com.rover.domain"), @ComponentScan("com.rover.repository")
//, @ComponentScan("com.rover.service")})
public class Application {

    public static void main(String[] args) {
    	System.out.println("Initializing the application .............................");
        SpringApplication.run(Application.class, args);
    }
    
}