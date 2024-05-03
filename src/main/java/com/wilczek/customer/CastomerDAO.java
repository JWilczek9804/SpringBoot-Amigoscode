package com.wilczek.customer;

import java.util.List;
import java.util.Optional;


public interface CastomerDAO {
    List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(Integer id);

    void insertCustomer(Customer customer);

    void deleteCustomerById(Integer id);

    void updateCustomer(Customer customer);

    boolean existPersonWithEmail(String email);

    boolean existPersonWithId(Integer id);
}
