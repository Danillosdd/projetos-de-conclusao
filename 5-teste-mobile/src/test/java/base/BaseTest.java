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
        
        // Configurações básicas da plataforma - DISPOSITIVOS VÁLIDOS
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:deviceName", "Google Pixel 6 GoogleAPI Emulator");
        caps.setCapability("appium:automationName", "UiAutomator2");
        
        // Usar calculadora padrão do Android
        caps.setCapability("browserName", ""); // Necessário para apps nativos
        caps.setCapability("appium:appPackage", "com.google.android.calculator");
        caps.setCapability("appium:appActivity", "com.android.calculator2.Calculator");
        caps.setCapability("appium:noReset", true);
        caps.setCapability("appium:autoGrantPermissions", true);
        
        // Configurações adicionais
        caps.setCapability("appium:newCommandTimeout", 300);
        
        // Configurações específicas do SauceLabs
        caps.setCapability("sauce:options", getSauceOptions());
        
        System.out.println("📱 Conectando ao SauceLabs...");
        driver = new AndroidDriver(new URL(SAUCE_URL), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        System.out.println("✅ Conectado ao SauceLabs com sucesso!");
    }
    
    private Object getSauceOptions() {
        java.util.Map<String, Object> sauceOptions = new java.util.HashMap<>();
        sauceOptions.put("username", SAUCE_USERNAME);
        sauceOptions.put("accessKey", SAUCE_ACCESS_KEY);
        sauceOptions.put("build", "Calculadora Google - Teste Mobile v1.0");
        sauceOptions.put("name", "Testes de Soma com Page Objects e CSV");
        sauceOptions.put("deviceOrientation", "portrait");
        sauceOptions.put("appiumVersion", "1.22.3"); // Versão estável
        return sauceOptions;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("🔚 Finalizando sessão SauceLabs...");
            driver.quit();
            System.out.println("✅ Sessão SauceLabs finalizada!");ntln("✅ Sessão SauceLabs finalizada!");
        }
    }
}