package com.wardrobeapp.backend.cloudinary;

import com.cloudinary.Cloudinary;
import com.wardrobeapp.backend.utils.Encryption;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

import static com.cloudinary.utils.ObjectUtils.asMap;
import static com.cloudinary.utils.ObjectUtils.emptyMap;

@Log4j
@Service
public class CloudinaryService {
    private static final Logger LOG = Logger.getLogger(CloudinaryService.class.getName());
    private static final String CLOUDINARY_NAME = "dw4erlpei";
    private static final String CLOUD_NAME_PARAM = "cloud_name";
    private static final String API_KEY_PARAM = "api_key";
    private static final String API_SECRET_PARAM = "api_secret";
    public static final String SECURE_URL = "secure_url";
    @Value("${cloudinary.api.key}")
    private String cloudinaryKey;
    @Value("${cloudinary.api.secret}")
    private String cloudinarySecret;


    public String uploadProductsImage(String imageLink) {
        String cloudinaryLink = null;
        try {
            Cloudinary cloudinary = new Cloudinary(asMap(
                    CLOUD_NAME_PARAM, CLOUDINARY_NAME,
                    API_KEY_PARAM, Encryption.decrypt(cloudinaryKey),
                    API_SECRET_PARAM, Encryption.decrypt(cloudinarySecret)));
            cloudinaryLink = cloudinary.uploader().upload(imageLink, emptyMap())
                    .get(SECURE_URL).toString();

            LOG.info("Uploading is completed");
        } catch (IOException e) {
            throw new RuntimeException("Cloudinary upload failed", e);
        }

        return cloudinaryLink;

    }
}
