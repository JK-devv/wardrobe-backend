package com.wardrobeapp.backend.model.event;

import com.wardrobeapp.backend.model.dto.ProductDto;
import org.springframework.context.ApplicationEvent;

public class ProductUpdateEvent extends ApplicationEvent {
    private ProductDto product;

    public ProductUpdateEvent(Object source, ProductDto productDto) {
        super(source);
        this.product = productDto;
    }

    public ProductDto getProduct() {
        return product;
    }
}
