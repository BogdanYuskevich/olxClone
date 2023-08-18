package com.buyit.olxclone.models;

import lombok.*;

@Data
@AllArgsConstructor
public class Product {
    private Long Id;
    private String title;
    private String desc;
    private int price;
    private String town;
    private String author;
}
