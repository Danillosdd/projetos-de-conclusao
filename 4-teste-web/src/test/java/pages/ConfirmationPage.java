package pages; // Define o pacote do arquivo

import org.openqa.selenium.By; // Importa classe para localizar elementos
import org.openqa.selenium.WebDriver; // Importa o WebDriver para controlar o navegador

public class ConfirmationPage { // Classe que representa a página de confirmação
    WebDriver driver; // Instância do WebDriver para manipular a página

    public ConfirmationPage(WebDriver driver) { // Construtor recebe o driver
        this.driver = driver;
    }

    // Retorna a mensagem de confirmação exibida após finalizar a compra
    public String getConfirmationMessage() {
        return driver.findElement(By.className("complete-header")).getText(); // Busca o texto da
    }
}