//mvn clean test
//mvn test -Dtest=CalculadoraTest

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CalculadoraPage;
import utils.CSVReader;

public class CalculadoraTest extends BaseTest {

    // 5.1 - Script simples que realiza soma de dois números
    @Test(description = "5.1 - Teste simples de soma: 5 + 3 = 8", priority = 1)
    public void testeSomaSimples() {
        System.out.println("🧮 [5.1] Iniciando teste simples de soma...");
        
        CalculadoraPage calculadora = new CalculadoraPage(driver);
        
        // Realiza soma simples: 5 + 3 = 8
        calculadora.realizarSoma("5", "3");
        
        String resultado = calculadora.obterResultado();
        Assert.assertEquals(resultado, "8", "O resultado da soma deve ser 8");
        
        System.out.println("✅ [5.1] Teste simples executado com sucesso: 5 + 3 = " + resultado);
    }

    // 5.2 - DataProvider para ler dados do CSV
    @DataProvider(name = "dadosCalculadora")
    public Object[][] fornecerDadosCSV() {
        System.out.println("📄 [5.2] Carregando dados do arquivo CSV...");
        
        String caminhoCSV = "src/test/resources/calculos.csv";
        List<String[]> dados = CSVReader.lerCSV(caminhoCSV);
        
        System.out.println("📊 [5.2] Carregados " + dados.size() + " casos de teste do CSV");
        
        Object[][] dadosArray = new Object[dados.size()][4];
        for (int i = 0; i < dados.size(); i++) {
            String[] linha = dados.get(i);
            dadosArray[i] = new Object[]{linha[0], linha[1], linha[2], linha[3]};
        }
        
        return dadosArray;
    }

    // 5.2 - Testes organizados em Page Objects com leitura de CSV (pelo menos 3 cálculos)
    @Test(dataProvider = "dadosCalculadora", 
          description = "5.2 - Testes organizados em Page Objects com dados do CSV",
          priority = 2)
    public void testeSomaComPageObjectsECSV(String num1, String operacao, String num2, String resultadoEsperado) {
        System.out.println(String.format("🔢 [5.2] Executando teste CSV: %s %s %s = ?", num1, operacao, num2));
        
        CalculadoraPage calculadora = new CalculadoraPage(driver);
        
        // Realiza a operação usando Page Objects
        calculadora.realizarSoma(num1, num2);
        
        String resultado = calculadora.obterResultado();
        Assert.assertEquals(resultado, resultadoEsperado, 
            String.format("O resultado de %s %s %s deve ser %s", num1, operacao, num2, resultadoEsperado));
        
        System.out.println(String.format("✅ [5.2] Teste CSV executado: %s %s %s = %s", 
            num1, operacao, num2, resultado));
    }
}
