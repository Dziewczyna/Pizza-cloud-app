package com.pizzacloud.pizza;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pizza {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(min = 5, message = "Nazwa musi składać się z przynajmniej 5 znaków.")
  private String name;

  private Date createdAt;

  @ManyToMany(targetEntity = Ingredient.class)
  @Size(min = 1, message = "Musisz wybrać przynajmniej jeden składnik.")
  private List<Ingredient> ingredients;

  @PrePersist
  void createdAt() {
    this.createdAt = new Date();
  }
}
