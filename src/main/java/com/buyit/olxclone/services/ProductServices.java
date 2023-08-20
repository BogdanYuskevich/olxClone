package com.buyit.olxclone.services;

import com.buyit.olxclone.models.Imagine;
import com.buyit.olxclone.models.Product;
import com.buyit.olxclone.repos.ProductRepos;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServices {
    private final ProductRepos productRepos;


    public List<Product> getList(String title) {
        if (title != null) return productRepos.findByTitle(title);
        return productRepos.findAll();
    }

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Imagine image1;
        Imagine image2;
        Imagine image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author: {}", product.getTitle(), product.getAuthor());
        Product productFromDb = productRepos.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImagines().get(0).getId());
        productRepos.save(product);
    }

    private Imagine toImageEntity(MultipartFile file1) throws IOException {
        Imagine imagine = new Imagine();
        imagine.setFileName(file1.getName());
        imagine.setOriginalFileName(file1.getOriginalFilename());
        imagine.setContentType(file1.getContentType());
        imagine.setSize(file1.getSize());
        imagine.setBytes(file1.getBytes());
        return imagine;
    }

    public void deleteById(Long id) {
        log.info("Deleted product at id = " + id);
        productRepos.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepos.findById(id).orElse(null);
    }
}
