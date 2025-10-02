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
        
        // ✅ CORREÇÃO: Configuração baseada no projeto calculadora142 que funciona
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "9.0");  // Volta para 9.0
        caps.setCapability("appium:deviceName", "Google Pixel 3 GoogleAPI Emulator");  // Nome válido
        caps.setCapability("appium:automationName", "UiAutomator2");
        
        // A capability 'app' DEVE estar no nível raiz
        caps.setCapability("app", "storage:filename=Calculator_8.6.1.apk");
        
        // Configurações específicas do SauceLabs usando baseOptions
        caps.setCapability("sauce:options", getBaseOptions());
        
        System.out.println("📱 Conectando ao SauceLabs...");
        driver = new AndroidDriver(new URL(SAUCE_URL), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        System.out.println("✅ Conectado ao SauceLabs com sucesso!");
    }
    
    private Object getBaseOptions() {
        java.util.Map<String, Object> baseOptions = new java.util.HashMap<>();
        baseOptions.put("username", SAUCE_USERNAME);
        baseOptions.put("accessKey", SAUCE_ACCESS_KEY);
        baseOptions.put("build", "Calculadora Google - Teste Mobile v1.0");
        baseOptions.put("name", "Testes de Soma com Page Objects e CSV");
        baseOptions.put("deviceOrientation", "portrait");
        // ✅ CORREÇÃO: Usar versão compatível com Android 9.0, sem especificar appiumVersion
        // baseOptions.put("appiumVersion", "2.0.0");  // Removido - deixar SauceLabs escolher
        return baseOptions;
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