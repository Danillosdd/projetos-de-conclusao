// 0 - nome do pacote

// 1 - bibliotecas
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths; // Anotação para marcar métodos de teste (JUnit 5)

import static org.hamcrest.Matchers.containsString; // Define a ordem de execução dos testes
import static org.hamcrest.Matchers.is; // Controla a ordem dos métodos de teste na classe
import org.junit.jupiter.api.MethodOrderer; // Estratégias para ordenar métodos de teste
import org.junit.jupiter.api.Order; // Permite testes parametrizados (com diferentes entradas)
import org.junit.jupiter.api.Test; // Biblioteca para converter objetos Java em JSON e vice-versa
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.google.gson.Gson;

import static io.restassured.RestAssured.given;

// 2 - classe
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Ativa a ordenação dos testes
public class TestStore {

    // 2.1 - atributos
    static String ct = "application/json"; // Content-Type
    static String uriStore = "https://petstore.swagger.io/v2/store/order"; // Base URL + endpoint

    // 2.2 - métodos de teste
    // Função de leitura do arquivo JSON
    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @Test
    @Order(1)
    public void testPostStore() throws IOException {
        // Configura
        String jsonBody = lerArquivoJson("src/test/resources/json/store1.json");
        given()
            .contentType(ct)
            .log().all()
            .body(jsonBody)
        .when()
            .post(uriStore)
        .then()
            .log().all()
            .statusCode(200)
            .body("id", is(1))
            .body("petId", is(95))
            .body("quantity", is(1))
            .body("shipDate", containsString("2025-08-28T00:00:00.000"))
            .body("status", is("placed"))
            .body("complete", is(true));
    }

    @Test
    @Order(2)
    public void testGetStore() {
        // Configura
        given()
            .contentType(ct)
            .log().all()
        .when()
            .get(uriStore + "/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", is(1))
            .body("petId", is(95))
            .body("quantity", is(1))
            .body("shipDate", containsString("2025-08-28T00:00:00.000"))
            .body("status", is("placed"))
            .body("complete", is(true));
    }

    @Test
    @Order(3)
    public void testDeleteStore() {
        // Configura
        given()
            .contentType(ct)
            .log().all()
        .when()
            .delete(uriStore + "/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("code", is(200))
            .body("type", is("unknown"))
            .body("message", containsString("1"));
    }

    // Data Driven Testing(DDT) - Teste Direcionado por Dados / Teste com Massa
    @ParameterizedTest
    @Order(4)
    @CsvFileSource(resources = "/csv/storeMassa.csv", numLinesToSkip = 1)
    public void testPostStoreDataDriven(int id, int petId, int quantity, String shipDate, String status, boolean complete) {
        // Criar o objeto Store com os dados do CSV
        Store store = new Store();
        store.id = id;
        store.petId = petId;
        store.quantity = quantity;
        store.shipDate = shipDate;
        store.status = status;
        store.complete = complete;

        // Converter para JSON
        Gson gson = new Gson();
        String jsonBody = gson.toJson(store);

        given()
            .contentType(ct)
            .log().all()
            .body(jsonBody)
        .when()
            .post(uriStore)
        .then()
            .log().all()
            .statusCode(200)
            .body("id", is(id))
            .body("petId", is(petId))
            .body("quantity", is(quantity))
            .body("shipDate", containsString(shipDate.substring(0,23)))
            .body("status", is(status))
            .body("complete", is(complete));
    }
}
