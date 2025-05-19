package com.wardrobeapp.backend.service;

import com.wardrobeapp.backend.model.dto.ProductDto;

public interface Parser {
    ProductDto parseProduct(String link);

    boolean support(String url);
}
