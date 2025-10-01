package stepsPO; // Define o pacote do arquivo

import org.junit.Assert; // Importa asserções para validação dos testes
import org.openqa.selenium.By; // Importa classe para localizar elementos
import org.openqa.selenium.WebDriver; // Importa o WebDriver para controlar o navegador

import io.cucumber.java.pt.Dado; // Importa anotação para step "Dado"
import io.cucumber.java.pt.Então; // Importa anotação para step "Então"
import io.cucumber.java.pt.Quando; // Importa anotação para step "Quando"
import pages.Base; // Importa classe base do WebDriver
import pages.CartPage; // Importa página do carrinho
import pages.CheckoutPage; // Importa página de checkout
import pages.ConfirmationPage; // Importa página de confirmação
import pages.HomePage; // Importa página inicial/login
import pages.InventoryPage; // Importa página de produtos

public class ComprarProdutosPO { // Classe dos steps do Cucumber

    WebDriver driver; // Instância do WebDriver
    HomePage homePage; // Página de login
    InventoryPage inventoryPage; // Página de produtos
    CartPage cartPage; // Página do carrinho
    CheckoutPage checkoutPage; // Página de checkout
    ConfirmationPage confirmationPage; // Página de confirmação

    @Dado("que estou na página de login do SauceDemo")
    public void que_estou_na_pagina_de_login_do_SauceDemo() {
        driver = Base.getDriver(); // Inicializa o driver
        driver.get("https://www.saucedemo.com/"); // Abre a página de login
        homePage = new HomePage(driver); // Instancia a página de login
    }

    @Quando("faço login com usuário {string} e senha {string}")
    public void faco_login_com_usuario_e_senha(String usuario, String senha) {
        homePage.login(usuario, senha); // Realiza o login

        // Tenta fechar o pop-up de senha do Chrome
        try {
            Thread.sleep(1000); // Aguarda o modal aparecer
            driver.findElement(By.xpath("//button[text()='OK']")).click(); // Clica no botão OK do modal
            Thread.sleep(500); // Aguarda o modal sumir
        } catch (Exception e) {
            // Ignora se não encontrar o modal
        }

        Assert.assertTrue(homePage.isLoggedIn()); // Valida se está logado
        inventoryPage = new InventoryPage(driver); // Instancia página de produtos
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } // Aguarda meio segundo
    }

    @Quando("adiciono o produto {string} ao carrinho")
    public void adiciono_o_produto_ao_carrinho(String produto) {
        inventoryPage.addProductToCart(produto); // Adiciona produto ao carrinho
        Assert.assertTrue(inventoryPage.isProductAdded(produto)); // Valida se produto foi adicionado
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } // Aguarda meio segundo
    }

    @Quando("acesso o carrinho")
    public void acesso_o_carrinho() {
        inventoryPage.goToCart(); // Vai para o carrinho
        cartPage = new CartPage(driver); // Instancia página do carrinho
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } // Aguarda meio segundo
    }

    @Quando("prossigo para o checkout")
    public void prossigo_para_o_checkout() {
        // Valida se algum produto está no carrinho
        Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack")
                || cartPage.isProductInCart("Sauce Labs Bike Light")
                || cartPage.isProductInCart("Sauce Labs Bolt T-Shirt")
                || cartPage.isProductInCart("Sauce Labs Fleece Jacket"));
        cartPage.checkout(); // Clica para ir ao checkout
        checkoutPage = new CheckoutPage(driver); // Instancia página de checkout
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } // Aguarda meio segundo
    }

    @Quando("preencho os dados de compra com nome {string}, sobrenome {string} e CEP {string}")
    public void preencho_os_dados_de_compra(String nome, String sobrenome, String cep) {
        checkoutPage.fillInformation(nome, sobrenome, cep); // Preenche dados do comprador
        Assert.assertTrue(checkoutPage.isOnOverviewPage()); // Valida se está na página de resumo
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } // Aguarda meio segundo
    }

    @Quando("finalizo a compra")
    public void finalizo_a_compra() {
        checkoutPage.finish(); // Finaliza a compra
        confirmationPage = new ConfirmationPage(driver); // Instancia página de confirmação
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); } // Aguarda meio segundo
    }

    @Então("devo ver a mensagem de confirmação {string}")
    public void devo_ver_a_mensagem_de_confirmacao(String mensagem) {
        Assert.assertEquals(mensagem, confirmationPage.getConfirmationMessage()); // Valida mensagem de confirmação
        // Remova o driver.quit(); aqui!
    }
}
