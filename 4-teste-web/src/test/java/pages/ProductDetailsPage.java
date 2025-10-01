package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage {
    
    // Elementos da página
    private final By nomeDetalhes = By.className("inventory_details_name");
    private final By precoDetalhes = By.className("inventory_details_price");
    private final By descricaoDetalhes = By.className("inventory_details_desc");
    private final By botaoAdicionarCarrinho = By.id("add-to-cart");
    private final By botaoRemoverCarrinho = By.id("remove");
    private final By botaoVoltar = By.id("back-to-products");
    private final By linkCarrinho = By.className("shopping_cart_link");
    
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }
    
    public void aguardarCarregamentoPagina() {
        wait.until(ExpectedConditions.presenceOfElementLocated(nomeDetalhes));
    }
    
    public String obterNomeProduto() {
        return driver.findElement(nomeDetalhes).getText();
    }
    
    public String obterPrecoProduto() {
        return driver.findElement(precoDetalhes).getText();
    }
    
    public String obterDescricaoProduto() {
        return driver.findElement(descricaoDetalhes).getText();
    }
    
    public void adicionarAoCarrinho() {
        driver.findElement(botaoAdicionarCarrinho).click();
    }
    
    public void removerDoCarrinho() {
        driver.findElement(botaoRemoverCarrinho).click();
    }
    
    public void voltarParaProdutos() {
        driver.findElement(botaoVoltar).click();
    }
    
    public void irParaCarrinho() {
        driver.findElement(linkCarrinho).click();
    }
    
    public boolean isProdutoAdicionadoCarrinho() {
        try {
            driver.findElement(botaoRemoverCarrinho);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}