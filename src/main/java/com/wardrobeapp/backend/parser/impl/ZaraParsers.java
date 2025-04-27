package com.wardrobeapp.backend.parser.impl;

import com.wardrobeapp.backend.parser.Parser;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ZaraParsers implements Parser {
    private final WebDriver driver;
    private static final Logger LOG = LoggerFactory.getLogger(ZaraParsers.class);
    private static final String ZARA_PATH = "zara.com";
    private static final String IMG_SELECTOR = "img";
    private static final String SRC_ATTRIBUTE = "src";


    public ZaraParsers() {
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
        String imageLink = null;
        try {
            driver.get(link);
            WebElement imageElement = driver.findElement(By.cssSelector(IMG_SELECTOR));
            imageLink = imageElement.getAttribute(SRC_ATTRIBUTE);

        } catch (Exception e) {
            LOG.error(String.format("Exception happened while parsing page : %s", e.getMessage()));
        } finally {
            driver.quit();
        }
        return imageLink;
    }

    @Override
    public boolean support(String url) {
        return url.contains(ZARA_PATH);
    }
}
