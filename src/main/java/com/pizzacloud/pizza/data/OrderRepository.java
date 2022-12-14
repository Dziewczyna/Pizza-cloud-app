package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
  //  Order save(Order order);
  //  List<Order> findByDeliveryZip(String deliveryZip);
}
