package com.buyit.olxclone.controllers;

import com.buyit.olxclone.models.Product;
import com.buyit.olxclone.services.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @GetMapping("/")
    public String products(@RequestParam(name = "title",required = false) String title,Model model) {
        model.addAttribute("products", productServices.getList(title));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable(value = "id") Long id, Model model) {

        model.addAttribute("product", productServices.getProductById(id));
        return "product-info";
    }

    @PostMapping("product/create")
    public String createProduct(Product product) {
        productServices.saveProduct(product);
        return "redirect:/";
    }

    @PostMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productServices.deleteById(id);
        return "redirect:/";
    }
}
