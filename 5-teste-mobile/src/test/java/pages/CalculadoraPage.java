package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculadoraPage {
    private AppiumDriver<MobileElement> driver;

    // Elementos da calculadora
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

    public CalculadoraPage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void digitarNumero(String numero) {
        for (char digito : numero.toCharArray()) {
            switch (digito) {
                case '0': btn0.click(); break;
                case '1': btn1.click(); break;
                case '2': btn2.click(); break;
                case '3': btn3.click(); break;
                case '4': btn4.click(); break;
                case '5': btn5.click(); break;
                case '6': btn6.click(); break;
                case '7': btn7.click(); break;
                case '8': btn8.click(); break;
                case '9': btn9.click(); break;
            }
        }
    }

    public void clicarSomar() {
        btnSomar.click();
    }

    public void clicarIgual() {
        btnIgual.click();
    }

    public String obterResultado() {
        return resultado.getText();
    }

    public void limparCalculadora() {
        btnLimpar.click();
    }

    public void realizarSoma(String num1, String num2) {
        limparCalculadora();
        digitarNumero(num1);
        clicarSomar();
        digitarNumero(num2);
        clicarIgual();
    }
}