package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
