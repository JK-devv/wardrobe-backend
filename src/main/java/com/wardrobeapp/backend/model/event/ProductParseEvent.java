package com.wardrobeapp.backend.model.event;

import com.wardrobeapp.backend.model.Product;
import com.wardrobeapp.backend.model.dto.ProductDto;
import org.springframework.context.ApplicationEvent;

public class ProductParseEvent extends ApplicationEvent {
    private ProductDto product;

    public ProductParseEvent(Object source, ProductDto product) {
        super(source);
        this.product = product;
    }

    public ProductDto getProduct() {
        return product;
    }
}
