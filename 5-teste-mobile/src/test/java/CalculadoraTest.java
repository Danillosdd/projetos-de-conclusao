//mvn clean test
//mvn test -Dtest=Calculadora

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;

public class CalculadoraTest {

    private AndroidDriver driver;

    private URL getUrl() {
        try {
            return new URL(
                    "https://oauth-danillo-75a98:49d13ae3-a890-47ed-813b-6ab9262151c7@ondemand.us-west-1.saucelabs.com:443/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @BeforeEach
    public void setUp() {
        var options = new BaseOptions()
                .amend("platformName", "Android")
                .amend("appium:platformVersion", "9.0")
                .amend("appium:deviceName", "Samsung Galaxy S9 FHD GoogleAPI Emulator")
                .amend("appium:deviceOrientation", "portrait")
                .amend("appium:app", "storage:filename=Calculator_8.6.1.apk")
                .amend("appium:appPackage", "com.google.android.calculator")
                .amend("appium:appActivity", "com.android.calculator2.Calculator")
                .amend("appium:automationName", "UiAutomator2")
                .amend("browserName", "")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("sauce:options", Map.ofEntries(Map.entry("name", "Appium Desktop Session -- Vscode")))
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true)
                .amend("unhandledPromptBehavior", "ignore");

        driver = new AndroidDriver(this.getUrl(), options);
    }

    @Test
    public void testeCalculadoraSoma() throws InterruptedException {
        // Aguarda a calculadora carregar
        Thread.sleep(3000);

        // Realiza a operação 5 + 3 = 8
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/op_add")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/eq")).click();

        // Verifica o resultado
        var resultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
        assertEquals("8", resultado.getText());
    }

    @Test
    public void testeCalculadoraSubtracao() throws InterruptedException {
        // Aguarda a calculadora carregar
        Thread.sleep(3000);

        // Realiza a operação 9 - 4 = 5
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_9")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/op_sub")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_4")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/eq")).click();

        // Verifica o resultado
        var resultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
        assertEquals("5", resultado.getText());
    }

    @Test
    public void testeCalculadoraMultiplicacao() throws InterruptedException {
        // Aguarda a calculadora carregar
        Thread.sleep(3000);

        // Realiza a operação 6 × 7 = 42
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_6")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/op_mul")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_7")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/eq")).click();

        // Verifica o resultado
        var resultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
        assertEquals("42", resultado.getText());
    }

    @Test
    public void testeCalculadoraDivisao() throws InterruptedException {
        // Aguarda a calculadora carregar
        Thread.sleep(3000);

        // Realiza a operação 15 ÷ 3 = 5
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_1")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/op_div")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/eq")).click();

        // Verifica o resultado
        var resultado = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final"));
        assertEquals("5", resultado.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
