package com.wilczek.customer;

import com.wilczek.AbstractTestContainers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest extends AbstractTestContainers {
    //

    // Rozszerzając klasę AbstractTestContainers zaczynamy działać na testowej bazie danych - kontenerze
    @Autowired
    private  CustomerRepository underTest;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        // Poniżej usuwamy dane użytkownika, który za każdym uruchomieniem testu się tworzy z klasy Main
        underTest.deleteAll();
        System.out.println(applicationContext.getBeanDefinitionCount());
    }

    public long getId(Customer customer){
        return underTest.findAll()
                .stream()
                .filter(c -> c.getEmail().equals(customer.getEmail()))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
    }
    @Test
    void existsCustomerByEmail() {
        // Given
        Customer c1 = createCustomer();
        underTest.save(c1);
        // When
        boolean check = underTest.existsCustomerByEmail(c1.getEmail());
        // Then
        assertThat(check).isTrue();
    }

    @Test
    void existsCustomerById() {
        // Given
        Customer c1 = createCustomer();
        underTest.save(c1);
        long id = getId(c1);
        // When
        boolean check = underTest.existsCustomerById(id);
        // Then
        assertThat(check).isTrue();
    }

    @Test
    void existsCustomerByEmailFailed() {
        // Given
        String email = FAKER.internet().safeEmailAddress();
        Customer c1 = createCustomer();
        underTest.save(c1);
        // When
        var condition = underTest.existsCustomerByEmail(email);

        // Then
        assertThat(condition).isFalse();
    }

    @Test
    void existsCustomerByIdFailed() {
        long fakeId = -1;

        Customer c1 = createCustomer();
        underTest.save(c1);

        // When
        var condition = underTest.existsCustomerById(fakeId);

        // Then
        assertThat(condition).isFalse();
    }
}