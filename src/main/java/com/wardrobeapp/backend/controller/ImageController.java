package com.wardrobeapp.backend.controller;

import com.wardrobeapp.backend.cloudinary.CloudinaryService;
import com.wardrobeapp.backend.parser.Parser;
import com.wardrobeapp.backend.parser.ParserStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/images")
public class ImageController {
    private final CloudinaryService cloudinaryService;
    private final ParserStrategy strategy;

    public ImageController(CloudinaryService cloudinaryService, ParserStrategy strategy) {
        this.cloudinaryService = cloudinaryService;
        this.strategy = strategy;
    }


    @PostMapping("/upload")
    public void parseImage(@RequestParam("link") String siteLink) {
        Parser parser = strategy.resolve(siteLink);
        String imageLink = parser.parseImage(siteLink);
        cloudinaryService.uploadImage(imageLink);

    }
}
