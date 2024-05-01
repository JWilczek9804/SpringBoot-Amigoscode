package com.wilczek;
import com.wilczek.customer.Customer;
import com.wilczek.customer.CustomerRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//@SpringBootApplication
@ComponentScan(basePackages = "com.wilczek")
@EnableAutoConfiguration
public class Main {
    //temporary db

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

//        printBeans(applicationContext);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return args -> {
            Customer alex = new Customer("Alex","alex@gmail.com",21);
            Customer jamila = new Customer("Jamila","jamila@gmail.com",19);
            Customer jacob = new Customer("Jacob","jacob@gmail.com",22);
            customerRepository.saveAll(List.of(alex,jamila,jacob));
            customerRepository.save(new Customer("John","john@gmail.com",41));
            customerRepository.delete(alex);
        };
    }
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // Domyślna wartość zakresu, w której używana jest pojedyńcza instancja na cały kontener
    public Foo getFoo(){
        return new Foo("bar");
    }
    record Foo(String name){}

    public static void printBeans(ConfigurableApplicationContext ctx) {

        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
    }


//    @RequestMapping(method = RequestMethod.GET,value = "/test")
//    @GetMapping("/greet")
//    public GreetResponse greet(
//            @RequestParam(value = "name", required = false) String mess,
//            @RequestParam(value = "surname", required = false) String sur)
//    {
//        String getMessage = mess == null || mess.isBlank() ? "Hello" : "Hello " + mess;
//        String surname = sur == null || sur.isBlank() ? getMessage : getMessage + " " + sur;
//        return new GreetResponse(surname,
//                List.of("Java","JS","Python"),
//                List.of(new Person("Bartek","Zawada"),
//                        new Person("Karol","Buta"),
//                        new Person("Jarek","Baniak")),
//                new Person("Jakub", "Wilczek"));
//    }
//
//    record Person(String name,String surname){}
//    record GreetResponse(String greet, List<String> favProgrammingLang, List<Person> allCoworkers, Person person){}

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
