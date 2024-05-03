package com.wilczek.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getAllCustomers();
    }

    @RequestMapping( path = "{idCustomer}")
    public Customer getCustomers(@PathVariable(value = "idCustomer") Integer idCustomer){
        return customerService.getCustomerById(idCustomer);
    }
    @PostMapping
    public void registerCustomer(
            @RequestBody CustomerRegistrationRequest request){
        customerService.addCustomer(request);
    }

    @DeleteMapping(path = "{idCustomer}")
    public void deleteCustomer(@PathVariable(value = "idCustomer") Integer id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("{idCustomer}")
    public void updateCustomer(
            @PathVariable(value = "idCustomer") Integer id,
            @RequestBody CustomerUpdateRequest request){
        customerService.updateCustomer(id,request);
    }
//    @PostMapping
//    public void registerCustomer(
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "email", required = false) String email,
//            @RequestParam(value = "age", required = false) Integer age){
//        customerService.addCustomer(new CustomerRegistrationRequest(name,email,age));
//    }
}
