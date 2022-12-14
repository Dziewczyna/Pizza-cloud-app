package com.pizzacloud.pizza;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Pizza_Order")
public class Order implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Date placedAt;
  private String name;
  private String street;
  private String city;
  private String state;
  private String zip;
  private String ccNumber;
  private String ccExpiration;
  private String ccCVV;

  @ManyToMany(targetEntity = Pizza.class)
  private List<Pizza> pizzas = new ArrayList<>();

  public void addDesign(Pizza design) {
    this.pizzas.add(design);
  }

  @PrePersist
  void placedAt() {
    this.placedAt = new Date();
  }
}
