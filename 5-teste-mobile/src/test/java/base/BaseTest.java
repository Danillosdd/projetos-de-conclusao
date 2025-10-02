package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
    protected AppiumDriver driver;
    
    // Configurações do SauceLabs
    private static final String SAUCE_USERNAME = "oauth-danillo-75a98";
    private static final String SAUCE_ACCESS_KEY = "49d13ae3-a890-47ed-813b-6ab9262151c7";
    private static final String SAUCE_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        System.out.println("🚀 Configurando SauceLabs para Calculadora do Google...");
        
        DesiredCapabilities caps = new DesiredCapabilities();
        
        // Capabilities básicas do Android
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "9.0");
        caps.setCapability("appium:deviceName", "Google Pixel 3 GoogleAPI Emulator");
        caps.setCapability("appium:automationName", "UiAutomator2");
        
        // Para apps nativos no SauceLabs, use app capability com storage
        caps.setCapability("app", "storage:filename=Calculator_8.6.1.apk");
        
        // Configurações específicas do SauceLabs
        caps.setCapability("sauce:options", getSauceOptions());
        
        System.out.println("📱 Conectando ao SauceLabs...");
        driver = new AndroidDriver(new URL(SAUCE_URL), caps);
        
        // Configuração de timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        System.out.println("✅ Conectado ao SauceLabs com sucesso!");
    }
    
    private Object getSauceOptions() {
        java.util.Map<String, Object> sauceOptions = new java.util.HashMap<>();
        sauceOptions.put("username", SAUCE_USERNAME);
        sauceOptions.put("accessKey", SAUCE_ACCESS_KEY);
        sauceOptions.put("build", "Calculadora Google - Build " + System.currentTimeMillis());
        sauceOptions.put("name", "Teste Calculadora Android - " + java.time.LocalDateTime.now());
        sauceOptions.put("deviceOrientation", "portrait");
        
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