package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {
    
    // Elementos da página
    private final By itensInventario = By.className("inventory_item");
    private final By linkCarrinho = By.className("shopping_cart_link");
    private final By contadorCarrinho = By.className("shopping_cart_badge");
    
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    
    public void aguardarCarregamentoPagina() {
        wait.until(ExpectedConditions.presenceOfElementLocated(itensInventario));
    }
    
    public String obterNomeProduto(String nomeProduto) {
        WebElement produto = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + nomeProduto + "']"));
        return produto.getText();
    }
    
    public String obterPrecoProduto(String nomeProduto) {
        WebElement precoElemento = driver.findElement(By.xpath(
            "//div[@class='inventory_item_name ' and text()='" + nomeProduto + 
            "']/ancestor::div[@class='inventory_item_label']/following-sibling::div//div[@class='inventory_item_price']"
        ));
        return precoElemento.getText();
    }
    
    public void clicarNoProduto(String nomeProduto) {
        WebElement produto = driver.findElement(By.xpath("//div[@class='inventory_item_name ' and text()='" + nomeProduto + "']"));
        produto.click();
    }
    
    public void adicionarProdutoAoCarrinho(String nomeProduto) {
        WebElement botaoAdicionar = driver.findElement(By.xpath(
            "//div[@class='inventory_item_name ' and text()='" + nomeProduto + 
            "']/ancestor::div[@class='inventory_item']//button[contains(@id,'add-to-cart')]"
        ));
        botaoAdicionar.click();
    }
    
    public void irParaCarrinho() {
        driver.findElement(linkCarrinho).click();
    }
    
    public int obterQuantidadeItensCarrinho() {
        try {
            String quantidade = driver.findElement(contadorCarrinho).getText();
            return Integer.parseInt(quantidade);
        } catch (Exception e) {
            return 0;
        }
    }
    
    public List<WebElement> listarProdutos() {
        return driver.findElements(itensInventario);
    }
}