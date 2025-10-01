// 0 - nome do pacote

// 1 - bibliotecas
import java.io.IOException; // Para tratar exceções de entrada/saída (IO)
import java.nio.file.Files; // Para ler arquivos do sistema de arquivos
import java.nio.file.Paths; // Para manipular caminhos de arquivos

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.MethodOrderer; // Anotação para marcar métodos de teste (JUnit 5)
import org.junit.jupiter.api.Order; // Define a ordem de execução dos testes
import org.junit.jupiter.api.Test; // Controla a ordem dos métodos de teste na classe
import org.junit.jupiter.api.TestMethodOrder; // Estratégias para ordenar métodos de teste
import org.junit.jupiter.params.ParameterizedTest; // Permite testes parametrizados (com diferentes entradas)
import org.junit.jupiter.params.provider.CsvFileSource; // Biblioteca para converter objetos Java em JSON e vice-versa

import com.google.gson.Gson;

import static io.restassured.RestAssured.given;

// 2 - classe
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Ativa a ordenação dos testes
public class TestPet {

    // 2.1 - atributos
    static String ct = "application/json"; // Content-Type
    static String uriPet = "https://petstore.swagger.io/v2/pet"; // Base URL + endpoint
    static int petId = 95; // Código esperado do pet

    // 2.2 - funções e métodos
    // 2.2.1 - funções e métodos comuns / uteis
    // Função de leitura do arquivo JSON
    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    // 2.2.2 - métodos de teste
    @Test
    public void testPostPet() throws IOException {
        // Configura

        // carregar os dados do arquivo JSON do pet ￼￼￼
        String jsonBody = lerArquivoJson("src/test/resources/json/pet1.json");

        // Entrada - petId que está definido no nível da classe

        // Começa o teste via Rest-Assured
        given() // Dado que
                .contentType(ct) // O tipo do conteudo é
                .log().all() // Mostre tudo na ida
                .body(jsonBody) // Envie o corpo da requisição
                // Executa
                .when() // Quando
                .post(uriPet) // Chamamos o endpoint faznedo um POST
                // Valida
                .then() // Então
                .log().all() // Mostre tudo na volta
                .statusCode(200) // O código de resposta é 200
                .body("name", is("Snoopy")) // Verifica se o nome é Snoopy
                .body("id", is(petId)) // Verifica o código do pet
                .body("category.name", is("cachorro")) // Verifica se é cachorro
                .body("tags[0].name", is("vacinado")) // Verifica se está vacinado
        ; // Fim do given

    }

    @Test
    public void testGetPet() {
        // Configura

        // Entrada - petId que está definido no nível da classe

        // Saídas / Resultados esperados

        String petName = "Snoopy"; // Nome do pet esperado
        String categoryName = "cachorro"; // Categoria do pet esperado
        String tagName = "vacinado"; // Tag do pet esperado

        given()
                .contentType(ct) // O tipo do conteudo é
                .log().all() // Mostre tudo na ida
                // Quando é get ou delete não tem body
                // Executa
                .when() // Quando
                .get(uriPet + "/" + petId) // Montar o endpoint da URI/<petId>
                // Valida
                .then() // Então
                .log().all() // Mostre tudo na volta
                .statusCode(200) // O código de resposta é 200
                .body("name", is("Snoopy")) // Verifica se o nome é Snoopy
                .body("id", is(petId)) // Verifica o código do pet
                .body("category.name", is("cachorro")) // Verifica se é cachorro
                .body("tags[0].name", is("vacinado")) // Verifica se está vacinado

        ; // Fim do given

    }

    // Data Driven Testing(DDT) - Teste Direcionado por Dados / Teste com Massa
    // Teste com Json parametrizado

    @ParameterizedTest
    @Order(5)
    @CsvFileSource(resources = "/csv/petMassa.csv", numLinesToSkip = 1, delimiter = ',') // Lê o arquivo CSV, ignorando
                                                                                         // a primeira linha, e o

    // separador é vírgula
    public void testPostPetDDT(
            int petId, // Parâmetro do ID do pet
            String petName, // Parâmetro do nome do pet
            int catId, // Parâmetro do ID da categoria
            String catName, // Parâmetro do nome da categoria
            String status1, // Parâmetro do status do pet
            String status2 // Parâmetro do status do pet
    ) // Fim dos Parâmetros
    { // Início do códigos do método testPostPetDDT

        // Criar a classe pet para receber os dados do csv
        Pet pet = new Pet(); // Cria uma nova instância da classe Pet
        Pet.Category category = pet.new Category(); // Cria uma nova instância da sub classe Category dentro de Pet
        Pet.Tag[] tags = new Pet.Tag[2]; // Cria uma nova instância da sub classe Tags dentro de Pet
        tags[0] = pet.new Tag(); // Cria uma nova instância da sub classe Tag dentro de Pet
        tags[1] = pet.new Tag(); // Cria uma nova instância da sub classe Tag dentro de Pet

        pet.id = petId; // Atribui o ID do pet do csv ao atributo petId do objeto pet
        pet.category = category; // Atribui a categoria do csv ao atributo category do objeto pet
        pet.category.id = catId; // Atribui o ID da categoria do csv ao atributo catId do objeto pet
        pet.category.name = catName; // Atribui o nome da categoria do csv ao
        pet.name = petName; // Atribui o nome do pet do csv ao atributo name do objeto pet
        // pet.photoUrls não precisa ser incluído, pois será vazio
        pet.tags = tags; // Atribui as tags do csv ao atributo tags do objeto pet
        pet.tags[0].id = 9; // Atribui o ID da tag do csv ao atributo id do objeto pet
        pet.tags[0].name = "vacinado"; // Atribui o nome da tag do csv ao atributo name do objeto pet
        pet.status = status1; // Atribui o status do pet do csv ao atributo status1 do objeto pet. Status
                              // inicial usado no Post = "available"

        // Criar um Json para o Body a ser enviado a partir da classe Pet e do CSV
        Gson gson = new Gson(); // Cria uma instância do Gson para converter objetos Java em JSON
        String jsonBody = gson.toJson(pet); // Converte o objeto pet em uma string JSON(Convertendo o CSV em JSON)

        given()
                .contentType(ct) // O tipo do conteudo é
                .log().all() // Mostre tudo na ida
                .body(jsonBody) // Envie o corpo da requisição com o JSON gerado
                // Executa
                .when() // Quando
                .post(uriPet) // Chamamos o endpoint faznedo um POST
                // Valida
                .then() // Então
                .log().all() // Mostre tudo na volta
                .statusCode(200) // O código de resposta é 200
                .body("id", is(petId)) // Verifica o código do pet convertido para inteiro
                .body("name", is(petName)) // Verifica se o nome é igual ao do CSV
                .body("category.id", is(catId)) // Verifica se o id da categoria é igual ao do CSV
                .body("category.name", is(catName)) // Verifica se o name da categoria é igual ao do CSV
                .body("status", is(status1)) // Verifica se o status é igual ao do CSV

        ; // Fim do given
    }
}