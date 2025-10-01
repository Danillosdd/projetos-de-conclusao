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
public class TesteAutenticacao {

    private static final String tipoConteudo = ContentType.JSON.toString();
    private static final String urlBase = "https://restful-booker.herokuapp.com";
    private static final Gson gson = new Gson();

    @BeforeAll
    public static void configurarTestes() {
        // Configuração global do RestAssured
        RestAssured.baseURI = urlBase;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Deve autenticar usuário com credenciais válidas")
    public void testarAutenticacaoComCredenciaisValidas() throws IOException {
        // Ler dados do arquivo JSON
        String corpoJson = lerArquivoJson("src/test/resources/json/autenticacao1.json");

        // Fazer a requisição de autenticação
        Response resposta = given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        // Verificar se o token foi retornado
        String token = resposta.jsonPath().getString("token");
        assertNotNull(token, "Token deve ser retornado");
        assertFalse(token.isEmpty(), "Token não deve estar vazio");

        System.out.println("Token recebido: " + token);
    }

    @Test
    @DisplayName("Deve falhar ao autenticar com credenciais inválidas")
    public void testarAutenticacaoComCredenciaisInvalidas() {
        SolicitacaoAutenticacao solicitacaoAuth = new SolicitacaoAutenticacao("usuarioInvalido", "senhaInvalida");
        String corpoJson = gson.toJson(solicitacaoAuth);

        given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body("reason", equalTo("Bad credentials"));
    }

    @Test
    @DisplayName("Deve falhar ao enviar dados vazios para autenticação")
    public void testarAutenticacaoComCredenciaisVazias() {
        SolicitacaoAutenticacao solicitacaoAuth = new SolicitacaoAutenticacao("", "");
        String corpoJson = gson.toJson(solicitacaoAuth);

        given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .body("reason", equalTo("Bad credentials"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/massaDadosAutenticacao.csv", numLinesToSkip = 1)
    @DisplayName("Teste parametrizado de autenticação com massa de dados")
    public void testarAutenticacaoComMassaDeDados(String nomeUsuario, String senha) {
        SolicitacaoAutenticacao solicitacaoAuth = new SolicitacaoAutenticacao(nomeUsuario, senha);
        String corpoJson = gson.toJson(solicitacaoAuth);

        Response resposta = given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Para credenciais válidas (admin), deve retornar um token
        if ("admin".equals(nomeUsuario) && "password123".equals(senha)) {
            assertThat(resposta.jsonPath().getString("token"), notNullValue());
        } else {
            // Para credenciais inválidas, deve retornar "Bad credentials"
            assertThat(resposta.jsonPath().getString("reason"), equalTo("Bad credentials"));
        }

        System.out.println("Teste para usuário: " + nomeUsuario + " - Status: " + resposta.statusCode());
    }

    // Método auxiliar para ler arquivos JSON
    private String lerArquivoJson(String caminhoArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
    }

    // Método auxiliar para obter um token válido (usado em outros testes)
    public static String obterTokenValido() {
        SolicitacaoAutenticacao solicitacaoAuth = new SolicitacaoAutenticacao("admin", "password123");
        String corpoJson = gson.toJson(solicitacaoAuth);

        Response resposta = given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post(urlBase + "/auth");

        return resposta.jsonPath().getString("token");
    }
}