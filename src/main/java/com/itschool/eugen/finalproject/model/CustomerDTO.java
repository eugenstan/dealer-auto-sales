package com.itschool.eugen.finalproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CustomerDTO(
        String lastName,
        String firstName,
        String phoneNumber,
        String city,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) // this annotation will make the id field read-only, meaning we don't provide it on request when creating a new UserDTO object
List<OrderDTO> orders) {
}
