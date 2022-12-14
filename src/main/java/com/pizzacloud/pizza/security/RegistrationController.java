package com.pizzacloud.pizza.security;

import com.pizzacloud.pizza.data.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
  private final UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping
  public String registerForm() {
    return "registration";
  }

  @PostMapping
  public String processRegistration(RegistrationForm registrationForm) {
    userRepository.save(registrationForm.toUser(passwordEncoder));
    return "redirect:/login";
  }
}
