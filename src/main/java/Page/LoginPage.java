package Page;

import generic_keyword.WebElementsInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends WebElementsInteractions {

    By userNameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        //this.driver = driver;
        super(driver);
    }

    public ProductPage userLogin(String userName, String password) {
        //driver.get("https://www.saucedemo.com/");
        goToApplication("https://www.saucedemo.com/");
        sentText(userNameField, userName);
        sentText(passwordField, password);
        clickElement(loginButton);
        return new ProductPage(driver);
    }
}
