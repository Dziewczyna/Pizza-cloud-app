package com.pizzacloud.pizza.security;

import com.pizzacloud.pizza.User;
import com.pizzacloud.pizza.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Autowired
  public UserRepositoryUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user != null) {
      return user;
    }
    throw new UsernameNotFoundException("User [" + username + "] not found");
  }
}
