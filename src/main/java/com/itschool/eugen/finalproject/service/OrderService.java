package com.itschool.eugen.finalproject.service;

import com.itschool.eugen.finalproject.model.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

   List<OrderDTO> findAllOrders(Long customerId);

   OrderDTO placeOrder(Long customerId, OrderDTO orderDTO);

   List<OrderDTO> findOrdersByDescription(String description);
}
