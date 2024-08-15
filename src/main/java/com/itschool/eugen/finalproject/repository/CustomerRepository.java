package com.itschool.eugen.finalproject.repository;

import com.itschool.eugen.finalproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> { // this repository will inherit all the methods from JpaRepository (e.g. CRUD operations)
}
