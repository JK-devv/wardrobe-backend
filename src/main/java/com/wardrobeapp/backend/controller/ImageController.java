package com.wardrobeapp.backend.controller;

import com.wardrobeapp.backend.cloudinary.CloudinaryService;
import com.wardrobeapp.backend.model.dto.ProductDto;
import com.wardrobeapp.backend.service.Parser;
import com.wardrobeapp.backend.service.ParserStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/images")
public class ImageController {
    private final CloudinaryService cloudinaryService;
    private final ParserStrategy strategy;
    private final ApplicationEventPublisher eventPublisher;

    public ImageController(CloudinaryService cloudinaryService, ParserStrategy strategy, ApplicationEventPublisher eventPublisher) {
        this.cloudinaryService = cloudinaryService;
        this.strategy = strategy;
        this.eventPublisher = eventPublisher;
    }


    @PostMapping("/upload")
    public void parseProduct(@RequestParam("link") String siteLink) {
        Parser parser = strategy.resolve(siteLink);
        ProductDto parsedProduct = parser.parseProduct(siteLink);
        cloudinaryService.uploadProductsImage(parsedProduct.getImageLink());
        //todo add imgae link to product
    }
}
