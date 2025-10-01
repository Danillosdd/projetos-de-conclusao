import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    TestAuth.class,
    TestBooking.class
})
public class TestSuite {
    // Esta classe serve como um ponto de entrada para executar todos os testes
    // Não precisa de métodos, apenas das anotações acima
}