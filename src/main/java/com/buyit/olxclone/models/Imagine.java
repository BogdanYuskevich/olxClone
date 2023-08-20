package com.buyit.olxclone.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Imagine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "Id")
    private Long Id;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "originalFileName")
    private String originalFileName;
    @Column(name = "size")
    private Long size;
    @Column(name = "contentType")
    private String contentType;
    @Column(name = "previewImage")
    private boolean previewImage;
    @Lob
    @Column(name = "bytes")
    private byte[] bytes;
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private Product product;
}
