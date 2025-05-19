package com.wardrobeapp.backend.service.impl;

import com.wardrobeapp.backend.model.dto.ProductDto;
import com.wardrobeapp.backend.model.event.ProductParseEvent;
import com.wardrobeapp.backend.model.dto.ProductFilterRequest;
import com.wardrobeapp.backend.model.Product;
import com.wardrobeapp.backend.model.event.ProductUpdateEvent;
import com.wardrobeapp.backend.repository.ProductRepository;
import com.wardrobeapp.backend.repository.model.ProductResource;
import com.wardrobeapp.backend.service.ProductService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    private final ApplicationEventPublisher eventPublisher;

    public ProductServiceImpl(ProductRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Product> findAll(ProductFilterRequest productFilterRequest) {
        // to do
        Specification<ProductResource> spec;
        if (productFilterRequest != null) {
            // spec =
        }
        return repository.findAll()
                .stream()
                .map(ProductResource::toApiModel)
                .toList();
    }

    @Override
    public Product getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find resource by id: " + id))
                .toApiModel();
    }


    @EventListener
    public void initProduct(ProductParseEvent productParseEvent) {
        ProductDto product = productParseEvent.getProduct();
        ProductResource productResource = new ProductResource(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getType() != null ? product.getType() : null,
                product.getPrice() != null ? product.getPrice() : null,
                product.getSizeList() != null ? product.getSizeList() : null,
                product.getAvailableSizeRange() != null ? product.getAvailableSizeRange() : null,
                product.getDescription() != null ? product.getDescription() : null);

        repository.save(productResource);

    }
}
