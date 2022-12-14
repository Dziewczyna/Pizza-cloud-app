package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {
  //  Pizza save(Pizza design);
}
