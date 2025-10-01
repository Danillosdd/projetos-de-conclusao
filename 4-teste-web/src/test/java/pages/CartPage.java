package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    
    // Elementos da página
    private final By itensCarrinho = By.className("cart_item");
    private final By nomeItem = By.className("inventory_item_name");
    private final By precoItem = By.className("inventory_item_price");
    private final By quantidadeItem = By.className("cart_quantity");
    private final By botaoContinuarComprando = By.id("continue-shopping");
    private final By botaoCheckout = By.id("checkout");
    private final By botaoRemoverItem = By.xpath("//button[contains(@id,'remove')]");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public void aguardarCarregamentoPagina() {
        wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(itensCarrinho),
            ExpectedConditions.presenceOfElementLocated(By.className("cart_list"))
        ));
    }
    
    public String obterNomeProduto() {
        try {
            return driver.findElement(By.xpath("//div[@class='cart_item']//div[@class='inventory_item_name']")).getText();
        } catch (Exception e) {
            return driver.findElement(nomeItem).getText();
        }
    }
    
    public String obterPrecoProduto() {
        try {
            return driver.findElement(By.xpath("//div[@class='cart_item']//div[@class='inventory_item_price']")).getText();
        } catch (Exception e) {
            return driver.findElement(precoItem).getText();
        }
    }
    
    public String obterQuantidadeProduto() {
        return driver.findElement(quantidadeItem).getText();
    }
    
    public List<WebElement> obterItensCarrinho() {
        return driver.findElements(itensCarrinho);
    }
    
    public int obterQuantidadeItensCarrinho() {
        return obterItensCarrinho().size();
    }
    
    public void continuarComprando() {
        driver.findElement(botaoContinuarComprando).click();
    }
    
    public void procederCheckout() {
        driver.findElement(botaoCheckout).click();
    }
    
    public void removerPrimeiroProduto() {
        driver.findElement(botaoRemoverItem).click();
    }
    
    public boolean isCarrinhoVazio() {
        try {
            driver.findElement(itensCarrinho);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}