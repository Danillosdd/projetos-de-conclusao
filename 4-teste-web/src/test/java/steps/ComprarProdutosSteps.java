package steps;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ComprarProdutosSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private String nomeProdutoCapturado;
    private String precoProdutoCapturado;
    
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        // Configurações para o Chrome ser mais estável
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Dado("que acesso o site SauceDemo")
    public void que_acesso_o_site_sauce_demo() {
        driver.get("https://www.saucedemo.com/");
    }
    
    @E("faço login com usuário {string} e senha {string}")
    public void faco_login_com_usuario_e_senha(String usuario, String senha) {
        driver.findElement(By.id("user-name")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(senha);
        driver.findElement(By.id("login-button")).click();
        
        // Aguarda carregamento da página de produtos
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_item")));
    }
    
    @Quando("seleciono o produto {string}")
    public void seleciono_o_produto(String nomeProduto) {
        WebElement produto = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + nomeProduto + "']"));
        produto.click();
    }
    
    @Então("valido que o nome {string} e preço {string} estão corretos na listagem")
    public void valido_que_o_nome_e_preco_estao_corretos_na_listagem(String nomeEsperado, String precoEsperado) {
        // Volta para a página de listagem se necessário
        if (!driver.getCurrentUrl().contains("inventory.html")) {
            driver.navigate().back();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_item")));
        }
        
        WebElement produto = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + nomeEsperado + "']"));
        nomeProdutoCapturado = produto.getText();
        
        WebElement precoElemento = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + nomeEsperado + "']/ancestor::div[@class='inventory_item_label']/following-sibling::div//div[@class='inventory_item_price']"));
        precoProdutoCapturado = precoElemento.getText();
        
        assertEquals(nomeEsperado, nomeProdutoCapturado, "Nome do produto na listagem deve estar correto");
        assertEquals(precoEsperado, precoProdutoCapturado, "Preço do produto na listagem deve estar correto");
        
        System.out.println("✅ Listagem - Produto: " + nomeProdutoCapturado + " - Preço: " + precoProdutoCapturado);
    }
    
    @E("vou para a página de detalhes do produto")
    public void vou_para_a_pagina_de_detalhes_do_produto() {
        WebElement produto = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + nomeProdutoCapturado + "']"));
        produto.click();
        
        // Aguarda carregamento da página de detalhes
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_details_name")));
    }
    
    @E("valido que o nome {string} e preço {string} estão corretos nos detalhes")
    public void valido_que_o_nome_e_preco_estao_corretos_nos_detalhes(String nomeEsperado, String precoEsperado) {
        String nomeNosDetalhes = driver.findElement(By.className("inventory_details_name")).getText();
        String precoNosDetalhes = driver.findElement(By.className("inventory_details_price")).getText();
        
        assertEquals(nomeEsperado, nomeNosDetalhes, "Nome do produto nos detalhes deve estar correto");
        assertEquals(precoEsperado, precoNosDetalhes, "Preço do produto nos detalhes deve estar correto");
        assertEquals(nomeProdutoCapturado, nomeNosDetalhes, "Nome deve ser consistente entre listagem e detalhes");
        assertEquals(precoProdutoCapturado, precoNosDetalhes, "Preço deve ser consistente entre listagem e detalhes");
        
        System.out.println("✅ Detalhes - Produto: " + nomeNosDetalhes + " - Preço: " + precoNosDetalhes);
    }
    
    @E("adiciono o produto ao carrinho")
    public void adiciono_o_produto_ao_carrinho() {
        driver.findElement(By.id("add-to-cart")).click();
    }
    
    @E("vou para o carrinho")
    public void vou_para_o_carrinho() {
        // Navega diretamente para a URL do carrinho
        driver.get("https://www.saucedemo.com/cart.html");
        
        // Aguarda carregamento da página do carrinho
        wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(By.className("cart_item")),
            ExpectedConditions.presenceOfElementLocated(By.className("cart_list"))
        ));
    }
    
    @Então("valido que o nome {string} e preço {string} estão corretos no carrinho")
    public void valido_que_o_nome_e_preco_estao_corretos_no_carrinho(String nomeEsperado, String precoEsperado) {
        String nomeNoCarrinho = driver.findElement(By.className("inventory_item_name")).getText();
        String precoNoCarrinho = driver.findElement(By.className("inventory_item_price")).getText();
        
        assertEquals(nomeEsperado, nomeNoCarrinho, "Nome do produto no carrinho deve estar correto");
        assertEquals(precoEsperado, precoNoCarrinho, "Preço do produto no carrinho deve estar correto");
        assertEquals(nomeProdutoCapturado, nomeNoCarrinho, "Nome deve ser consistente entre listagem e carrinho");
        assertEquals(precoProdutoCapturado, precoNoCarrinho, "Preço deve ser consistente entre listagem e carrinho");
        
        System.out.println("✅ Carrinho - Produto: " + nomeNoCarrinho + " - Preço: " + precoNoCarrinho);
        System.out.println("🎉 Teste concluído com sucesso! Produto validado em todas as etapas.");
    }
}