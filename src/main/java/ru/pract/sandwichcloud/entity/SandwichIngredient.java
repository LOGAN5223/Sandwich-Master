package ru.pract.sandwichcloud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sandwich_ingredients")
public class SandwichIngredient {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sandwich_id", referencedColumnName = "id")
    private Sandwich sandwich_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ingredients_id", referencedColumnName = "id")
    private Ingredient ingredients_id;

}
