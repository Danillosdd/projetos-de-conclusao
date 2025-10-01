package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    
    // Elementos da página
    private final By campoUsuario = By.id("user-name");
    private final By campoSenha = By.id("password");
    private final By botaoLogin = By.id("login-button");
    private final By mensagemErro = By.xpath("//h3[@data-test='error']");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void preencherUsuario(String usuario) {
        driver.findElement(campoUsuario).sendKeys(usuario);
    }
    
    public void preencherSenha(String senha) {
        driver.findElement(campoSenha).sendKeys(senha);
    }
    
    public void clicarBotaoLogin() {
        driver.findElement(botaoLogin).click();
    }
    
    public void fazerLogin(String usuario, String senha) {
        preencherUsuario(usuario);
        preencherSenha(senha);
        clicarBotaoLogin();
    }
    
    public String obterMensagemErro() {
        try {
            return driver.findElement(mensagemErro).getText();
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isLoginBemSucedido() {
        return driver.getCurrentUrl().contains("inventory.html");
    }
}