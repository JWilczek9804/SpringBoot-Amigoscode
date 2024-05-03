package com.wilczek.customer;

import com.wilczek.exceptions.DuplicateResourceException;
import com.wilczek.exceptions.RequestValidationException;
import com.wilczek.exceptions.ResourceNotFoundException;
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
                        new ResourceNotFoundException("Customer with id [%s] has not found".formatted(id)));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        String email = customerRegistrationRequest.email();
        String name = customerRegistrationRequest.name();
        Integer age = customerRegistrationRequest.age();
        if (castomerDAO.existPersonWithEmail(email)) {
            throw new DuplicateResourceException("Email [%s] is already taken".formatted(email));
        }
            Customer customer = new Customer(name, email, age);
            castomerDAO.insertCustomer(customer);
    }
    public void deleteCustomer(Integer id){
        if (!castomerDAO.existPersonWithId(id)){
            throw new ResourceNotFoundException("Customer with id %s not found".formatted(id));
        }
        castomerDAO.deleteCustomerById(id);
    }

    public void updateCustomer(Integer id,CustomerUpdateRequest customerUpdateRequest){
        Customer customer = getCustomerById(id);

        if (!castomerDAO.existPersonWithId(id)){
            throw new ResourceNotFoundException("Customer with id %s not found".formatted(id));
        }
        if (customer.getName().equals(customerUpdateRequest.name())
        || customer.getEmail().equals(customerUpdateRequest.email())
        || customer.getAge().equals(customerUpdateRequest.age())){
            throw new RequestValidationException("You trying to update the same value");
        }
        if (castomerDAO.existPersonWithEmail(customerUpdateRequest.email())){
            throw new DuplicateResourceException("This email is already taken");
        }
        String name = customerUpdateRequest.name() == null ? customer.getName() : customerUpdateRequest.name();
        String email = customerUpdateRequest.email() == null ? customer.getEmail() : customerUpdateRequest.email();
        Integer age = customerUpdateRequest.age() == null ? customer.getAge() : customerUpdateRequest.age();

            castomerDAO.updateCustomer(new Customer(id,name,email,age));
    }
}
