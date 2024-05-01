package com.wilczek.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// fake database
@Repository("List")
public class CustomerListDataAccessService implements CastomerDAO{

    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(1,"Alex","alex@gmail.com",21);
        Customer jamila = new Customer(2,"Jamila","jamila@gmail.com",19);
        Customer jacob = new Customer(3,"Jacob","jacob@gmail.com",22);
        customers.add(alex);
        customers.add(jamila);
        customers.add(jacob);
    }
    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }
}
