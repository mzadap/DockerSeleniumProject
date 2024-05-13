package generic_keyword;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebElementsInteractions {

    public WebDriver driver;

    public WebElementsInteractions(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    public void sentText(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void goToApplication(String url) {
        driver.get(url);
    }

    public String getTextData(By locator) {
        return driver.findElement(locator).getText();
    }
}
