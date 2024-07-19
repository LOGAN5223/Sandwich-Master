package ru.pract.sandwichcloud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Sandwich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must have at least 1 symbol")
    @Column(name = "sandwich_name")
    private String sandwichName;

    private Date created_at = new Date();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sandwich_order_id", referencedColumnName = "id")
    private SandwichOrder sandwich_order_id;

    @OneToMany(mappedBy = "sandwich_id", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<SandwichIngredient> sandwichIngredients;

    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }


}
