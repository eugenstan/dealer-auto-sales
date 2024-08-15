package com.itschool.eugen.finalproject.service.impl;

import com.itschool.eugen.finalproject.entity.Customer;
import com.itschool.eugen.finalproject.entity.Order;
import com.itschool.eugen.finalproject.model.OrderDTO;
import com.itschool.eugen.finalproject.repository.CustomerRepository;
import com.itschool.eugen.finalproject.repository.OrderRepository;
import com.itschool.eugen.finalproject.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<OrderDTO> findAllOrders(Long customerId) {
        return orderRepository.findOrdersByCustomer_Id(customerId)
                .stream()
                .map(order -> new OrderDTO(order.getId(), order.getDescription()))
                .toList();
    }

    @Override
    public OrderDTO placeOrder(Long customerId, OrderDTO orderDTO) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id: " + customerId + " not found"));
        Order order = new Order(orderDTO.description(), customer);
        Order createdOrder = orderRepository.save(order);

        return new OrderDTO(createdOrder.getId(), createdOrder.getDescription());
    }

    @Override
    public List<OrderDTO> findOrdersByDescription(String description) {
        return orderRepository.findOrdersByDescription(description)
                .stream()
                .map(order -> new OrderDTO(order.getId(), order.getDescription()))
                .toList();
    }
}
