package com.buyit.olxclone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public class ProductController {
    @GetMapping("/")
    public String products() {
        return "products";
    }
}
