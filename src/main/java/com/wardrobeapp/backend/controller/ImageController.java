package com.wardrobeapp.backend.controller;

import com.wardrobeapp.backend.cloudinary.CloudinaryService;
import com.wardrobeapp.backend.parser.Parser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/images")
public class ImageController {
    private final CloudinaryService cloudinaryService;
    private final Parser parser;

    public ImageController(CloudinaryService cloudinaryService, Parser parser) {
        this.cloudinaryService = cloudinaryService;
        this.parser = parser;
    }


    @PostMapping("/upload")
    public void parseImage(@RequestParam("link") String siteLink) {
        String imageLing = parser.parseImage(siteLink);
        cloudinaryService.uploadImage(imageLing);

    }
}
