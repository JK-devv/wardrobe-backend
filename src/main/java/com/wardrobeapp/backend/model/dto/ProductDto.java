package com.wardrobeapp.backend.model.dto;

import com.wardrobeapp.backend.repository.model.Size;
import com.wardrobeapp.backend.repository.model.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductDto {
    private UUID id;

    private String name;
    private String brand;
    private Type type;
    private BigDecimal price;
    private List<Size> sizeList;
    private List<Size> availableSizeRange;
    private String description;
    private String imageLink;


    public ProductDto(UUID id, String name, String brand, String imageLink) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.imageLink = imageLink;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Size> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<Size> sizeList) {
        this.sizeList = sizeList;
    }

    public List<Size> getAvailableSizeRange() {
        return availableSizeRange;
    }

    public void setAvailableSizeRange(List<Size> availableSizeRange) {
        this.availableSizeRange = availableSizeRange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
