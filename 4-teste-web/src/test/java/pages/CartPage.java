package pages; // Define o pacote do arquivo

import org.openqa.selenium.By; // Importa classe para localizar elementos
import org.openqa.selenium.WebDriver; // Importa o WebDriver para controlar o navegador

public class CartPage { // Classe que representa a página do carrinho
    WebDriver driver; // Instância do WebDriver para manipular a página

    public CartPage(WebDriver driver) { // Construtor recebe o driver
        this.driver = driver;
    }

    // Verifica se um produto está presente no carrinho
    public boolean isProductInCart(String productName) {
        // Monta o XPath para localizar o produto pelo nome dentro do carrinho
        String xpath = String.format("//div[@class='cart_item']//div[text()='%s']", productName);
        // Retorna true se encontrou o produto, false caso contrário
        return driver.findElements(By.xpath(xpath)).size() > 0;
    }

    // Realiza o checkout (prossegue para a próxima etapa da compra)
    public void checkout() {
        driver.findElement(By.id("checkout")).click(); // Clica no botão de checkout
    }
}