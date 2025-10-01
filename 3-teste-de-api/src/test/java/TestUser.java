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
public class TestUser {

    // 2.1 - atributos
    static String ct = "application/json"; // Content-Type
    static String uriUser = "https://petstore.swagger.io/v2/user"; // Base URL + endpoint

    // 2.2 - métodos de teste
    // Função de leitura do arquivo JSON
    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @Test
    @Order(1)
    public void testPostUser() throws IOException {
        // Configura
        String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");
        given()
            .contentType(ct)
            .log().all()
            .body(jsonBody)
        .when()
            .post(uriUser)
        .then()
            .log().all()
            .statusCode(200)
            .body("code", is(200))
            .body("type", is("unknown"))
            .body("message", containsString("10"));
    }

    @Test
    @Order(2)
    public void testGetUser() {
        // Configura
        given()
            .contentType(ct)
            .log().all()
        .when()
            .get(uriUser + "/danillo")
        .then()
            .log().all()
            .statusCode(200)
            .body("id", is(10))
            .body("username", is("danillo"))
            .body("firstName", is("Danillo"))
            .body("lastName", is("Silva"))
            .body("email", is("danillo@email.com"))
            .body("password", is("honeypot"))
            .body("phone", is("123456789"))
            .body("userStatus", is(1));
    }

    @Test
    @Order(3)
    public void testPutUser() {
        // Configura
        User user = new User();
        user.id = 10;
        user.username = "danillo";
        user.firstName = "Danillo";
        user.lastName = "Silva";
        user.email = "danillo@email.com";
        user.password = "honeypot";
        user.phone = "987654321"; // valor alterado para PUT
        user.userStatus = 1;

        Gson gson = new Gson();
        String jsonBody = gson.toJson(user);

        given()
            .contentType(ct)
            .log().all()
            .body(jsonBody)
        .when()
            .put(uriUser + "/danillo")
        .then()
            .log().all()
            .statusCode(200)
            .body("code", is(200))
            .body("type", is("unknown"))
            .body("message", containsString("10"));
    }

    @Test
    @Order(4)
    public void testDeleteUser() {
        // Configura
        given()
            .contentType(ct)
            .log().all()
        .when()
            .delete(uriUser + "/danillo")
        .then()
            .log().all()
            .statusCode(200)
            .body("code", is(200))
            .body("type", is("unknown"))
            .body("message", containsString("danillo"));
    }

    // Data Driven Testing(DDT) - Teste Direcionado por Dados / Teste com Massa
    @ParameterizedTest
    @Order(5)
    @CsvFileSource(resources = "/csv/userMassa.csv", numLinesToSkip = 1)
    public void testPostUserDataDriven(int id, String username, String firstName, String lastName, String email, String password, String phone, int userStatus) {
        // Criar o objeto User com os dados do CSV
        User user = new User();
        user.id = id;
        user.username = username;
        user.firstName = firstName;
        user.lastName = lastName;
        user.email = email;
        user.password = password;
        user.phone = phone;
        user.userStatus = userStatus;

        // Converter para JSON
        Gson gson = new Gson();
        String jsonBody = gson.toJson(user);

        given()
            .contentType(ct)
            .log().all()
            .body(jsonBody)
        .when()
            .post(uriUser)
        .then()
            .log().all()
            .statusCode(200)
            .body("code", is(200))
            .body("type", is("unknown"))
            .body("message", containsString(String.valueOf(id)));

        // Validar os campos do usuário criado
        given()
            .contentType(ct)
            .log().all()
        .when()
            .get(uriUser + "/" + username)
        .then()
            .log().all()
            .statusCode(200)
            .body("id", is(id))
            .body("username", is(username))
            .body("firstName", is(firstName))
            .body("lastName", is(lastName))
            .body("email", is(email))
            .body("password", is(password))
            .body("phone", is(phone))
            .body("userStatus", is(userStatus));
    }
}
