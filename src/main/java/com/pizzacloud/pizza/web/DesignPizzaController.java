package com.pizzacloud.pizza.web;

import com.pizzacloud.pizza.Ingredient;
import com.pizzacloud.pizza.Pizza;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignPizzaController {

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("1", "szynka", Ingredient.Type.PROTEIN),
                new Ingredient("2", "boczek wędzony", Ingredient.Type.PROTEIN),
                new Ingredient("3", "kurczak", Ingredient.Type.PROTEIN),
                new Ingredient("4", "salami", Ingredient.Type.PROTEIN),
                new Ingredient("5", "kiełbasa", Ingredient.Type.PROTEIN),
                new Ingredient("6", "pomidor", Ingredient.Type.VEGETABLE),
                new Ingredient("7", "papryka", Ingredient.Type.VEGETABLE),
                new Ingredient("8", "rukola", Ingredient.Type.VEGETABLE),
                new Ingredient("9", "kukurydza", Ingredient.Type.VEGETABLE),
                new Ingredient("10", "pikantna papryczka", Ingredient.Type.VEGETABLE),
                new Ingredient("11", "łosoś", Ingredient.Type.FISH),
                new Ingredient("12", "tuńczyk", Ingredient.Type.FISH),
                new Ingredient("13", "ananas", Ingredient.Type.FRUIT),
                new Ingredient("14", "gruszka", Ingredient.Type.FRUIT),
                new Ingredient("15", "mozarella", Ingredient.Type.CHEESE),
                new Ingredient("16", "cheddar", Ingredient.Type.CHEESE),
                new Ingredient("17", "cheddar", Ingredient.Type.CHEESE),
                new Ingredient("18", "ketchup", Ingredient.Type.SAUCE),
                new Ingredient("19", "sos czosnkowy", Ingredient.Type.SAUCE)
        );

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
