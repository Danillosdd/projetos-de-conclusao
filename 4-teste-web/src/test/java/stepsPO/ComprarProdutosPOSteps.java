package stepsPO;

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
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductDetailsPage;

public class ComprarProdutosPOSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;
    
    private String nomeProdutoCapturado;
    private String precoProdutoCapturado;
    
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        // Configurações para o Chrome ser mais estável
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        // options.addArguments("--headless=new"); // Comentado para ver o navegador funcionando
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
        
        // Inicializa as páginas
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        cartPage = new CartPage(driver);
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Dado("que acesso o site SauceDemo")
    public void que_acesso_o_site_sauce_demo() {
        loginPage.navegarPara("https://www.saucedemo.com/");
    }
    
    @E("faço login com usuário {string} e senha {string}")
    public void faco_login_com_usuario_e_senha(String usuario, String senha) {
        loginPage.fazerLogin(usuario, senha);
        
        // Trata modal de "Mude sua senha" se aparecer
        try {
            WebDriverWait modalWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement okButton = modalWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'OK') or contains(text(), 'ok')]")));
            okButton.click();
            System.out.println("⚠️ Modal de senha detectado e fechado");
        } catch (Exception e) {
            // Modal não apareceu, continua normalmente
        }
        
        inventoryPage.aguardarCarregamentoPagina();
    }
    
    @Quando("seleciono o produto {string}")
    public void seleciono_o_produto(String nomeProduto) {
        inventoryPage.clicarNoProduto(nomeProduto);
    }
    
    @Então("valido que o nome {string} e preço {string} estão corretos na listagem")
    public void valido_que_o_nome_e_preco_estao_corretos_na_listagem(String nomeEsperado, String precoEsperado) {
        // Volta para a página de listagem se necessário
        if (!inventoryPage.obterUrlAtual().contains("inventory.html")) {
            driver.navigate().back();
            inventoryPage.aguardarCarregamentoPagina();
        }
        
        nomeProdutoCapturado = inventoryPage.obterNomeProduto(nomeEsperado);
        precoProdutoCapturado = inventoryPage.obterPrecoProduto(nomeEsperado);
        
        assertEquals(nomeEsperado, nomeProdutoCapturado, "Nome do produto na listagem deve estar correto");
        assertEquals(precoEsperado, precoProdutoCapturado, "Preço do produto na listagem deve estar correto");
        
        System.out.println("✅ Listagem - Produto: " + nomeProdutoCapturado + " - Preço: " + precoProdutoCapturado);
    }
    
    @E("vou para a página de detalhes do produto")
    public void vou_para_a_pagina_de_detalhes_do_produto() {
        inventoryPage.clicarNoProduto(nomeProdutoCapturado);
        productDetailsPage.aguardarCarregamentoPagina();
    }
    
    @E("valido que o nome {string} e preço {string} estão corretos nos detalhes")
    public void valido_que_o_nome_e_preco_estao_corretos_nos_detalhes(String nomeEsperado, String precoEsperado) {
        String nomeNosDetalhes = productDetailsPage.obterNomeProduto();
        String precoNosDetalhes = productDetailsPage.obterPrecoProduto();
        
        assertEquals(nomeEsperado, nomeNosDetalhes, "Nome do produto nos detalhes deve estar correto");
        assertEquals(precoEsperado, precoNosDetalhes, "Preço do produto nos detalhes deve estar correto");
        assertEquals(nomeProdutoCapturado, nomeNosDetalhes, "Nome deve ser consistente entre listagem e detalhes");
        assertEquals(precoProdutoCapturado, precoNosDetalhes, "Preço deve ser consistente entre listagem e detalhes");
        
        System.out.println("✅ Detalhes - Produto: " + nomeNosDetalhes + " - Preço: " + precoNosDetalhes);
    }
    
    @E("adiciono o produto ao carrinho")
    public void adiciono_o_produto_ao_carrinho() {
        productDetailsPage.adicionarAoCarrinho();
    }
    
    @E("vou para o carrinho")
    public void vou_para_o_carrinho() {
        // Navega diretamente para o carrinho
        driver.get("https://www.saucedemo.com/cart.html");
        cartPage.aguardarCarregamentoPagina();
    }
    
    @Então("valido que o nome {string} e preço {string} estão corretos no carrinho")
    public void valido_que_o_nome_e_preco_estao_corretos_no_carrinho(String nomeEsperado, String precoEsperado) {
        String nomeNoCarrinho = cartPage.obterNomeProduto();
        String precoNoCarrinho = cartPage.obterPrecoProduto();
        
        assertEquals(nomeEsperado, nomeNoCarrinho, "Nome do produto no carrinho deve estar correto");
        assertEquals(precoEsperado, precoNoCarrinho, "Preço do produto no carrinho deve estar correto");
        assertEquals(nomeProdutoCapturado, nomeNoCarrinho, "Nome deve ser consistente entre listagem e carrinho");
        assertEquals(precoProdutoCapturado, precoNoCarrinho, "Preço deve ser consistente entre listagem e carrinho");
        
        System.out.println("✅ Carrinho - Produto: " + nomeNoCarrinho + " - Preço: " + precoNoCarrinho);
        System.out.println("🎉 Teste concluído com sucesso! Produto validado em todas as etapas.");
    }
}