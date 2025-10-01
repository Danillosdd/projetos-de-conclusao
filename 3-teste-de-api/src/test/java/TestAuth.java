import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@DisplayName("Testes da API Restful Booker - Autenticação")
public class TestAuth {

    private static final String ct = ContentType.JSON.toString();
    private static final String uri = "https://restful-booker.herokuapp.com";
    private static final Gson gson = new Gson();

    @BeforeAll
    public static void setup() {
        // Configuração global do RestAssured
        RestAssured.baseURI = uri;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Deve autenticar usuário com credenciais válidas")
    public void testAuthValidCredentials() throws IOException {
        // Ler dados do arquivo JSON
        String jsonBody = readJsonFile("src/test/resources/json/auth1.json");

        // Fazer a requisição de autenticação
        Response response = given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        // Verificar se o token foi retornado
        String token = response.jsonPath().getString("token");
        assertNotNull(token, "Token deve ser retornado");
        assertFalse(token.isEmpty(), "Token não deve estar vazio");

        System.out.println("Token recebido: " + token);
    }

    @Test
    @DisplayName("Deve falhar ao autenticar com credenciais inválidas")
    public void testAuthInvalidCredentials() {
        AuthRequest authRequest = new AuthRequest("usuarioInvalido", "senhaInvalida");
        String jsonBody = gson.toJson(authRequest);

        given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body("reason", equalTo("Bad credentials"));
    }

    @Test
    @DisplayName("Deve falhar ao enviar dados vazios para autenticação")
    public void testAuthEmptyCredentials() {
        AuthRequest authRequest = new AuthRequest("", "");
        String jsonBody = gson.toJson(authRequest);

        given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body("reason", equalTo("Bad credentials"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/authMassa.csv", numLinesToSkip = 1)
    @DisplayName("Teste parametrizado de autenticação com massa de dados")
    public void testAuthWithMassData(String username, String password) {
        AuthRequest authRequest = new AuthRequest(username, password);
        String jsonBody = gson.toJson(authRequest);

        Response response = given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Para credenciais válidas (admin), deve retornar um token
        if ("admin".equals(username) && "password123".equals(password)) {
            assertThat(response.jsonPath().getString("token"), notNullValue());
        } else {
            // Para credenciais inválidas, deve retornar "Bad credentials"
            assertThat(response.jsonPath().getString("reason"), equalTo("Bad credentials"));
        }

        System.out.println("Teste para usuário: " + username + " - Status: " + response.statusCode());
    }

    // Método auxiliar para ler arquivos JSON
    private String readJsonFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // Método auxiliar para obter um token válido (usado em outros testes)
    public static String getValidToken() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");
        String jsonBody = gson.toJson(authRequest);

        Response response = given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post(uri + "/auth");

        return response.jsonPath().getString("token");
    }
}