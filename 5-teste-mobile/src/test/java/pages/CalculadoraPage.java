package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class CalculadoraPage {
    private AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;

    // Elementos da calculadora do Google
    @FindBy(id = "com.google.android.calculator:id/digit_0")
    private MobileElement btn0;

    @FindBy(id = "com.google.android.calculator:id/digit_1")
    private MobileElement btn1;

    @FindBy(id = "com.google.android.calculator:id/digit_2")
    private MobileElement btn2;

    @FindBy(id = "com.google.android.calculator:id/digit_3")
    private MobileElement btn3;

    @FindBy(id = "com.google.android.calculator:id/digit_4")
    private MobileElement btn4;

    @FindBy(id = "com.google.android.calculator:id/digit_5")
    private MobileElement btn5;

    @FindBy(id = "com.google.android.calculator:id/digit_6")
    private MobileElement btn6;

    @FindBy(id = "com.google.android.calculator:id/digit_7")
    private MobileElement btn7;

    @FindBy(id = "com.google.android.calculator:id/digit_8")
    private MobileElement btn8;

    @FindBy(id = "com.google.android.calculator:id/digit_9")
    private MobileElement btn9;

    @FindBy(id = "com.google.android.calculator:id/op_add")
    private MobileElement btnSomar;

    @FindBy(id = "com.google.android.calculator:id/eq")
    private MobileElement btnIgual;

    @FindBy(id = "com.google.android.calculator:id/result")
    private MobileElement resultado;

    @FindBy(id = "com.google.android.calculator:id/clr")
    private MobileElement btnLimpar;

    // Construtor
    public CalculadoraPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        System.out.println("📱 CalculadoraPage inicializada com sucesso");
    }

    // Método para digitar um número
    public void digitarNumero(String numero) {
        System.out.println("🔢 Digitando número: " + numero);
        
        for (char digito : numero.toCharArray()) {
            MobileElement botao = obterBotaoDigito(digito);
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

    // Método auxiliar para obter o botão do dígito
    private MobileElement obterBotaoDigito(char digito) {
        switch (digito) {
            case '0': return btn0;
            case '1': return btn1;
            case '2': return btn2;
            case '3': return btn3;
            case '4': return btn4;
            case '5': return btn5;
            case '6': return btn6;
            case '7': return btn7;
            case '8': return btn8;
            case '9': return btn9;
            default: throw new IllegalArgumentException("Dígito inválido: " + digito);
        }
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