package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.PizzaOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {
  //  Order save(Order order);
  //  List<Order> findByDeliveryZip(String deliveryZip);
}
