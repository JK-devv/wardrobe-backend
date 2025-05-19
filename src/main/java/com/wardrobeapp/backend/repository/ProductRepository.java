package com.wardrobeapp.backend.repository;

import com.wardrobeapp.backend.repository.model.ProductResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ProductRepository extends JpaRepository<ProductResource, UUID> {
}
