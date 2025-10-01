import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    TesteAutenticacao.class,
    TesteReserva.class
})
public class SuiteDeTestes {
    // Esta classe serve como um organizador para executar todos os testes
    // Não precisa de implementação, as anotações fazem todo o trabalho
}