package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.PizzaOrder;
import com.pizzacloud.pizza.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<PizzaOrder, Long> {
    List<PizzaOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

  //  Order save(Order order);
  //  List<Order> findByDeliveryZip(String deliveryZip);
}
