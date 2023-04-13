package com.pizzacloud.pizza.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzacloud.pizza.Pizza;
import com.pizzacloud.pizza.PizzaOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public abstract class JdbcOrderRepository implements OrderRepository {

  private final SimpleJdbcInsert orderInserter;
  private final SimpleJdbcInsert orderPizzaInserter;
  private final ObjectMapper objectMapper;

  public JdbcOrderRepository(JdbcTemplate jdbc) {
    this.orderInserter =
        new SimpleJdbcInsert(jdbc).withTableName("Pizza_Order").usingGeneratedKeyColumns("id");
    this.orderPizzaInserter = new SimpleJdbcInsert(jdbc).withTableName("Pizza_Order_Pizzas");
    this.objectMapper = new ObjectMapper();
  }

  @Override
  public PizzaOrder save(PizzaOrder order) {
    order.setPlacedAt(new Date());
    long orderId = saveOrderDetails(order);
    order.setId(orderId);
    List<Pizza> pizzas = order.getPizzas();
    return null;
  }

  private long saveOrderDetails(PizzaOrder order) {
    @SuppressWarnings("unchecked")
    Map<String, Object> values = objectMapper.convertValue(order, Map.class);
    values.put("placedAt", order.getPlacedAt());
    long orderId = orderInserter.executeAndReturnKey(values).longValue();

    return orderId;
  }

  private void savePizzaToOrder(Pizza pizza, long orderId) {
    Map<String, Object> values = new HashMap<>();
    values.put("pizzaOrder", orderId);
    values.put("pizza", pizza.getId());
    orderPizzaInserter.execute(values);
  }
}
