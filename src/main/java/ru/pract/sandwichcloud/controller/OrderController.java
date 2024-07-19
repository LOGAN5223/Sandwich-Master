package ru.pract.sandwichcloud.controller;

import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.pract.sandwichcloud.entity.SandwichOrder;
import ru.pract.sandwichcloud.entity.UserTable;
import ru.pract.sandwichcloud.repository.OrderRepository;
import ru.pract.sandwichcloud.web.OrderProps;

@Controller
@RequestMapping(value = "/orders", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@SessionAttributes(value = "sandwichOrder")
public class OrderController {

    private OrderRepository orderRepository;
    private OrderProps orderProps;
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @ModelAttribute("userTable")
    public UserTable userTable(){
        return (UserTable) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("orders/info")
    public String getOrders(){
        return "orderInfo";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal UserTable userTable, Model model){
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepository.findByUserTableIdOrderByPlacedAtDesc(userTable, pageable)
                );
        return "orderList";
    }

    @GetMapping(value = {"current", "/"})
    public String orderForm(){
        return "orderForm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteAllOrders(){
        orderRepository.deleteAll();
    }

    @PostMapping("/process")
    public String processOrder(@Valid SandwichOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal UserTable userTable){
        if (errors.hasErrors()){
            return "orderForm";
        }

        order.setUserTable(userTable);

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId){
        try{
            orderRepository.deleteById(orderId);
        }catch (EmptyResultDataAccessException e){}
    }

    @PatchMapping(path = "/orderId", consumes = "application/json")
    public SandwichOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody SandwichOrder patch){
        SandwichOrder order = orderRepository.findById(orderId).get();
        if (patch.getDelivery_name() != null) {
            order.setDelivery_name(patch.getDelivery_name());
        }
        if (patch.getDelivery_street() != null) {
            order.setDelivery_street(patch.getDelivery_street());
        }
        if (patch.getDelivery_city() != null) {
            order.setDelivery_city(patch.getDelivery_city());
        }
        if (patch.getDelivery_state() != null) {
            order.setDelivery_state(patch.getDelivery_state());
        }
        if (patch.getDelivery_zip() != null) {
            order.setDelivery_zip(patch.getDelivery_zip());
        }
        if (patch.getCc_number() != null) {
            order.setCc_number(patch.getCc_number());
        }
        if (patch.getCc_expiration() != null) {
            order.setCc_expiration(patch.getCc_expiration());
        }
        if (patch.getCc_cvv() != null) {
            order.setCc_cvv(patch.getCc_cvv());
        }
        return orderRepository.save(order);
    }

}
