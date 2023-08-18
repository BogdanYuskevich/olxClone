package com.buyit.olxclone.services;

import com.buyit.olxclone.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service

public class ProductServices {
    private final List<Product> products = new ArrayList<>();
    private Long Id = 0L;

    {
        products.add(new Product(++Id, "Ps 5", "Its ps", 1000, "Galych", "Biba"));
        products.add(new Product(++Id, "Iphon", "Its iphon", 2900, "Galych", "Biba"));
    }

    public List<Product> getList() {
        return products;
    }

    public void saveProduct(Product product) {
        product.setId(++Id);
        products.add(product);
    }

    public void deleteById(Long id) {
        products.removeIf(product -> Objects.equals(product.getId(), id));
    }

    public Product getProductById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
