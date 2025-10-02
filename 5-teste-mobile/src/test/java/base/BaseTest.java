package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected AppiumDriver<MobileElement> driver;
    
    // Configurações do SauceLabs
    private static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
    private static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    private static final String SAUCE_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        
        // Configurações do dispositivo para SauceLabs
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("deviceName", "Samsung Galaxy S21 FE 5G GoogleAPI Emulator");
        caps.setCapability("automationName", "UiAutomator2");
        
        // Configurações do app da Calculadora do Google
        caps.setCapability("appPackage", "com.google.android.calculator");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        
        // Configurações do SauceLabs
        caps.setCapability("sauce:options", getSauceOptions());
        
        // URL do SauceLabs ou Appium local
        String hubUrl = (SAUCE_USERNAME != null && SAUCE_ACCESS_KEY != null) ? 
            SAUCE_URL : "http://localhost:4723/wd/hub";
        
        System.out.println("🚀 Conectando ao Appium em: " + hubUrl);
        
        driver = new AndroidDriver<>(new URL(hubUrl), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        System.out.println("📱 Sessão iniciada com sucesso!");
    }
    
    private Object getSauceOptions() {
        java.util.Map<String, Object> sauceOptions = new java.util.HashMap<>();
        sauceOptions.put("username", SAUCE_USERNAME);
        sauceOptions.put("accessKey", SAUCE_ACCESS_KEY);
        sauceOptions.put("build", "Calculadora Google - Teste Mobile");
        sauceOptions.put("name", "Testes de Soma com Page Objects e CSV");
        return sauceOptions;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("🔚 Finalizando sessão...");
            driver.quit();
        }
    }
}