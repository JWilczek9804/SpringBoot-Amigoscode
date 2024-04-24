package com.wilczek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

//@SpringBootApplication
@ComponentScan(basePackages = "com.wilczek")
@EnableAutoConfiguration
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
    @GetMapping("/")
    public GreetResponse greet(){
        return new GreetResponse("Hello",
                List.of("Java","JS","Python"),
                List.of(new Person("Bartek","Zawada"),
                        new Person("Karol","Buta"),
                        new Person("Jarek","Baniak")),
                new Person("Jakub", "Wilczek"));
    }

    record Person(String name,String surname){}
    record GreetResponse(String greet, List<String> favProgrammingLang, List<Person> allCoworkers, Person person){}

//class GreetResponse{ // poniżej napisana klasa jest 1 do 1 utworzonym recordem.
//        private final String greet;
//
//    GreetResponse(String greet) {
//        this.greet = greet;
//    }
//
//    public String getGreet() { // bez tej metody na stronie nie wyświetli się prawidłowo JSON
//        return greet;
//    }
//
//    @Override
//    public String toString() {
//        return "GreetResponse{" +
//                "greet='" + greet + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        GreetResponse that = (GreetResponse) o;
//        return Objects.equals(greet, that.greet);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(greet);
//    }
//}
}
