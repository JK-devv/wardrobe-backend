package com.wardrobeapp.backend.parser.impl;

import com.wardrobeapp.backend.parser.Parser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BershkaParser implements Parser {
    private static final String BERSHKA_DOMAIN = "bershka.com";
    private static final String FIND_BY_SELECTOR = "img[data-qa-anchor='pdpMainImage']";
    private static final String SRC_ATTRIBUTE = "src";
    private final WebDriver driver;

    public BershkaParser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.91 Safari/537.36");

        this.driver = new ChromeDriver(options);
    }

    @Override
    public String parseImage(String link) {
        try {
            driver.get(link);
            WebElement imageElement = driver.findElement(By.cssSelector(FIND_BY_SELECTOR));
            String imageLink = imageElement.getAttribute(SRC_ATTRIBUTE);

            if (imageLink == null || imageLink.isEmpty()) {
                throw new IllegalStateException("Image link is null or empty after parsing page: " + link);
            }

            return imageLink;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse image from page: " + link, e);
        } finally {
            driver.quit();
        }
    }


    @Override
    public boolean support(String url) {
        return url.contains(BERSHKA_DOMAIN);
    }
}
