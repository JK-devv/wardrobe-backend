package com.wardrobeapp.backend.model;

import com.wardrobeapp.backend.repository.model.Size;
import com.wardrobeapp.backend.repository.model.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Product(UUID id,
                      String name,
                      String brand,
                      Type type,
                      String affiliateLink,
                      BigDecimal price,
                      List<Size> sizeChart,
                      List<Size> availableSizeRange,
                      String description,
                      String imageLink,
                      LocalDateTime createdAt) {
}
