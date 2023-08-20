package com.buyit.olxclone.controllers;

import com.buyit.olxclone.models.Imagine;
import com.buyit.olxclone.repos.ImagineRepos;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImagineRepos imagineRepos;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) {
        Imagine image = imagineRepos.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}

