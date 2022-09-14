package com.pizzacloud.pizza.data;

import com.pizzacloud.pizza.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private final JdbcTemplate jdbc;

  public JdbcIngredientRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Iterable<Ingredient> findAll() {
    final String SELECT_INGREDIENTS = "SELECT id, name, type from Ingredient";
    return jdbc.query(SELECT_INGREDIENTS, this::mapRowToIngredient);
  }

  private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
    return new Ingredient(
        rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
  }

  @Override
  public Ingredient findById(String id) {
    final String SELECT_INGREDIENT_BY_ID = "SELECT id, name, type from Ingredient WHERE id=?";
    return jdbc.queryForObject(SELECT_INGREDIENT_BY_ID, this::mapRowToIngredient, id);
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    final String INSERT_INGREDIENT = "INSERT INTO Ingredient (id, name, type) values (?, ?, ?)";
    jdbc.update(INSERT_INGREDIENT, ingredient.getId(), ingredient.getName(), ingredient.getType());
    return ingredient;
  }
}
