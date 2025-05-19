package com.wardrobeapp.backend.model.dto;

import com.wardrobeapp.backend.repository.model.Size;
import com.wardrobeapp.backend.repository.model.Type;

import java.util.List;

public record ProductFilterRequest(String name,
                                   String brand,
                                   Type type,
                                   // if true - filter for low price first
                                   // if false - filter for higher price first
                                   boolean isPriceLow,
                                   List<Size> availableSizeRange) {
}
