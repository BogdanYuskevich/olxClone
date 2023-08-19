package com.buyit.olxclone.services;

import com.buyit.olxclone.models.Product;
import com.buyit.olxclone.repos.ProductRepos;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServices {
    private final ProductRepos productRepos;


    public List<Product> getList(String title) {
        return productRepos.findAll();
    }

    public void saveProduct(Product product) {
        log.info("Saved new {}",product);
        productRepos.save(product);
    }

    public void deleteById(Long id) {
        log.info("Deleted product at id = "+id);
        productRepos.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepos.findById(id).orElse(null);
    }
}
