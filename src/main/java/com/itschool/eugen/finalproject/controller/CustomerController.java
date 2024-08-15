package com.itschool.eugen.finalproject.controller;

import com.itschool.eugen.finalproject.model.CustomerDTO;
import com.itschool.eugen.finalproject.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer Manager", description = "Customer Manager API that manipulates operations related to customers") // Swagger annotation to group the API endpoints under the "Customer Manager" tag
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Add a new customer")
    @PostMapping( "/customers")
    public CustomerDTO insertCustomer(CustomerDTO customerDTO) {
        return customerService.addCustomer(customerDTO);
    }

    @Operation(summary = "Find a customer by ID")
    @GetMapping("/customers/{id}")
    public CustomerDTO findCustomerById(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }

    @Operation(summary = "Update data for a customer")
    @PutMapping("/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @Operation(summary = "Delete a customer")
    @DeleteMapping("/customers/{id}")
    public boolean deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

}
