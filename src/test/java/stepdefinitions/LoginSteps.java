package stepdefinitions;
import pages.LoginPage;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

public class LoginSteps {
    @Steps
    LoginPage loginPage;

    @Given("Gino abre la aplicación")
    public void el_usuario_abre_la_aplicacion() {
        System.out.println("Iniciando aplicación...");

        loginPage.scrollToText("standard_user");
        loginPage.clickCredential("standard_user");
        loginPage.clickLoginButton();
    }
}