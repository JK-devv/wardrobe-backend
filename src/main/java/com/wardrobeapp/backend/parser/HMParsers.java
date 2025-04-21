package com.wardrobeapp.backend.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HMParsers implements Parser {
    private static final String IMG_ATTRIBUTE = "img";
    private static final String SRCSERET = "srcset";

    @Override
    public String parseImage(String link) {
        String imageLinkHighestQuality;
        try {
            Attribute attribute = Jsoup.connect(link)
                    .get()
                    .selectFirst(IMG_ATTRIBUTE)
                    .attribute(SRCSERET);
            String attributeString = attribute.toString();
            // looking for high quality link image
            String[] images = attributeString.split(", ");
            imageLinkHighestQuality = images[images.length - 1];
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse image link from : " + link, e);
        }
        return imageLinkHighestQuality;
    }
}
