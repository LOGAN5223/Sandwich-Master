package ru.pract.sandwichcloud.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class SandwichOrder implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placedAt = new Date();

    @NotBlank(message = "Delivery name is required!")
    private String delivery_name;

    @NotBlank(message = "Delivery street is required!")
    private String delivery_street;

    @NotBlank(message = "Delivery city is required!")
    private String delivery_city;

    @NotBlank(message="State is required")
    private String delivery_state;

    @NotBlank(message="Zip code is required")
    private String delivery_zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String cc_number;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String cc_expiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String cc_cvv;

    @OneToMany(mappedBy = "sandwich_order_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Sandwich> sandwiches = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userTableId", referencedColumnName = "id")
    private UserTable userTableId;

    public void addSandwich(Sandwich sandwich){
        this.sandwiches.add(sandwich);
        sandwich.setSandwich_order_id(SandwichOrder.this);
    }

    @Transactional
    public void setUserTable(UserTable userTable){
        userTable.getSandwichOrders().add(SandwichOrder.this);
        this.setUserTableId(userTable);
    }
}
