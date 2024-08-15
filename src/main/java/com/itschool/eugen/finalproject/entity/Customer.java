package com.itschool.eugen.finalproject.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity // Annotation to tell Hibernate that this class is an entity and should be persisted in the database
@Table(name = "customers") // Annotation to tell Hibernate that this entity should be mapped to the 'users' table in the database

public class Customer {

    @Id // Annotation to tell Hibernate that this field is the primary key in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Annotation to tell Hibernate to generate new ids for us. GenerationType.IDENTITY will use the database's auto-increment feature (will increment the id by 1)
    private Long id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String city;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY) // mappedBy is used to specify the field in the Order class that owns the relationship and fetch is used to specify how the orders should be fetched (LAZY will fetch the orders only when they are accessed)
    private List<Order> orders;

    @Transient  // this marks the field as transient, meaning it will not be persisted in the database (can be used for business logic, etc.)
    private String test;

    protected Customer() {
        // hibernate needs a non-arg constructor, otherwise it will fail
    }

    // constructors needed for business logic in the service layer
    public Customer(String lastName, String firstName, String phoneNumber, String city, List<Order> orders) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.orders = orders;
    }


    // getters and setters needed for hibernate serialization/deserialization
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

        public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
