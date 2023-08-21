package com.buyit.olxclone.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;
    @Column(name = "Title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "town")
    private String town;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Imagine> imagines = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct(Imagine imagine) {
        imagine.setProduct(this);
        imagines.add(imagine);
    }
}
