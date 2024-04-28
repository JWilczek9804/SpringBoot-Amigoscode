package com.wilczek.customer;

import java.util.List;
import java.util.Optional;


public interface CastomerDAO {
    List<Customer> selectAllCustomers();

    Optional<Customer> selectCustomerById(Integer id);

}
