package Base_Test_Page;

import Base_Test_Page.BaseTest;
import Page.LoginPage;
import Page.ProductPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    ProductPage productPage;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test
    public void loginTests() {
        String username = "standard_user";
        String password = "secret_sauce";
        loginPage = new LoginPage(driver);
        productPage = loginPage.userLogin(username, password);
        logger.info("username is: " + username + " Password is " + password);
        System.out.println(productPage.getTitleOfPage());
    }
}
