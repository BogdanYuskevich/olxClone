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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productServices;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productServices.getList(title));
        model.addAttribute("user", productServices.getUserByPrincipal(principal));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable(value = "id") Long id, Model model) {
        Product product = productServices.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("imagines", product.getImagines());
        return "product-info";
    }

    @PostMapping("product/create")
    public String createProduct(Product product, @RequestParam(name = "file1") MultipartFile file1,
                                @RequestParam(name = "file2") MultipartFile file2,
                                @RequestParam(name = "file3") MultipartFile file3,
                                Principal principal) throws IOException {
        productServices.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id) {
        productServices.deleteById(id);
        return "redirect:/";
    }
}
