package pages; // Define o pacote do arquivo

import org.openqa.selenium.By; // Importa classes do Selenium para automação
import org.openqa.selenium.WebDriver; // Importa classes específicas do Chrome
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base { // Classe base para manipulação do WebDriver

    protected static WebDriver driver; // Instância compartilhada do WebDriver

    public static void iniciarDriver() { // Inicializa o driver do Chrome
        if (driver == null) { // Só inicializa se ainda não existe
            ChromeOptions options = new ChromeOptions(); // Configurações do Chrome

            // Flags para desabilitar pop-ups e recursos do navegador
            options.addArguments("--disable-save-password-bubble"); // Desabilita sugestão de salvar senha
            options.addArguments("--disable-infobars"); // Remove barra de informações
            options.addArguments("--disable-notifications"); // Desabilita notificações
            options.addArguments("--start-maximized"); // Inicia maximizado
            options.addArguments("--disable-blink-features=AutomationControlled"); // Oculta automação
            options.addArguments("--no-default-browser-check"); // Não verifica navegador padrão
            options.addArguments("--disable-features=PasswordManager,AutofillServerCommunication"); // Desabilita gerenciador de senhas e preenchimento automático
            options.addArguments("--disable-extensions"); // Desabilita extensões
            options.addArguments("--disable-popup-blocking"); // Desabilita bloqueio de pop-ups
            options.addArguments("--user-data-dir=/tmp/chrome-test-profile"); // Usa perfil temporário
            options.addArguments("--incognito"); // Modo incógnito
            options.addArguments("--profile-directory=Default"); // Usa perfil padrão

            // Desabilita serviços de senha via preferências
            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
                put("credentials_enable_service", false); // Desabilita serviço de credenciais
                put("profile.password_manager_enabled", false); // Desabilita gerenciador de senhas
            }});

            driver = new ChromeDriver(options); // Cria o driver do Chrome com as opções
        }
    }

    public static void fecharDriver() { // Fecha e limpa o driver
        if (driver != null) {
            driver.quit(); // Encerra o navegador
            driver = null; // Limpa a referência
        }
    }

    public static WebDriver getDriver() { // Retorna o driver, inicializando se necessário
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            // Repete as mesmas opções do iniciarDriver()
            options.addArguments("--disable-save-password-bubble"); // Desabilita sugestão de salvar senha
            options.addArguments("--disable-infobars"); // Remove barra de informações
            options.addArguments("--disable-notifications"); // Desabilita notificações
            options.addArguments("--no-default-browser-check"); // Não verifica navegador padrão
            options.addArguments("--disable-features=PasswordManager,AutofillServerCommunication"); // Desabilita gerenciador de senhas e preenchimento automático
            options.addArguments("--disable-extensions"); // Desabilita extensões
            options.addArguments("--disable-popup-blocking"); // Desabilita bloqueio de pop-ups
            options.addArguments("--user-data-dir=/tmp/chrome-test-profile"); // Usa perfil temporário
            options.addArguments("--incognito"); // Modo incógnito
            options.addArguments("--profile-directory=Default"); // Usa perfil padrão

            // Desabilita serviços de senha via preferências
            options.setExperimentalOption("prefs", new java.util.HashMap<String, Object>() {{
                put("credentials_enable_service", false); // Desabilita serviço de credenciais
                put("profile.password_manager_enabled", false); // Desabilita gerenciador de senhas
            }});
            driver = new ChromeDriver(options);
            driver.manage().window().maximize(); // Maximiza a janela
        }
        return driver;
    }

    public static void aguardarModal() { // Fecha o modal de senha do Chrome se aparecer
        try {
            WebElement btnOk = driver.findElement(By.xpath("//button[text()='OK']")); // Localiza botão OK do modal
            btnOk.click(); // Clica para fechar
            Thread.sleep(500); // Aguarda o modal sumir
        } catch (org.openqa.selenium.NoSuchElementException | InterruptedException e) {
            // Ignora se não encontrar o modal ou se a thread for interrompida
        }
    }
}
