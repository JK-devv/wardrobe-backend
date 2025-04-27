package com.wardrobeapp.backend.parser.impl;

import com.wardrobeapp.backend.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HMParsers implements Parser {
    private static final String IMG_ATTRIBUTE = "img";
    private static final String SRC_SET = "srcset";
    private static final String HM_DOMAIN = "hm.com";


    @Override
    public boolean support(String url) {
        return url.contains(HM_DOMAIN);
    }

    @Override
    public String parseImage(String link) {
        String imageLinkHighestQuality;
        try {
            Attribute attribute = Jsoup.connect(link)
                    .get()
                    .selectFirst(IMG_ATTRIBUTE)
                    .attribute(SRC_SET);
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
