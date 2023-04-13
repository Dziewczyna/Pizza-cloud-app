package com.pizzacloud.pizza.web;

import com.pizzacloud.pizza.PizzaOrder;
import com.pizzacloud.pizza.User;
import com.pizzacloud.pizza.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix = "pizza.orders")
public class OrderController {
  private OrderProps orderProps;

  @Autowired private final OrderRepository orderRepository;

  public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
    this.orderRepository = orderRepository;
    this.orderProps = orderProps;
  }

  @GetMapping("/current")
  public String orderForm(Model model) {
    model.addAttribute("order", new PizzaOrder());
    return "orderForm";
  }

  @PostMapping
  public String processOrder(
      @Validated PizzaOrder order,
      Errors errors,
      SessionStatus sessionStatus,
      @AuthenticationPrincipal User user) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    order.setUser(user);

    orderRepository.save(order);
    sessionStatus.setComplete();
    log.info("The order has been set: " + order);

    return "redirect:/";
  }

  @GetMapping
  public String ordersForUser(@AuthenticationPrincipal User user, Model model){
    Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
    model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user,pageable));
    return "orderList";
  }
}
