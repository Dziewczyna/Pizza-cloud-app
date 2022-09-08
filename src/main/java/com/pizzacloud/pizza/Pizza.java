package com.pizzacloud.pizza;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Pizza {
    private Long id;
    private Date createdAt;
    private String name;
    private List<String> ingredients;
}
