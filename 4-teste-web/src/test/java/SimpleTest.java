import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTest {
    
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        // Configurações baseadas nos projetos que funcionam corretamente
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        
        // Flags específicas para desabilitar modal de senha (baseado nos projetos do GitHub)
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-features=PasswordManager,AutofillServerCommunication");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--user-data-dir=/tmp/chrome-test-profile");
        options.addArguments("--incognito");
        options.addArguments("--profile-directory=Default");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        
        // Desabilita serviços de senha via preferências (CHAVE DO SUCESSO!)
        java.util.Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        System.out.println("✅ Chrome iniciado com configurações para prevenir modal de senha");
    }

    @Test
    public void testeCompraDeProduto() {
        // Acessa a página de login
        driver.get("https://www.saucedemo.com");
        
        // Faz login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        // Aguarda a página de produtos carregar
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_item")));
        
        // Coleta informações do produto na listagem
        WebElement produto = driver.findElement(By.xpath("//div[@class='inventory_item'][1]"));
        String nomeNaListagem = produto.findElement(By.className("inventory_item_name")).getText();
        String precoNaListagem = produto.findElement(By.className("inventory_item_price")).getText();
        
        System.out.println("Produto na listagem: " + nomeNaListagem + " - Preço: " + precoNaListagem);
        
        // Clica no produto para ir aos detalhes
        produto.findElement(By.className("inventory_item_name")).click();
        
        // Aguarda navegação para detalhes
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_details_name")));
        System.out.println("✅ Navegação para detalhes bem-sucedida");
        
        // Coleta informações do produto nos detalhes
        String nomeNosDetalhes = driver.findElement(By.className("inventory_details_name")).getText();
        String precoNosDetalhes = driver.findElement(By.className("inventory_details_price")).getText();
        
        System.out.println("Produto nos detalhes: " + nomeNosDetalhes + " - Preço: " + precoNosDetalhes);
        
        // Valida se as informações são as mesmas
        assertEquals(nomeNaListagem, nomeNosDetalhes, "Nome do produto deve ser o mesmo na listagem e nos detalhes");
        assertEquals(precoNaListagem, precoNosDetalhes, "Preço do produto deve ser o mesmo na listagem e nos detalhes");
        
        // Adiciona ao carrinho
        driver.findElement(By.id("add-to-cart")).click();
        
        // Vai para o carrinho
        driver.findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart_item")));
        
        // Coleta informações do produto no carrinho
        String nomeNoCarrinho = driver.findElement(By.className("inventory_item_name")).getText();
        String precoNoCarrinho = driver.findElement(By.className("inventory_item_price")).getText();
        
        System.out.println("Produto no carrinho: " + nomeNoCarrinho + " - Preço: " + precoNoCarrinho);
        
        // Validações finais
        assertEquals(nomeNaListagem, nomeNoCarrinho, "Nome do produto deve ser o mesmo na listagem e no carrinho");
        assertEquals(precoNaListagem, precoNoCarrinho, "Preço do produto deve ser o mesmo na listagem e no carrinho");
        
        System.out.println("✅ Teste concluído com sucesso! O produto manteve o mesmo nome e preço em todas as telas.");
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
