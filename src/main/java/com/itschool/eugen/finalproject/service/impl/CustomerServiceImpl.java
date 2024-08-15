package com.itschool.eugen.finalproject.service.impl;

import com.itschool.eugen.finalproject.entity.Customer;
import com.itschool.eugen.finalproject.entity.Order;
import com.itschool.eugen.finalproject.model.CustomerDTO;
import com.itschool.eugen.finalproject.model.OrderDTO;
import com.itschool.eugen.finalproject.repository.CustomerRepository;
import com.itschool.eugen.finalproject.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) { // inject the CustomerRepository
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
            Customer customer = convertToCustomer(customerDTO);

            // save the User entity into database
            Customer createdCustomer = customerRepository.save(customer);
            return convertToCustomerDTO(createdCustomer);
        // customerRepository.save(customerDTO);
    }

    @Override
    public CustomerDTO findCustomer(Long id) {
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id: " + id + " not found"));
        return convertToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
            List<Customer> allCustomers = customerRepository.findAll();

            return allCustomers.stream()
                    .map(this::convertToCustomerDTO)
                    .toList();
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
            Customer customer = convertToCustomer(customerDTO);
            customer.setId(id);

            Customer updatedCustomer = customerRepository.save(customer);

            return convertToCustomerDTO(updatedCustomer);
    }

    @Override
    public boolean deleteCustomer(Long id) {
            customerRepository.deleteById(id);
            return true;
    }
    private Customer convertToCustomer(CustomerDTO customerDTO) {
            // get the addressDTO from the userDTO
           // AddressDTO addressDTO = userDTO.address();

            // convert the addressDTO to an Address entity
          //  Address address = new Address(addressDTO.city(), addressDTO.street(), addressDTO.number(), addressDTO.zipCode());

            // get the orderDTOs from the userDTO
            List<OrderDTO> orderDTOS = customerDTO.orders();

            // convert the orderDTOs to Order entities
            List<Order> orders = Optional.ofNullable(orderDTOS)
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(orderDTO -> new Order(orderDTO.description()))
                    .toList();

            // create a new User entity with userDTO info and the address entity
            return new Customer(customerDTO.lastName(), customerDTO.firstName(), customerDTO.phoneNumber(), customerDTO.city(), orders);
        }

        private CustomerDTO convertToCustomerDTO(Customer customer) {
            // get the Address entity from the User entity
            //Address address = user.getAddress();

            // convert the Address entity to an AddressDTO
           // AddressDTO addressDTO = new AddressDTO(address.getCity(), address.getStreet(), address.getNumber(), address.getZipCode());

            List<OrderDTO> orderDTOS = customer.getOrders()
                    .stream()
                    .map(order -> new OrderDTO(order.getId(), order.getDescription()))
                    .toList();

            // return a new UserDTO based on the info from User entity and the AddressDTO
            return new CustomerDTO(customer.getLastName(), customer.getFirstName(), customer.getPhoneNumber(), customer.getCity(), orderDTOS);
        }
    }


