package ru.pract.sandwichcloud.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.pract.sandwichcloud.entity.Ingredient;
import ru.pract.sandwichcloud.entity.Ingredient.Type;
import ru.pract.sandwichcloud.entity.Sandwich;
import ru.pract.sandwichcloud.entity.SandwichOrder;
import ru.pract.sandwichcloud.repository.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("sandwichOrder")
public class DesignSandwichController {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignSandwichController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }
    @ModelAttribute(name = "sandwichOrder")
    public SandwichOrder order(){
        return new SandwichOrder();
    }

    @ModelAttribute(name = "sandwich")
    public Sandwich sandwich(){
        return new Sandwich();
    }

    @GetMapping
    public String showDesignForm(){
        return "sandwichDesign";
    }

    @PostMapping
    public String processSandwich(@Valid Sandwich sandwich, Errors errors, @ModelAttribute("sandwichOrder") SandwichOrder sandwichOrder){
        if(errors.hasErrors()){
            return "sandwichDesign";
        }

        sandwichOrder.addSandwich(sandwich);

        return "redirect:/orders/current";
    }

}
