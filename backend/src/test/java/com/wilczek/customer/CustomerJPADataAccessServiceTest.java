package com.wilczek.customer;

import com.wilczek.AbstractTestContainers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class CustomerJPADataAccessServiceTest extends AbstractTestContainers {


    private CustomerJPADataAccessService underTest;
    private AutoCloseable autoCloseable;

    @Mock
    private CustomerRepository customerRepository;

    public long getId(Customer customer){
        return underTest.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(customer.getEmail()))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
    }

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);// Otwiera mocki dla tego testu, inicjalizując adnotacje @Mock.
        underTest = new CustomerJPADataAccessService(customerRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close(); // Zamknięcie mocków po każdym teście, zapewniając, że każde podejście zaczyna się od świeżego mocka.
    }

    @Test
    void selectAllCustomers() {
        // When
        underTest.selectAllCustomers();
        // Then
        Mockito.verify(customerRepository).findAll();
    }

    @Test
    void selectCustomerById() {
        // Given
        long id = 1L;
        // When
        underTest.selectCustomerById(id);

        // Then
        Mockito.verify(customerRepository).findById(id);
    }

    @Test
    void insertCustomer() {
        // Given
        Customer c1 = createCustomer();
        // When
        underTest.insertCustomer(c1);
        // Then
        Mockito.verify(customerRepository).save(c1);
    }

    @Test
    void deleteCustomerById() {
        // Given
        long id = 1L;
        // When
        underTest.deleteCustomerById(id);
        // Then
        Mockito.verify(customerRepository).deleteById(id);
    }

    @Test
    void updateCustomer() {
        // Given
        Customer c1 = createCustomer();
        // When
        underTest.updateCustomer(c1);
        // Then
        Mockito.verify(customerRepository).save(c1);
    }

    @Test
    void existPersonWithEmail() {
        // Given
        String email = FAKER.internet().safeEmailAddress();
        // When
        underTest.existPersonWithEmail(email);
        // Then
        Mockito.verify(customerRepository).existsCustomerByEmail(email);
    }

    @Test
    void existPersonWithId() {
        // Given
        long id = 1L;
        // When
        underTest.existPersonWithId(id);
        // Then
        Mockito.verify(customerRepository).existsCustomerById(id);
    }
}