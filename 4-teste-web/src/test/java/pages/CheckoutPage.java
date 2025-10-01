package pages; // Define o pacote do arquivo

import org.openqa.selenium.By; // Importa classe para localizar elementos
import org.openqa.selenium.WebDriver; // Importa o WebDriver para controlar o navegador

public class CheckoutPage { // Classe que representa a página de checkout
    WebDriver driver; // Instância do WebDriver para manipular a página

    public CheckoutPage(WebDriver driver) { // Construtor recebe o driver
        this.driver = driver;
    }

    // Preenche os dados do comprador e clica em "Continuar"
    public void fillInformation(String nome, String sobrenome, String cep) {
        driver.findElement(By.id("first-name")).sendKeys(nome); // Preenche o campo nome
        driver.findElement(By.id("last-name")).sendKeys(sobrenome); // Preenche o campo sobrenome
        driver.findElement(By.id("postal-code")).sendKeys(cep); // Preenche o campo CEP
        driver.findElement(By.id("continue")).click(); // Clica no botão continuar
    }

    // Verifica se está na página de resumo do checkout
    public boolean isOnOverviewPage() {
        return driver.getCurrentUrl().contains("checkout-step-two.html"); // Retorna true se está na página de overview
    }

    // Finaliza a compra clicando no botão "Finish"
    public void finish() {
        driver.findElement(By.id("finish")).click(); // Clica no botão finalizar
    }
}