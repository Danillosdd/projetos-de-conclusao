package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CalculadoraPage {
    private AppiumDriver driver;
    private WebDriverWait wait;

    // ✅ CORREÇÃO: Múltiplos seletores para o resultado
    @AndroidFindBy(id = "com.google.android.calculator:id/result_final")
    private WebElement resultadoFinal;
    
    @AndroidFindBy(id = "com.google.android.calculator:id/result_preview") 
    private WebElement resultadoPreview;
    
    @AndroidFindBy(id = "com.google.android.calculator:id/result")
    private WebElement resultado;
    
    @AndroidFindBy(id = "com.google.android.calculator:id/formula")
    private WebElement formula;

    // Elementos dos números
    @AndroidFindBy(id = "com.google.android.calculator:id/digit_0")
    private WebElement botao0;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_1")
    private WebElement botao1;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_2")
    private WebElement botao2;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_3")
    private WebElement botao3;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_4")
    private WebElement botao4;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_5")
    private WebElement botao5;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_6")
    private WebElement botao6;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_7")
    private WebElement botao7;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_8")
    private WebElement botao8;

    @AndroidFindBy(id = "com.google.android.calculator:id/digit_9")
    private WebElement botao9;

    // Elementos das operações
    @AndroidFindBy(id = "com.google.android.calculator:id/op_add")
    private WebElement botaoSomar;

    @AndroidFindBy(id = "com.google.android.calculator:id/eq")
    private WebElement botaoIgual;

    @AndroidFindBy(id = "com.google.android.calculator:id/clr")
    private WebElement botaoLimpar;

    public CalculadoraPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        System.out.println("📱 CalculadoraPage inicializada com sucesso");
    }

    public void realizarSoma(String numero1, String numero2) {
        System.out.println("🧮 Realizando operação: " + numero1 + " + " + numero2);
        
        limparCalculadora();
        digitarNumero(numero1);
        clicarSomar();
        digitarNumero(numero2);
        clicarIgual();
        
        System.out.println("✅ Operação de soma concluída");
    }

    private void limparCalculadora() {
        System.out.println("🧹 Limpando calculadora");
        wait.until(ExpectedConditions.elementToBeClickable(botaoLimpar)).click();
    }

    private void digitarNumero(String numero) {
        System.out.println("🔢 Digitando número: " + numero);
        for (char digito : numero.toCharArray()) {
            WebElement botao = obterBotaoNumero(digito);
            wait.until(ExpectedConditions.elementToBeClickable(botao)).click();
        }
    }

    private WebElement obterBotaoNumero(char digito) {
        switch (digito) {
            case '0': return botao0;
            case '1': return botao1;
            case '2': return botao2;
            case '3': return botao3;
            case '4': return botao4;
            case '5': return botao5;
            case '6': return botao6;
            case '7': return botao7;
            case '8': return botao8;
            case '9': return botao9;
            default: throw new IllegalArgumentException("Dígito inválido: " + digito);
        }
    }

    private void clicarSomar() {
        System.out.println("➕ Clicando no botão somar");
        wait.until(ExpectedConditions.elementToBeClickable(botaoSomar)).click();
    }

    private void clicarIgual() {
        System.out.println("= Clicando no botão igual");
        wait.until(ExpectedConditions.elementToBeClickable(botaoIgual)).click();
    }

    public String obterResultado() {
        System.out.println("📊 Obtendo resultado...");
        
        // ✅ CORREÇÃO: Tentar múltiplos seletores para o resultado
        try {
            // Tenta primeiro o result_final
            return wait.until(ExpectedConditions.visibilityOf(resultadoFinal)).getText();
        } catch (Exception e1) {
            try {
                // Se não funcionar, tenta result_preview
                return wait.until(ExpectedConditions.visibilityOf(resultadoPreview)).getText();
            } catch (Exception e2) {
                try {
                    // Se não funcionar, tenta result
                    return wait.until(ExpectedConditions.visibilityOf(resultado)).getText();
                } catch (Exception e3) {
                    try {
                        // Por último, tenta formula
                        return wait.until(ExpectedConditions.visibilityOf(formula)).getText();
                    } catch (Exception e4) {
                        throw new RuntimeException("❌ Não foi possível encontrar o elemento resultado em nenhum dos seletores testados", e4);
                    }
                }
            }
        }
    }
}