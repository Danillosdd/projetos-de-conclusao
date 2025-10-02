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
    
    // Configurações do SauceLabs (substitua pelos seus valores)
    private static final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
    private static final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    private static final String SAUCE_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        System.out.println("🚀 Configurando o driver Appium...");
        
        DesiredCapabilities caps = new DesiredCapabilities();
        
        // Configurações básicas do dispositivo
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("deviceName", "Samsung Galaxy S21 FE 5G GoogleAPI Emulator");
        caps.setCapability("automationName", "UiAutomator2");
        
        // Configurações da Calculadora do Google
        caps.setCapability("appPackage", "com.google.android.calculator");
        caps.setCapability("appActivity", "com.android.calculator2.Calculator");
        
        // Configurações adicionais
        caps.setCapability("noReset", true);
        caps.setCapability("newCommandTimeout", 300);
        
        // Configurações do SauceLabs (se disponível)
        if (SAUCE_USERNAME != null && SAUCE_ACCESS_KEY != null) {
            caps.setCapability("sauce:options", getSauceOptions());
            driver = new AndroidDriver<>(new URL(SAUCE_URL), caps);
            System.out.println("📱 Conectado ao SauceLabs");
        } else {
            // Configuração local
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
            System.out.println("📱 Conectado ao Appium local");
        }
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("✅ Driver configurado com sucesso!");
    }
    
    private Object getSauceOptions() {
        java.util.Map<String, Object> sauceOptions = new java.util.HashMap<>();
        sauceOptions.put("username", SAUCE_USERNAME);
        sauceOptions.put("accessKey", SAUCE_ACCESS_KEY);
        sauceOptions.put("build", "Calculadora Google - Teste Mobile");
        sauceOptions.put("name", "Testes de Soma com Page Objects e CSV");
        sauceOptions.put("deviceOrientation", "portrait");
        return sauceOptions;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("🔚 Finalizando sessão do driver...");
            driver.quit();
            System.out.println("✅ Sessão finalizada!");
        }
    }
}