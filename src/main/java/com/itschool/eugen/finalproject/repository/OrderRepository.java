package com.itschool.eugen.finalproject.repository;

import com.itschool.eugen.finalproject.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByCustomer_Id(Long id);

    List<Order> findOrdersByDescription(String description);
}
