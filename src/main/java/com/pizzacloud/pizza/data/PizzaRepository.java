package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.Pizza;

public interface PizzaRepository {
  Pizza save(Pizza design);
}
