package com.customers;


import com.wilczek.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ComponentScan(basePackages = "com.customers")
@EnableAutoConfiguration
@RestController
public class Main2 {

    //temporary db
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(1,"Alex","alex@gmail.com",21);
        Customer jamila = new Customer(2,"Jamila","jamila@gmail.com",19);
        customers.add(alex);
        customers.add(jamila);
    }


    public static void main(String[] args) {
        System.out.println(customers);
        SpringApplication.run(Main.class,args);
    }

    @GetMapping("/")
    public List<Customer> getCustomers(){
        return customers;
    }
    static class Customer{
        private Integer id, age;
        private String name, email;

        public Customer(){}
        public Customer(Integer id, String name, String email,Integer age) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "id=" + id +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return Objects.equals(id, customer.id) && Objects.equals(age, customer.age) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, age, name, email);
        }
    }
}
