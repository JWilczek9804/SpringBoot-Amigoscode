package com.wilczek.customer;

import com.wilczek.AbstractTestContainers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerJDBCDataAccessServiceTest extends AbstractTestContainers {

    private CustomerJDBCDataAccessService underTest;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();


    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDataAccessService(
                getJDBCTemplate(),
                customerRowMapper
        );
    }

    @Test
    void selectAllCustomers() {
        // Given
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress() + "-" + UUID.randomUUID(),
                new Random().nextInt(19,40)
        );

        underTest.insertCustomer(customer);

        // When
        List<Customer> customers = underTest.selectAllCustomers();

        // Then
        assertThat(customers).isNotEmpty();
        System.out.println();
    }

    @Test
    void selectCustomerById() {
        // Given
        String email = FAKER.internet().safeEmailAddress();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                new Random().nextInt(19,40)
        );
        underTest.insertCustomer(customer);

        long id = underTest.selectAllCustomers()
                .stream()
                .filter(c -> c.getEmail().equals(email))
                .map(Customer::getId)
                .findFirst()
                .orElseThrow();
        // When
        Optional<Customer> actual = underTest.selectCustomerById(id);

        // Then
        assertThat(actual).isPresent().hasValueSatisfying(v -> {
            assertThat(v.getId()).isEqualTo(id);
            assertThat(v.getName()).isEqualTo(customer.getName());
            assertThat(v.getEmail()).isEqualTo(customer.getEmail());
            assertThat(v.getAge()).isEqualTo(customer.getAge());
            }
        );
    }

    @Test
    void willReturnEmptyWhenSelectCustomerById() {
        // Given
        long id = -1;

        // When
        Optional<Customer> customer = underTest.selectCustomerById(id);

        // Then
        assertThat(customer).isEmpty();
    }

    @Test
    void insertCustomer() {
        // Given
        String email = FAKER.internet().safeEmailAddress();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                new Random().nextInt(19,55)
        );

        // When
        underTest.insertCustomer(customer);
        // Then
        assertThat(underTest).isNotNull();
    }

    @Test
    void deleteCustomerById() {
        // Given

        // When

        // Then
    }

    @Test
    void updateCustomer() {
        // Given
        Customer customer = new Customer(
                FAKER.name().fullName(),
                FAKER.internet().safeEmailAddress(),
                new Random().nextInt(19,55)
        );
        underTest.updateCustomer(customer);

        // When

        // Then
    }

    @Test
    void existPersonWithEmail() {
        // Given
        String email = FAKER.internet().safeEmailAddress();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                new Random().nextInt(19,55)
        );
        // When
        boolean check = underTest.selectAllCustomers()
                .stream()
                .anyMatch(c -> c.getEmail().equals(email));
        // Then
        assertThat(check).isTrue();
    }

    @Test
    void existPersonWithId() {
        // Given
        String email = FAKER.internet().safeEmailAddress();
        Customer customer = new Customer(
                FAKER.name().fullName(),
                email,
                new Random().nextInt(19,55)
        );
        // When
        boolean check = underTest.selectAllCustomers()
                .stream()
                .map(Customer::getId)
                .anyMatch(c -> c.equals(customer.getId()));

        // Then
        assertThat(check).isTrue();
    }
}