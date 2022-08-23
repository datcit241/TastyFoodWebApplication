package com.tastyfoodwebapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {

    @RequestMapping(value="/cart/add-product/{productId}")
    public void addToCart(@PathVariable String productId) {

    }
}
