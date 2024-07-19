package ru.pract.sandwichcloud.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "ingredient")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    @Enumerated(EnumType.STRING)
    private final Type type;
    private final String img;
    public enum Type{
        BREAD, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    @OneToMany(mappedBy = "ingredients_id",cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<SandwichIngredient> ingredient_sandwich_ingredient;
}
