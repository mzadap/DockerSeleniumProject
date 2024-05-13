package Page;

import generic_keyword.WebElementsInteractions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends WebElementsInteractions {

    By getTitleOfProductPage = By.xpath("//span[text()='Products']");

    By getTextOfFirstItem = By.xpath("//div[text()='Sauce Labs Backpack']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleOfPage() {
        return getTextData(getTitleOfProductPage);
        //return driver.findElement(getTitleOfProductPage).getText();
    }

    public String getTextItemName() {
        return getTextData(getTextOfFirstItem);
    }
}
