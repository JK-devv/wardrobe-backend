package com.wardrobeapp.backend.service;

import com.wardrobeapp.backend.model.dto.ProductFilterRequest;
import com.wardrobeapp.backend.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> findAll(ProductFilterRequest productFilterRequest);

    Product getById(UUID id);
}
