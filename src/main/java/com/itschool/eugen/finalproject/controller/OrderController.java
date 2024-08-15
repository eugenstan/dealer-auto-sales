package com.itschool.eugen.finalproject.controller;

import com.itschool.eugen.finalproject.model.OrderDTO;
import com.itschool.eugen.finalproject.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order Manager", description = "Order Manager API that manipulates operations related to orders") // Swagger annotation to group the API endpoints under the "Order Manager" tag
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @Operation(summary = "Place an order", description = "Place an order for a specific customer and return the created order")
    @PostMapping("{customerId}")
    public OrderDTO placeOrder(@PathVariable Long customerId, OrderDTO orderDTO) {
        return orderService.placeOrder(customerId, orderDTO);
    }

    @Operation(summary = "Find all orders for a customer", description = "Find all orders for a specific customer and return a list of orders")
    @GetMapping("{customerId}")
    public List<OrderDTO> getAllOrders(@PathVariable Long customerId) {
        return orderService.findAllOrders(customerId);
    }

    @Operation(summary = "Find a order by a description", description = "Find a order by a description and return that order")
    @GetMapping("description/{description}")
    public List<OrderDTO> getOrdersByDescription(@PathVariable String description) {
        return orderService.findOrdersByDescription(description);
    }
}
