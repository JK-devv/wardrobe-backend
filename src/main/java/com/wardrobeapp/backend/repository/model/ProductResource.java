package com.wardrobeapp.backend.repository.model;

import com.wardrobeapp.backend.model.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductResource {
    @Id
    private UUID id;
    private String name;
    private String brand;
    private Type type;
    @Column(name = "affiliate_link")
    private String affiliateLink;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "size_chart")
    private List<Size> sizeChart;

    @Column(name = "available_size_range")
    @Enumerated(EnumType.STRING)
    private List<Size> availableSizeRange;
    private String description;
    @Column(name = "image_url")
    private String imageLink;
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public ProductResource(UUID id, String name, String brand, Type type, BigDecimal price, List<Size> sizeChart, List<Size> availableSizeRange, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.price = price;
        this.sizeChart = sizeChart;
        this.availableSizeRange = availableSizeRange;
        this.description = description;
    }

    public Product toApiModel() {
        return new Product(this.id,
                this.name,
                this.brand,
                this.type,
                this.affiliateLink,
                this.price,
                this.sizeChart,
                this.availableSizeRange,
                this.description,
                this.imageLink,
                this.createdAt);

    }
}
