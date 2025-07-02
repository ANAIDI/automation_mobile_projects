package pages;

import drivers.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends PageObject{
    private AppiumDriver driver;

    // Localizadores
    private By loginButton = By.xpath("//*[@content-desc='test-LOGIN']");

    // Constructor
    public LoginPage() {
        this.driver = DriverManager.getDriver();
    }

    // Retorna un localizador dinámico por nombre de usuario
    private By getCredentialBy(String username) {
        return By.xpath("//*[contains(@text, '" + username + "')]");
    }

    // Metodo para hacer scroll hasta un texto visible
    public void scrollToText(String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
        ));
    }

    // Metodo para hacer clic sobre un elemento de usuario dinámico
    public void clickCredential(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By locator = getCredentialBy(username);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Metodo para hacer clic en el botón de login
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}