package com.buyit.olxclone.repos;

import com.buyit.olxclone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepos extends JpaRepository<Product,Long> {
    List<Product> findByTitle(String title);
}
