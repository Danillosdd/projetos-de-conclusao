import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import com.iterasys.Main;

public class TesteAreas {

    @Test
    public void testeVolumeCubo() {

        float lado = 4;

        float resultadoEsperado = 64;

        float resultadoAtual = Main.volumeCubo(lado);

        assertEquals(resultadoEsperado, resultadoAtual);

    }

    @ParameterizedTest
    @CsvSource(value = {
            "2, 3, 4, 24.0",
            "5, 2, 3, 30.0",
            "1, 1, 1, 1.0",
            "6, 4, 2, 48.0",
            "3.5, 2, 4, 28.0"
    }, delimiter = ',')
    public void testeVolumePararelelepipedoDDT(float comprimento, float largura, float altura,
            float resultadoEsperado) {

        float resultadoAtual = Main.volumePararelelepipedo(comprimento, largura, altura);

        assertEquals(resultadoEsperado, resultadoAtual);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "csv/esfera.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeVolumeEsferaCSV(float raio, float resultadoEsperado) {
        float resultadoAtual = Main.volumeEsfera(raio);

        assertEquals(resultadoEsperado, resultadoAtual);

    }

}
