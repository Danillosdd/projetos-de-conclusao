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
    @Test
    public void testeQuadrado() {
        // AAA - Arrange, Act, Assert
        // CEV - Configura, Executa, Valida

        //Configura
        // Dados de Entrada
        float lado = 7;

        // Dados de Saída / Resultado Esperado
        float resultadoEsperado = 49;

        // Executa
        float resultadoAtual = Main.quadrado(lado);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    }

    @Test
    public void testeRetangulo() {
        // AAA - Arrange, Act, Assert
        // CEV - Configura, Executa, Valida

        //Configura
        // Dados de Entrada
        float base = 35;
        float altura = 2.5f;

        // Dados de Saída / Resultado Esperado
        float resultadoEsperado = 87.5f;

        // Executa
        float resultadoAtual = Main.retangulo(base, altura);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    }

    @Test
    public void testeCirculo() {
        // AAA - Arrange, Act, Assert
        // CEV - Configura, Executa, Valida

        //Configura
        // Dados de Entrada
        float raio = 3;

        // Dados de Saída / Resultado Esperado
        float resultadoEsperado = 28.274334f; // Área de um círculo: PI * raio^2

        // Executa
        float resultadoAtual = Main.circulo(raio);

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);

    }

    @Test
    public void testeTriangulo() {
        // AAA - Arrange, Act, Assert
        // CEV - Configura, Executa, Valida

        // Configura
        // Dados de Entrada
        float base = 5; // Alterado para float para consistência
        float altura = 7;

        // Dados de Saída / Resultado Esperado
        float resultadoEsperado = 17.5f; // Área de um triângulo: (base * altura) / 2

        // Executa
        float resultadoAtual = Main.triangulo(base, altura); // Supondo que o método retorne float

        // Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    
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
 
}
