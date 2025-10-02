package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CalculadoraPage {
    private AppiumDriver driver; // Removido o generic <MobileElement>
    private WebDriverWait wait;

    // Elementos da calculadora do Google - Usando WebElement ao invés de MobileElement
    @FindBy(id = "com.google.android.calculator:id/digit_0")
    private WebElement btn0;

    @FindBy(id = "com.google.android.calculator:id/digit_1")
    private WebElement btn1;

    @FindBy(id = "com.google.android.calculator:id/digit_2")
    private WebElement btn2;

    @FindBy(id = "com.google.android.calculator:id/digit_3")
    private WebElement btn3;

    @FindBy(id = "com.google.android.calculator:id/digit_4")
    private WebElement btn4;

    @FindBy(id = "com.google.android.calculator:id/digit_5")
    private WebElement btn5;

    @FindBy(id = "com.google.android.calculator:id/digit_6")
    private WebElement btn6;

    @FindBy(id = "com.google.android.calculator:id/digit_7")
    private WebElement btn7;

    @FindBy(id = "com.google.android.calculator:id/digit_8")
    private WebElement btn8;

    @FindBy(id = "com.google.android.calculator:id/digit_9")
    private WebElement btn9;

    @FindBy(id = "com.google.android.calculator:id/op_add")
    private WebElement btnSomar;

    @FindBy(id = "com.google.android.calculator:id/eq")
    private WebElement btnIgual;

    @FindBy(id = "com.google.android.calculator:id/result")
    private WebElement resultado;

    @FindBy(id = "com.google.android.calculator:id/clr")
    private WebElement btnLimpar;

    // Construtor - Removido o generic <MobileElement>
    public CalculadoraPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        System.out.println("📱 CalculadoraPage inicializada com sucesso");
    }

    // Método para digitar um número
    public void digitarNumero(String numero) {
        System.out.println("🔢 Digitando número: " + numero);
        
        for (char digito : numero.toCharArray()) {
            WebElement botao = obterBotaoDigito(digito);
            wait.until(ExpectedConditions.elementToBeClickable(botao));
            botao.click();
            
            // Pequena pausa entre cliques
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Método auxiliar para obter o botão do dígito - Retorna WebElement
    private WebElement obterBotaoDigito(char digito) {
        return switch (digito) { // Usando switch expression do Java 17
            case '0' -> btn0;
            case '1' -> btn1;
            case '2' -> btn2;
            case '3' -> btn3;
            case '4' -> btn4;
            case '5' -> btn5;
            case '6' -> btn6;
            case '7' -> btn7;
            case '8' -> btn8;
            case '9' -> btn9;
            default -> throw new IllegalArgumentException("Dígito inválido: " + digito);
        };
    }

    // Método para clicar no botão somar
    public void clicarSomar() {
        System.out.println("➕ Clicando no botão somar");
        wait.until(ExpectedConditions.elementToBeClickable(btnSomar));
        btnSomar.click();
    }

    // Método para clicar no botão igual
    public void clicarIgual() {
        System.out.println("= Clicando no botão igual");
        wait.until(ExpectedConditions.elementToBeClickable(btnIgual));
        btnIgual.click();
        
        // Aguarda o resultado aparecer
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método para obter o resultado
    public String obterResultado() {
        System.out.println("📊 Obtendo resultado...");
        wait.until(ExpectedConditions.visibilityOf(resultado));
        String res = resultado.getText();
        System.out.println("📊 Resultado obtido: " + res);
        return res;
    }

    // Método para limpar a calculadora
    public void limparCalculadora() {
        System.out.println("🧹 Limpando calculadora");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnLimpar));
            btnLimpar.click();
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("⚠️ Não foi possível limpar - continuando...");
        }
    }

    // Método principal para realizar soma
    public void realizarSoma(String num1, String num2) {
        System.out.println(String.format("🧮 Realizando operação: %s + %s", num1, num2));
        
        limparCalculadora();
        digitarNumero(num1);
        clicarSomar();
        digitarNumero(num2);
        clicarIgual();
        
        System.out.println("✅ Operação de soma concluída");
    }
}