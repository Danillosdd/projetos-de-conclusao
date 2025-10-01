package pages; // Define o pacote do arquivo

import org.openqa.selenium.WebDriver; // Importa o WebDriver para controlar o navegador

public class CommonPage extends Base { // Classe comum para páginas, herda de Base

    protected WebDriver driver; // Instância protegida do WebDriver

    public CommonPage() { // Construtor padrão
        this.driver = Base.getDriver(); // Inicializa o driver usando o método da classe Base
    }

    public String getUrlAtual() { // Retorna a URL atual da página
        return driver.getCurrentUrl();
    }

    public String getTituloPagina() { // Retorna o título da página atual
        return driver.getTitle();
    }
}
