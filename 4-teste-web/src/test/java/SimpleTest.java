import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SimpleTest {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void testComprarProdutoSimples() {
        // Acessa o site
        driver.get("https://www.saucedemo.com/");
        
        // Faz login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        // Aguarda carregamento da página de produtos
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_item")));
        
        // Seleciona o produto "Sauce Labs Backpack"
        String produtoSelecionado = "Sauce Labs Backpack";
        WebElement produto = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + produtoSelecionado + "']"));
        
        // Captura nome e preço na tela de produtos
        String nomeNaListagem = produto.getText();
        WebElement precoElemento = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + produtoSelecionado + "']/ancestor::div[@class='inventory_item_label']/following-sibling::div//div[@class='inventory_item_price']"));
        String precoNaListagem = precoElemento.getText();
        
        System.out.println("Produto na listagem: " + nomeNaListagem + " - Preço: " + precoNaListagem);
        
        // Clica no produto para ver detalhes
        produto.click();
        
        // Aguarda carregamento da página de detalhes
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_details_name")));
        
        // Captura nome e preço na tela de detalhes
        String nomeNosDetalhes = driver.findElement(By.className("inventory_details_name")).getText();
        String precoNosDetalhes = driver.findElement(By.className("inventory_details_price")).getText();
        
        System.out.println("Produto nos detalhes: " + nomeNosDetalhes + " - Preço: " + precoNosDetalhes);
        
        // Valida se nome e preço são os mesmos
        assertEquals(nomeNaListagem, nomeNosDetalhes, "Nome do produto deve ser o mesmo na listagem e nos detalhes");
        assertEquals(precoNaListagem, precoNosDetalhes, "Preço do produto deve ser o mesmo na listagem e nos detalhes");
        
        // Adiciona ao carrinho
        driver.findElement(By.id("add-to-cart")).click();
        
        // Vai para o carrinho
        driver.findElement(By.className("shopping_cart_link")).click();
        
        // Aguarda carregamento da página do carrinho
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart_item")));
        
        // Captura nome e preço no carrinho
        String nomeNoCarrinho = driver.findElement(By.className("inventory_item_name")).getText();
        String precoNoCarrinho = driver.findElement(By.className("inventory_item_price")).getText();
        
        System.out.println("Produto no carrinho: " + nomeNoCarrinho + " - Preço: " + precoNoCarrinho);
        
        // Valida se nome e preço são os mesmos no carrinho
        assertEquals(nomeNaListagem, nomeNoCarrinho, "Nome do produto deve ser o mesmo na listagem e no carrinho");
        assertEquals(precoNaListagem, precoNoCarrinho, "Preço do produto deve ser o mesmo na listagem e no carrinho");
        
        System.out.println("✅ Teste concluído com sucesso! O produto manteve o mesmo nome e preço em todas as telas.");
    }
}