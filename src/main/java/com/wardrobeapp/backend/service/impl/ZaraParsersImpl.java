package com.wardrobeapp.backend.service.impl;

import com.wardrobeapp.backend.model.dto.ProductDto;
import com.wardrobeapp.backend.model.event.ProductParseEvent;
import com.wardrobeapp.backend.service.Parser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class ZaraParsersImpl implements Parser {
    private final WebDriver driver;
    private static final Logger LOG = LoggerFactory.getLogger(ZaraParsersImpl.class);
    private static final String ZARA_PATH = "zara.com";
    private static final String IMG_SELECTOR = "img";
    private static final String SRC_ATTRIBUTE = "src";
    private static final String BRAND_NAME = "zara";
    private static final String H1 = "h1";

    public ZaraParsersImpl() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.91 Safari/537.36");

        this.driver = new ChromeDriver(options);
    }

    @Override
    public ProductDto parseProduct(String link) {
        String imageLink = null;
        String productName = null;
        try {
            driver.get(link);
            WebElement imageElement = driver.findElement(By.cssSelector(IMG_SELECTOR));
            imageLink = imageElement.getAttribute(SRC_ATTRIBUTE);

            List<WebElement> h1 = driver.findElements(By.tagName(H1));
            productName = h1.stream()
                    .map(WebElement::getText)
                    .filter(text -> !text.isBlank())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Somethings went wrong"));

        } catch (Exception e) {
            LOG.error(String.format("Exception happened while parsing page : %s", e.getMessage()));
        } finally {
            driver.quit();
        }
        return new ProductDto(UUID.randomUUID(), productName, BRAND_NAME, imageLink);

    }

    @Override
    public boolean support(String url) {
        return url.contains(ZARA_PATH);
    }
}
