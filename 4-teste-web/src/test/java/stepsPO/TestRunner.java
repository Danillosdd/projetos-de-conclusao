package stepsPO; // Define o pacote do arquivo

import org.junit.platform.suite.api.ConfigurationParameter; // Permite configurar parâmetros da suíte
import org.junit.platform.suite.api.IncludeEngines; // Permite incluir engines de execução
import org.junit.platform.suite.api.SelectClasspathResource; // Permite selecionar recursos do classpath
import org.junit.platform.suite.api.Suite; // Indica que é uma suíte de testes

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME; // Constante para definir o pacote dos steps
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME; // Constante para definir plugins de relatório

@Suite // Indica que esta classe é uma suíte de testes
@IncludeEngines("cucumber") // Usa o engine do Cucumber para execução dos testes
@SelectClasspathResource("features") // Seleciona a pasta "features" no classpath para buscar os cenários
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty") // Define o plugin "pretty" para saída formatada
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps") // Define o pacote onde estão os steps do Cucumber
public class TestRunner { // Classe runner para executar os testes com JUnit
}