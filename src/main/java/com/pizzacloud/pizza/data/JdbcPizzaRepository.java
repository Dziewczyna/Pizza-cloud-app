package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.Ingredient;
import com.pizzacloud.pizza.Pizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Repository
public class JdbcPizzaRepository implements PizzaRepository {
  private final JdbcTemplate jdbc;

  public JdbcPizzaRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Pizza save(Pizza pizza) {
    long pizzaId = savePizzaInfo(pizza);
    pizza.setId(pizzaId);
    for (Ingredient ingredient : pizza.getIngredients()) {
      saveIngredientToPizza(ingredient, pizzaId);
    }

    return pizza;
  }

  private long savePizzaInfo(Pizza pizza) {
    final String INSERT_INTO_PIZZA = "INSERT INTO Pizza (name, createdAt) values (?,?)";

    pizza.setCreatedAt(new Date());
    PreparedStatementCreator psc =
        new PreparedStatementCreatorFactory(INSERT_INTO_PIZZA, Types.VARCHAR, Types.TIMESTAMP)
            .newPreparedStatementCreator(
                Arrays.asList(pizza.getName(), new Timestamp(pizza.getCreatedAt().getTime())));

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbc.update(psc, keyHolder);

    return Objects.requireNonNull(keyHolder.getKey()).longValue();
  }

  private void saveIngredientToPizza(Ingredient ingredient, long pizzaId) {
    final String INSERT_INTO_PIZZA_INGREDIENTS =
        "INSERT INTO Pizza_Ingredients (pizza, ingredient) values (?,?)";

    jdbc.update(INSERT_INTO_PIZZA_INGREDIENTS, pizzaId, ingredient.getId());
  }
}
