import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

@DisplayName("Testes da API Restful Booker - Booking")
public class TestBooking {

    private static final String ct = ContentType.JSON.toString();
    private static final String uri = "https://restful-booker.herokuapp.com";
    private static final Gson gson = new Gson();
    private static String authToken;

    @BeforeAll
    public static void setup() {
        // Configuração global do RestAssured
        RestAssured.baseURI = uri;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
        // Obter token de autenticação
        authToken = TestAuth.getValidToken();
        System.out.println("Token obtido para os testes: " + authToken);
    }

    @Test
    @DisplayName("Deve criar um novo booking com sucesso")
    public void testCreateBooking() throws IOException {
        // Ler dados do arquivo JSON
        String jsonBody = readJsonFile("src/test/resources/json/booking1.json");

        // Fazer a requisição para criar um booking
        Response response = given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo("João"))
                .body("booking.lastname", equalTo("Silva"))
                .body("booking.totalprice", equalTo(150))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.bookingdates.checkin", equalTo("2025-10-01"))
                .body("booking.bookingdates.checkout", equalTo("2025-10-05"))
                .body("booking.additionalneeds", equalTo("Breakfast"))
                .extract()
                .response();

        // Extrair e validar o ID do booking criado
        int bookingId = response.jsonPath().getInt("bookingid");
        assertTrue(bookingId > 0, "ID do booking deve ser maior que 0");

        System.out.println("Booking criado com ID: " + bookingId);
    }

    @Test
    @DisplayName("Deve consultar todos os bookings")
    public void testGetAllBookings() {
        given()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("[0]", hasKey("bookingid"));

        System.out.println("Lista de bookings recuperada com sucesso");
    }

    @Test
    @DisplayName("Deve consultar booking por ID")
    public void testGetBookingById() throws IOException {
        // Primeiro, criar um booking para consultar
        String jsonBody = readJsonFile("src/test/resources/json/booking1.json");
        
        Response createResponse = given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post("/booking");

        int bookingId = createResponse.jsonPath().getInt("bookingid");

        // Consultar o booking criado
        given()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("João"))
                .body("lastname", equalTo("Silva"))
                .body("totalprice", equalTo(150))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2025-10-01"))
                .body("bookingdates.checkout", equalTo("2025-10-05"))
                .body("additionalneeds", equalTo("Breakfast"));

        System.out.println("Booking " + bookingId + " consultado com sucesso");
    }

    @Test
    @DisplayName("Deve atualizar um booking existente")
    public void testUpdateBooking() throws IOException {
        // Primeiro, criar um booking para atualizar
        String jsonBody = readJsonFile("src/test/resources/json/booking1.json");
        
        Response createResponse = given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post("/booking");

        int bookingId = createResponse.jsonPath().getInt("bookingid");

        // Criar dados atualizados
        BookingDates updatedDates = new BookingDates("2025-11-01", "2025-11-10");
        Booking updatedBooking = new Booking("Maria", "Santos", 200, false, updatedDates, "Late checkout");
        String updatedJson = gson.toJson(updatedBooking);

        // Atualizar o booking
        given()
                .contentType(ct)
                .header("Cookie", "token=" + authToken)
                .body(updatedJson)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Maria"))
                .body("lastname", equalTo("Santos"))
                .body("totalprice", equalTo(200))
                .body("depositpaid", equalTo(false))
                .body("additionalneeds", equalTo("Late checkout"));

        System.out.println("Booking " + bookingId + " atualizado com sucesso");
    }

    @Test
    @DisplayName("Deve deletar um booking existente")
    public void testDeleteBooking() throws IOException {
        // Primeiro, criar um booking para deletar
        String jsonBody = readJsonFile("src/test/resources/json/booking1.json");
        
        Response createResponse = given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post("/booking");

        int bookingId = createResponse.jsonPath().getInt("bookingid");

        // Deletar o booking
        given()
                .header("Cookie", "token=" + authToken)
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201);

        // Verificar se o booking foi deletado (deve retornar 404)
        given()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(404);

        System.out.println("Booking " + bookingId + " deletado com sucesso");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/bookingMassa.csv", numLinesToSkip = 1)
    @DisplayName("Teste parametrizado de criação de booking com massa de dados")
    public void testCreateBookingWithMassData(String firstname, String lastname, 
                                            int totalprice, boolean depositpaid, 
                                            String checkin, String checkout, 
                                            String additionalneeds) {
        
        BookingDates dates = new BookingDates(checkin, checkout);
        Booking booking = new Booking(firstname, lastname, totalprice, depositpaid, dates, additionalneeds);
        String jsonBody = gson.toJson(booking);

        Response response = given()
                .contentType(ct)
                .body(jsonBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo(firstname))
                .body("booking.lastname", equalTo(lastname))
                .extract()
                .response();

        int bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Booking criado para " + firstname + " " + lastname + " com ID: " + bookingId);
    }

    @Test
    @DisplayName("Deve falhar ao tentar acessar booking inexistente")
    public void testGetNonExistentBooking() {
        given()
                .when()
                .get("/booking/999999")
                .then()
                .statusCode(404);

        System.out.println("Teste de booking inexistente passou - retornou 404 como esperado");
    }

    @Test
    @DisplayName("Deve filtrar bookings por nome")
    public void testGetBookingsByName() {
        given()
                .queryParam("firstname", "João")
                .queryParam("lastname", "Silva")
                .when()
                .get("/booking")
                .then()
                .statusCode(200);

        System.out.println("Filtro por nome funcionou corretamente");
    }

    @Test
    @DisplayName("Deve filtrar bookings por data de check-in")
    public void testGetBookingsByCheckinDate() {
        given()
                .queryParam("checkin", "2025-10-01")
                .when()
                .get("/booking")
                .then()
                .statusCode(200);

        System.out.println("Filtro por data de check-in funcionou corretamente");
    }

    // Método auxiliar para ler arquivos JSON
    private String readJsonFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}