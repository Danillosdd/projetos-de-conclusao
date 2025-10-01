package pages; // Define o pacote do arquivo

import org.openqa.selenium.By; // Importa classe para localizar elementos
import org.openqa.selenium.WebDriver; // Importa o WebDriver para controlar o navegador

public class InventoryPage { // Classe que representa a página de produtos

    WebDriver driver; // Instância do WebDriver para manipular a página

    public InventoryPage(WebDriver driver) { // Construtor recebe o driver
        this.driver = driver;
    }

    // Adiciona um produto ao carrinho pelo nome
    public void addProductToCart(String productName) {
        // Monta o XPath para localizar o botão "Add to cart" do produto
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        driver.findElement(By.xpath(xpath)).click(); // Clica no botão
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } // Aguarda meio segundo
    }

    // Verifica se o produto foi adicionado ao carrinho (botão mudou para "Remove")
    public boolean isProductAdded(String productName) {
        // Monta o XPath para localizar o botão "Remove" do produto
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Remove']", productName);
        return driver.findElements(By.xpath(xpath)).size() > 0; // Retorna true se encontrou o botão "Remove"
    }

    // Acessa o carrinho de compras
    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click(); // Clica no ícone do carrinho
    }
}
