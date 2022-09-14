package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.Ingredient;

public interface IngredientRepository {
  Iterable<Ingredient> findAll();

  Ingredient findById(String id);

  Ingredient save(Ingredient ingredient);
}
