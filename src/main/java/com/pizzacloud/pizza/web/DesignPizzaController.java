package com.pizzacloud.pizza.web;

import com.pizzacloud.pizza.Ingredient;
import com.pizzacloud.pizza.Pizza;
import com.pizzacloud.pizza.PizzaOrder;
import com.pizzacloud.pizza.data.IngredientRepository;
import com.pizzacloud.pizza.data.PizzaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignPizzaController {
  private final IngredientRepository ingredientRepository;
  private final PizzaRepository pizzaRepository;

  @Autowired
  public DesignPizzaController(
      IngredientRepository ingredientRepository, PizzaRepository pizzaRepository) {
    this.ingredientRepository = ingredientRepository;
    this.pizzaRepository = pizzaRepository;
  }

  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepository.findAll().forEach(ingredients::add);

    Ingredient.Type[] types = Ingredient.Type.values();
    for (Ingredient.Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
    model.addAttribute("design", new Pizza());
    return "design";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
    return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
  }

  @ModelAttribute(name = "order")
  public PizzaOrder order() {
    return new PizzaOrder();
  }

  @ModelAttribute(name = "pizza")
  public Pizza pizza() {
    return new Pizza();
  }

  @PostMapping
  public String processDesign(
      @Valid Pizza pizzaDesign, Errors errors, @ModelAttribute PizzaOrder order) {
    log.info("Przetwarzanie projektu pizzy: " + pizzaDesign);
    if (errors.hasErrors()) {
      return "design";
    }

    Pizza saved = pizzaRepository.save(pizzaDesign);
    order.addPizza(saved);

    return "redirect:/orders/current";
  }
}
