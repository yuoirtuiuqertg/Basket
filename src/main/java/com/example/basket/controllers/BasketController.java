package com.example.basket.controllers;

import com.example.basket.models.Basket;
import com.example.basket.services.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;


    @GetMapping("/basket")
    public String basket(@RequestParam(value = "name", required = false) String name,Model model) {
        model.addAttribute("products", basketService.listBasket(name));
        model.addAttribute("price", cost(name));
        model.addAttribute("quantity", quantity(name));
        return "basket";
    }
    @GetMapping("/basket/put")
    public String basketPage(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "price", required = false) int price,
                             @RequestParam(value = "quantity", required = false) int quantity, Model model) {

        Basket basket = new Basket();
        basket.setName(name);
        basket.setPrice(price);
        basket.setQuantity(quantity);
        basketService.saveBasket(basket);
        return "redirect:/basket";
    }

    @GetMapping("/basket/{id}")
    public String deleteProductById(@PathVariable Long id){
        basketService.deleteBasket(id);
        return "redirect:/basket";
    }

    public int cost (String name){
        List<Basket> baskets = basketService.listBasket(name);
        int cost = 0;
        if(baskets.size() == 0){
            return 0;
        } else {
            for (int i = 0; i < baskets.size(); i++) {
                cost += baskets.get(i).getPrice() * baskets.get(i).getQuantity();
            }
            return cost;
        }
    }

    public int quantity (String name){
        List<Basket> baskets = basketService.listBasket(name);
        int quantity = 0;
        if(baskets.size() == 0){
            return 0;
        } else {
            for (int i = 0; i < baskets.size(); i++) {
                quantity += baskets.get(i).getQuantity();
            }
            return quantity;
        }
    }


    @GetMapping ("/basket/delete")
    public String basketDelete(){
        basketService.deleteAllBasket();
        return "redirect:/basket";
    }
}
