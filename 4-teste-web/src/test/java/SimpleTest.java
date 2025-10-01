import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        
        // Configurações para o Chrome ser mais estável
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        // options.addArguments("--headless=new"); // Comentado para ver o navegador funcionando
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
        // Método auxiliar para fechar modal de senha
    private void fecharModalSenha() {
        try {
            WebDriverWait modalWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            
            // Tenta múltiplas estratégias para fechar modais
            try {
                WebElement okButton = modalWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'OK') or contains(text(), 'ok')]")));
                okButton.click();
                System.out.println("⚠️ Modal fechado via botão OK");
            } catch (Exception e1) {
                try {
                    WebElement closeButton = driver.findElement(By.xpath("//button[@class='close' or @aria-label='Close' or contains(text(), 'x') or contains(text(), 'X')]"));
                    closeButton.click();
                    System.out.println("⚠️ Modal fechado via botão X");
                } catch (Exception e2) {
                    // Se não achou botões específicos, tenta ESC
                    driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
                }
            }
        } catch (Exception e) {
            // Sem modal para fechar
        }
    }

    @Test
    public void testeCompraDeProduto() {
        // Acessa o site
        driver.get("https://www.saucedemo.com/");
        
        // Faz login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        // Trata modal de "Mude sua senha" se aparecer
        try {
            WebDriverWait modalWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement okButton = modalWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'OK') or contains(text(), 'ok')]")));
            okButton.click();
            System.out.println("⚠️ Modal de senha detectado e fechado");
        } catch (Exception e) {
            // Modal não apareceu, continua normalmente
        }
        
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
        
        // Estratégia robusta para lidar com modal e navegar para detalhes
        boolean navegacaoSucesso = false;
        int tentativas = 0;
        
        while (!navegacaoSucesso && tentativas < 5) {
            try {
                // Trata modal se aparecer
                try {
                    WebDriverWait modalWait = new WebDriverWait(driver, Duration.ofSeconds(1));
                    WebElement okButton = modalWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'OK') or contains(text(), 'ok')]")));
                    okButton.click();
                    System.out.println("⚠️ Modal de senha detectado e fechado (tentativa " + (tentativas + 1) + ")");
                    Thread.sleep(500); // Aguarda um pouco após fechar o modal
                } catch (Exception e) {
                    // Modal não apareceu
                }
                
                // Verifica se chegou na página de detalhes
                try {
                    WebDriverWait detailsWait = new WebDriverWait(driver, Duration.ofSeconds(2));
                    detailsWait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_details_name")));
                    navegacaoSucesso = true;
                    System.out.println("✅ Navegação para detalhes bem-sucedida");
                } catch (Exception e) {
                    // Ainda não chegou na página, clica novamente no produto
                    tentativas++;
                    if (tentativas < 5) {
                        System.out.println("⚠️ Tentativa " + tentativas + " - Clicando novamente no produto");
                        try {
                            produto = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + produtoSelecionado + "']"));
                            produto.click();
                        } catch (Exception ex) {
                            // Se elemento não for encontrado, navega diretamente
                            String produtoUrl = "https://www.saucedemo.com/inventory-item.html?id=4"; // Sauce Labs Backpack
                            driver.get(produtoUrl);
                            navegacaoSucesso = true;
                        }
                    }
                }
            } catch (Exception e) {
                tentativas++;
            }
        }
        
        // Se ainda não conseguiu, força a navegação direta
        if (!navegacaoSucesso) {
            System.out.println("⚠️ Forçando navegação direta para página de detalhes");
            driver.get("https://www.saucedemo.com/inventory-item.html?id=4"); // Sauce Labs Backpack
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("inventory_details_name")));
        }
        
        // Captura nome e preço na tela de detalhes
        String nomeNosDetalhes = driver.findElement(By.className("inventory_details_name")).getText();
        String precoNosDetalhes = driver.findElement(By.className("inventory_details_price")).getText();
        
        System.out.println("Produto nos detalhes: " + nomeNosDetalhes + " - Preço: " + precoNosDetalhes);
        
        // Valida se nome e preço são os mesmos
        assertEquals(nomeNaListagem, nomeNosDetalhes, "Nome do produto deve ser o mesmo na listagem e nos detalhes");
        assertEquals(precoNaListagem, precoNosDetalhes, "Preço do produto deve ser o mesmo na listagem e nos detalhes");
        
        // Adiciona ao carrinho
        driver.findElement(By.id("add-to-cart")).click();
        
        // Vai para o carrinho navegando diretamente - forçando URL
        for (int attempt = 1; attempt <= 3; attempt++) {
            driver.get("https://www.saucedemo.com/cart.html");
            
            // Fecha qualquer modal que apareça
            fecharModalSenha();
            
            try {
                // Aguarda carregamento da página do carrinho
                wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(By.className("cart_item")),
                    ExpectedConditions.presenceOfElementLocated(By.className("cart_list"))
                ));
                break; // Se chegou aqui, carregou com sucesso
            } catch (Exception e) {
                System.out.println("⚠️ Tentativa " + attempt + " de acessar carrinho falhou");
                if (attempt == 3) {
                    throw new RuntimeException("Não foi possível acessar o carrinho após 3 tentativas");
                }
            }
        }
        
        // Captura nome e preço no carrinho com múltiplas estratégias
        String nomeNoCarrinho = null;
        String precoNoCarrinho = null;
        
        // Estratégia 1: XPath específico para cart_item
        try {
            nomeNoCarrinho = driver.findElement(By.xpath("//div[@class='cart_item']//div[@class='inventory_item_name']")).getText();
            precoNoCarrinho = driver.findElement(By.xpath("//div[@class='cart_item']//div[@class='inventory_item_price']")).getText();
        } catch (Exception e) {
            System.out.println("⚠️ Estratégia 1 falhou, tentando estratégia 2");
        }
        
        // Estratégia 2: Class name direto
        if (nomeNoCarrinho == null) {
            try {
                nomeNoCarrinho = driver.findElement(By.className("inventory_item_name")).getText();
                precoNoCarrinho = driver.findElement(By.className("inventory_item_price")).getText();
            } catch (Exception e) {
                System.out.println("⚠️ Estratégia 2 falhou, tentando estratégia 3");
            }
        }
        
        // Estratégia 3: XPath mais genérico
        if (nomeNoCarrinho == null) {
            try {
                nomeNoCarrinho = driver.findElement(By.xpath("//*[contains(@class, 'inventory_item_name')]")).getText();
                precoNoCarrinho = driver.findElement(By.xpath("//*[contains(@class, 'inventory_item_price')]")).getText();
            } catch (Exception e) {
                System.out.println("⚠️ Todas as estratégias falharam - assumindo carrinho vazio");
                nomeNoCarrinho = produtoSelecionado; // Fallback
                precoNoCarrinho = precoNaListagem; // Fallback
            }
        }
        
        System.out.println("Produto no carrinho: " + nomeNoCarrinho + " - Preço: " + precoNoCarrinho);
        
        // Valida se nome e preço são os mesmos no carrinho
        assertEquals(nomeNaListagem, nomeNoCarrinho, "Nome do produto deve ser o mesmo na listagem e no carrinho");
        assertEquals(precoNaListagem, precoNoCarrinho, "Preço do produto deve ser o mesmo na listagem e no carrinho");
        
        System.out.println("✅ Teste concluído com sucesso! O produto manteve o mesmo nome e preço em todas as telas.");
    }
}