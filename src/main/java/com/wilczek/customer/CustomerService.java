package com.wilczek.customer;

import com.wilczek.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // Rozszerzona adnotacja @Component
public class CustomerService {

    private final CastomerDAO castomerDAO;

    public CustomerService(@Qualifier("JPA") CastomerDAO castomerDAO) {
        this.castomerDAO = castomerDAO;
    }
    public List<Customer> getAllCustomers(){
        return castomerDAO.selectAllCustomers();
    }
    public Customer getCustomerById(Integer id){
        return castomerDAO.selectCustomerById(id).
                orElseThrow(() ->
                        new ResourceNotFound("Customer with id [%s] has not found".formatted(id)));
    }

}
