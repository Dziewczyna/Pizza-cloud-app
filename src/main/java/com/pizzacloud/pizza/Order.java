package com.pizzacloud.pizza;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Data placedAt;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
}
