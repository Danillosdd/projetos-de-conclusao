//mvn clean test
//mvn test -Dtest=Calculadora

import base.BaseTest;
import pages.CalculadoraPage;
import utils.CSVReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest extends BaseTest {

    // 5.1 - Script simples que realiza soma de dois números
    @Test(description = "Teste simples de soma: 5 + 3 = 8")
    public void testeSomaSimples() {
        System.out.println("🧮 Iniciando teste simples de soma...");

        CalculadoraPage calculadora = new CalculadoraPage(driver);

        // Realiza soma: 5 + 3 = 8
        calculadora.realizarSoma("5", "3");

        String resultado = calculadora.obterResultado();
        Assert.assertEquals(resultado, "8", "O resultado da soma deve ser 8");

        System.out.println("✅ Teste simples executado com sucesso: 5 + 3 = " + resultado);
    }

    // 5.2 - DataProvider para ler dados do CSV
    @DataProvider(name = "dadosCalculadora")
    public Object[][] fornecerDadosCSV() {
        System.out.println("📄 Carregando dados do arquivo CSV...");

        String caminhoCSV = "src/test/resources/calculos.csv";
        List<String[]> dados = CSVReader.lerCSV(caminhoCSV);

        System.out.println("📊 Carregados " + dados.size() + " casos de teste do CSV");

        Object[][] dadosArray = new Object[dados.size()][4];
        for (int i = 0; i < dados.size(); i++) {
            String[] linha = dados.get(i);
            dadosArray[i] = new Object[]{linha[0], linha[1], linha[2], linha[3]};
        }

        return dadosArray;
    }

    // 5.2 - Testes organizados em Page Objects com leitura de CSV
    @Test(dataProvider = "dadosCalculadora",
            description = "Testes de soma organizados com Page Objects e dados do CSV")
    public void testeSomaComPageObjectsECSV(String num1, String operacao, String num2, String resultadoEsperado) {
        System.out.println(String.format("🔢 Executando teste: %s %s %s = ?", num1, operacao, num2));

        CalculadoraPage calculadora = new CalculadoraPage(driver);

        // Realiza a operação usando Page Objects
        calculadora.realizarSoma(num1, num2);

        String resultado = calculadora.obterResultado();
        Assert.assertEquals(resultado, resultadoEsperado,
                String.format("O resultado de %s %s %s deve ser %s", num1, operacao, num2, resultadoEsperado));

        System.out.println(String.format("✅ Teste executado com sucesso: %s %s %s = %s",
                num1, operacao, num2, resultado));
    }
}
