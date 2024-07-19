package ru.pract.sandwichcloud.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sandwich.orders")
@Data
public class OrderProps {
    private int pageSize = 20;
}
