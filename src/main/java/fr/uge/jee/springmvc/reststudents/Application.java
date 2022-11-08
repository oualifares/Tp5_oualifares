package fr.uge.jee.springmvc.reststudents;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner printAllBeans(ApplicationContext applicationContext){
        return args -> {
            WebClient webClient = WebClient.create();
            var students = webClient.get()
                    .uri("http://localhost:8080/students/")
                    .retrieve()
                    .bodyToFlux(Student.class) ;
            students.toStream().collect(Collectors.toUnmodifiableList()).forEach(System.out::println);
        };
    }

}