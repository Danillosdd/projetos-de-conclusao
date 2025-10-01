package pages; // Define o pacote do arquivo

import org.openqa.selenium.By; // Importa classe para localizar elementos
import org.openqa.selenium.WebDriver; // Importa o WebDriver para controlar o navegador

public class HomePage { // Classe que representa a página de login
    WebDriver driver; // Instância do WebDriver para manipular a página

    public HomePage(WebDriver driver) { // Construtor recebe o driver
        this.driver = driver;
    }

    // Realiza o login com usuário e senha informados
    public void login(String usuario, String senha) {
        driver.findElement(By.id("user-name")).sendKeys(usuario); // Preenche o campo usuário
        driver.findElement(By.id("password")).sendKeys(senha); // Preenche o campo senha
        driver.findElement(By.id("login-button")).click(); // Clica no botão de login
    }

    // Verifica se o login foi bem-sucedido (está na página de produtos)
    public boolean isLoggedIn() {
        return driver.getCurrentUrl().contains("inventory.html"); // Retorna true se está na página de produtos
    }
}