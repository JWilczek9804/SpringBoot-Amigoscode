package com.wilczek.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("api/v1/customers")
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @RequestMapping( path = "api/v1/customers/{idCustomer}")
    public Customer getCustomers(@PathVariable(value = "idCustomer") Integer idCustomer){
        return customerService.getCustomerById(idCustomer);
            }
}
