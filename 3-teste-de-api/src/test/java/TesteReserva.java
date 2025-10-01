import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
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

@DisplayName("Testes da API Restful Booker - Gerenciamento de Reservas")
public class TesteReserva {

    private static final String tipoConteudo = ContentType.JSON.toString();
    private static final String urlBase = "https://restful-booker.herokuapp.com";
    private static final Gson gson = new Gson();
    private static String tokenAutenticacao;

    @BeforeAll
    public static void configurarTestes() {
        // Configuração global do RestAssured
        RestAssured.baseURI = urlBase;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        // Obter token de autenticação para testes que necessitam
        tokenAutenticacao = TesteAutenticacao.obterTokenValido();
        System.out.println("Token de autenticação obtido para os testes");
    }

    @Test
    @DisplayName("Deve criar uma nova reserva com sucesso")
    public void testarCriacaoDeNovaReserva() throws IOException {
        // Ler dados do arquivo JSON
        String corpoJson = lerArquivoJson("src/test/resources/json/reserva1.json");

        // Fazer a requisição para criar reserva
        Response resposta = given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("booking.firstname", notNullValue())
                .body("booking.lastname", notNullValue())
                .body("booking.totalprice", greaterThan(0))
                .extract()
                .response();

        // Verificar se a resposta contém os dados esperados
        int idReserva = resposta.jsonPath().getInt("bookingid");
        assertThat(idReserva, greaterThan(0));

        System.out.println("Nova reserva criada com ID: " + idReserva);
    }

    @Test
    @DisplayName("Deve consultar todas as reservas existentes")
    public void testarConsultaDeTodasAsReservas() {
        given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));

        System.out.println("Consulta de todas as reservas realizada com sucesso");
    }

    @Test
    @DisplayName("Deve consultar uma reserva específica por ID")
    public void testarConsultaDeReservaPorId() throws IOException {
        // Primeiro, criar uma reserva para garantir que existe
        String corpoJson = lerArquivoJson("src/test/resources/json/reserva1.json");
        
        Response respostaCriacao = given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        int idReserva = respostaCriacao.jsonPath().getInt("bookingid");

        // Agora consultar a reserva criada
        given()
                .when()
                .get("/booking/" + idReserva)
                .then()
                .statusCode(200)
                .body("firstname", notNullValue())
                .body("lastname", notNullValue())
                .body("totalprice", greaterThan(0));

        System.out.println("Consulta da reserva ID " + idReserva + " realizada com sucesso");
    }

    @Test
    @DisplayName("Deve atualizar uma reserva existente")
    public void testarAtualizacaoDeReserva() throws IOException {
        // Primeiro, criar uma reserva para atualizar
        String corpoJsonCriacao = lerArquivoJson("src/test/resources/json/reserva1.json");
        
        Response respostaCriacao = given()
                .contentType(tipoConteudo)
                .body(corpoJsonCriacao)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        int idReserva = respostaCriacao.jsonPath().getInt("bookingid");

        // Obter um token fresco para a atualização
        String tokenFresco = obterTokenFresco();

        // Criar dados atualizados
        DatasReserva datasAtualizadas = new DatasReserva("2024-02-01", "2024-02-05");
        Reserva reservaAtualizada = new Reserva("João", "Silva", 250, true, datasAtualizadas, "Café da manhã");
        String corpoJsonAtualizacao = gson.toJson(reservaAtualizada);

        // Atualizar a reserva com token fresco
        given()
                .contentType(tipoConteudo)
                .header("Cookie", "token=" + tokenFresco)
                .body(corpoJsonAtualizacao)
                .when()
                .put("/booking/" + idReserva)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("João"))
                .body("lastname", equalTo("Silva"))
                .body("totalprice", equalTo(250));

        System.out.println("Reserva ID " + idReserva + " atualizada com sucesso");
    }

    @Test
    @DisplayName("Deve deletar uma reserva existente")
    public void testarDelecaoDeReserva() throws IOException {
        // Primeiro, criar uma reserva para deletar
        String corpoJson = lerArquivoJson("src/test/resources/json/reserva1.json");
        
        Response respostaCriacao = given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        int idReserva = respostaCriacao.jsonPath().getInt("bookingid");

        // Obter um token fresco para a deleção
        String tokenFresco = obterTokenFresco();

        // Deletar a reserva com token fresco
        given()
                .header("Cookie", "token=" + tokenFresco)
                .when()
                .delete("/booking/" + idReserva)
                .then()
                .statusCode(201);

        // Verificar se a reserva foi deletada
        given()
                .when()
                .get("/booking/" + idReserva)
                .then()
                .statusCode(404);

        System.out.println("Reserva ID " + idReserva + " deletada com sucesso");
    }

    @Test
    @DisplayName("Deve filtrar reservas por nome")
    public void testarFiltroDeReservasPorNome() {
        given()
                .queryParam("firstname", "Jim")
                .when()
                .get("/booking")
                .then()
                .statusCode(200);

        System.out.println("Filtro por nome 'Jim' executado com sucesso");
    }

    @Test
    @DisplayName("Deve filtrar reservas por data de check-in")
    public void testarFiltroDeReservasPorDataCheckin() {
        given()
                .queryParam("checkin", "2023-01-01")
                .when()
                .get("/booking")
                .then()
                .statusCode(200);

        System.out.println("Filtro por data de check-in executado com sucesso");
    }

    @Test
    @DisplayName("Deve retornar 404 para reserva inexistente")
    public void testarConsultaDeReservaInexistente() {
        given()
                .when()
                .get("/booking/99999")
                .then()
                .statusCode(404);

        System.out.println("Teste de reserva inexistente executado com sucesso");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/massaDadosReserva.csv", numLinesToSkip = 1)
    @DisplayName("Teste parametrizado de criação de reservas com massa de dados")
    public void testarCriacaoDeReservaComMassaDeDados(String primeiroNome, String ultimoNome, 
                                                      int precoTotal, boolean depositoPago,
                                                      String dataCheckin, String dataCheckout, 
                                                      String necessidadesAdicionais) {
        
        // Criar objeto de reserva com os dados do CSV
        DatasReserva datasReserva = new DatasReserva(dataCheckin, dataCheckout);
        Reserva novaReserva = new Reserva(primeiroNome, ultimoNome, precoTotal, depositoPago, 
                                         datasReserva, necessidadesAdicionais);
        String corpoJson = gson.toJson(novaReserva);

        // Fazer a requisição
        Response resposta = given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .extract()
                .response();

        int idReserva = resposta.jsonPath().getInt("bookingid");
        System.out.println("Reserva criada para " + primeiroNome + " " + ultimoNome + " - ID: " + idReserva);
    }

    // Método auxiliar para ler arquivos JSON
    private String lerArquivoJson(String caminhoArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
    }

    // Método auxiliar para obter token fresco
    private String obterTokenFresco() {
        SolicitacaoAutenticacao solicitacaoAuth = new SolicitacaoAutenticacao("admin", "password123");
        String corpoJson = gson.toJson(solicitacaoAuth);

        Response resposta = given()
                .contentType(tipoConteudo)
                .body(corpoJson)
                .when()
                .post("/auth");

        return resposta.jsonPath().getString("token");
    }
}