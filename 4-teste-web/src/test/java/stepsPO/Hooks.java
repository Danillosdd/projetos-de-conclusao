package stepsPO; // Define o pacote do arquivo

import io.cucumber.java.After; // Importa anotação para executar após cada cenário
import io.cucumber.java.Before; // Importa anotação para executar antes de cada cenário
import pages.Base; // Importa a classe base do WebDriver

public class Hooks { // Classe de hooks do Cucumber

    @Before // Executa antes de cada cenário
    public void setUp() {
        Base.iniciarDriver(); // Inicializa o WebDriver
    }

    @After // Executa após cada cenário
    public void tearDown() {
        Base.fecharDriver(); // Finaliza o WebDriver
    }
}
