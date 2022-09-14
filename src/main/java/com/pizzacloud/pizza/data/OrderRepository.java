package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.Order;

public interface OrderRepository {
  Order save(Order order);
}
