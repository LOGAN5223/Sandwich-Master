package ru.pract.sandwichcloud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.pract.sandwichcloud.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
