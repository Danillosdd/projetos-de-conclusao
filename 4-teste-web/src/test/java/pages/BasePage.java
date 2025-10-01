package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navegarPara(String url) {
        driver.get(url);
    }
    
    public String obterTituloAtual() {
        return driver.getTitle();
    }
    
    public String obterUrlAtual() {
        return driver.getCurrentUrl();
    }
}