// bibliotecas

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.iterasys.Main;

//classe
public class TesteAreas {
    //atributos

    //funções e metodos
    
    // DDT: Data Driver Testing --> Teste Direcionado à Dados
    // Popular: Teste com massa
    @ParameterizedTest
    @CsvSource(value = {
        "5, 7, 35.0",
        "3, 2, 6.0",
        "10, 10, 100.0",
        "-3, -3, 9.0",
        "7, 7, 49.0"
    }, delimiter = ',')
    public void testeRetanguloDDT(float base, float altura, float resultadoEsperado) {
        // AAA - Arrange, Act, Assert
        // CEV - Configura, Executa, Valida

        //Configura
        // Os Dados de entrada e o resultado esperado são lidos da massa de teste acima  
        // Executa
        float resultadoAtual = Main.retangulo(base, altura);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/triangulo.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeTrianguloCSV(float base, float altura, float resultadoEsperado) {
        // AAA - Arrange, Act, Assert
        // CEV - Configura, Executa, Valida

        //Configura
        // Os Dados de entrada e o resultado esperado são lidos da massa de teste acima  
        // Executa
        float resultadoAtual = Main.triangulo(base, altura);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    }

    // 2.2 - Teste de unidade simples para o volume de um cubo
    @Test
    public void testeVolumeCubo() {
        // AAA - Arrange, Act, Assert
        // CEV - Configura, Executa, Valida

        //Configura
        // Dados de Entrada
        float lado = 4;

        // Dados de Saída / Resultado Esperado
        float resultadoEsperado = 64; // Volume de um cubo: lado^3 = 4^3 = 64

        // Executa
        float resultadoAtual = Main.volumeCubo(lado);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    }

    // 2.2 - Teste lendo uma lista de valores para o volume de um paralelepípedo
    @ParameterizedTest
    @CsvSource(value = {
        "2, 3, 4, 24.0",
        "5, 2, 3, 30.0",
        "1, 1, 1, 1.0",
        "6, 4, 2, 48.0",
        "3.5, 2, 4, 28.0"
    }, delimiter = ',')
    public void testeVolumePararelelepipedoDDT(float comprimento, float largura, float altura, float resultadoEsperado) {
        // AAA - Arrange, Act, Assert
        // CEV - Configura, Executa, Valida

        //Configura
        // Os Dados de entrada e o resultado esperado são lidos da massa de teste acima  
        // Executa
        float resultadoAtual = Main.volumePararelelepipedo(comprimento, largura, altura);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    }

    // 2.3 - Teste com arquivo CSV para volume de uma esfera
    @ParameterizedTest
    @CsvFileSource(resources = "csv/esfera.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeVolumeEsferaCSV(float raio, float resultadoEsperado) {
        // AAA - Arrange, Act, Assert
        // CEV - Configura, Executa, Valida

        //Configura
        // Os Dados de entrada e o resultado esperado são lidos da massa de teste do arquivo CSV
        // Executa
        float resultadoAtual = Main.volumeEsfera(raio);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    }
 
}
