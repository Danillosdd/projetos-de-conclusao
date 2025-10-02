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
    
    // Configurações do SauceLabs (igual ao seu projeto calculadora142)
    private static final String SAUCE_USERNAME = "oauth-danillosdd-93c82";
    private static final String SAUCE_ACCESS_KEY = "42940e59-9c8b-472e-a6b9-a9f81b351788";
    private static final String SAUCE_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        System.out.println("🚀 Configurando SauceLabs para Calculadora do Google...");
        
        DesiredCapabilities caps = new DesiredCapabilities();
        
        // Configurações do dispositivo no SauceLabs
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "11.0");
        caps.setCapability("appium:deviceName", "Samsung Galaxy S21 FE 5G GoogleAPI Emulator");
        caps.setCapability("appium:automationName", "UiAutomator2");
        
        // App da Calculadora do Google
        caps.setCapability("appium:appPackage", "com.google.android.calculator");
        caps.setCapability("appium:appActivity", "com.android.calculator2.Calculator");
        
        // Configurações adicionais
        caps.setCapability("appium:noReset", true);
        caps.setCapability("appium:newCommandTimeout", 300);
        
        // Configurações específicas do SauceLabs
        caps.setCapability("sauce:options", getSauceOptions());
        
        System.out.println("📱 Conectando ao SauceLabs...");
        driver = new AndroidDriver<>(new URL(SAUCE_URL), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        
        System.out.println("✅ Conectado ao SauceLabs com sucesso!");
    }
    
    private Object getSauceOptions() {
        java.util.Map<String, Object> sauceOptions = new java.util.HashMap<>();
        sauceOptions.put("username", SAUCE_USERNAME);
        sauceOptions.put("accessKey", SAUCE_ACCESS_KEY);
        sauceOptions.put("build", "Calculadora Google - Teste Mobile v1.0");
        sauceOptions.put("name", "Testes de Soma com Page Objects e CSV");
        sauceOptions.put("deviceOrientation", "portrait");
        sauceOptions.put("appiumVersion", "1.22.0");
        return sauceOptions;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("🔚 Finalizando sessão SauceLabs...");
            driver.quit();
            System.out.println("✅ Sessão SauceLabs finalizada!");
        }
    }
}