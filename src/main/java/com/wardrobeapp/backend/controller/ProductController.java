package com.wardrobeapp.backend.controller;

import com.wardrobeapp.backend.model.Product;
import com.wardrobeapp.backend.model.dto.ProductFilterRequest;
import com.wardrobeapp.backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll(@RequestBody(required = false)
                                ProductFilterRequest productFilterRequest) {
        return service.findAll(productFilterRequest);
    }


    @GetMapping("/{id}")
    public Product getById(@PathVariable UUID id) {
        return service.getById(id);
    }
}
