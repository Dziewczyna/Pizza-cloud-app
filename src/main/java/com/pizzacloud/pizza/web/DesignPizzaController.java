package com.pizzacloud.pizza.web;

import com.pizzacloud.pizza.Ingredient;
import com.pizzacloud.pizza.Pizza;
import com.pizzacloud.pizza.data.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignPizzaController {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignPizzaController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);


        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type:types){
            model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
        }
        model.addAttribute("design", new Pizza());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(Object design){
        log.info("Przetwarzanie projektu pizzy: "+design);
        return "redirect:/orders/current";
    }
}
