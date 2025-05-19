package com.wardrobeapp.backend.service.impl;

import com.wardrobeapp.backend.model.dto.ProductDto;
import com.wardrobeapp.backend.service.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class HMParsersImpl implements Parser {
    private static final String IMG_ATTRIBUTE = "img";
    private static final String SRC_SET = "srcset";
    private static final String HM_DOMAIN = "hm.com";
    private static final String BRAND_NAME = "HM";
    private static final String H1 = "h1";


    @Override
    public boolean support(String url) {
        return url.contains(HM_DOMAIN);
    }

    @Override
    public ProductDto parseProduct(String link) {
        String imageLinkHighestQuality;
        String name;
        try {
            Document doc = Jsoup.connect(link).get();

            Attribute imageAttribute = doc
                    .selectFirst(IMG_ATTRIBUTE)
                    .attribute(SRC_SET);
            Element h1 = doc.selectFirst(H1);
            String attributeString = imageAttribute.toString();

            // looking for high quality link image
            String[] images = attributeString.split(", ");
            imageLinkHighestQuality = images[images.length - 1];
            name = h1.text();

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse image link from : " + link, e);
        }
        return new ProductDto(
                UUID.randomUUID(),
                name,
                BRAND_NAME,
                imageLinkHighestQuality);
    }
}
