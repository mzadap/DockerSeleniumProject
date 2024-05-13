package Base_Test_Page;

import base.AppConstants;
import base.BasePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportHelper;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class BaseTest {

    public WebDriver driver;
    protected String browser;
    protected static ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();
    private static final ExtentReports REPORTS = ExtentReportHelper.getReportsObject();
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    ChromeOptions options = new ChromeOptions();
    FirefoxOptions firefoxOptions = new FirefoxOptions();



    @Parameters({"browserName"})
    @BeforeMethod
    public void setupTest(@Optional String browserName, ITestResult iTestResult) {



        if (browserName!= null) {
            browser = browserName;
        } else {
            browser = AppConstants.browserName;
        }

        System.out.println("Browser running " + browser);

        //ChromeOptions chromeOptions = new ChromeOptions();
        if (browser.equalsIgnoreCase("chrome")) {
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                //chromeOptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                //chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver();
            } else if (AppConstants.platform.equalsIgnoreCase("Remote")) {
                options.setPlatformName("linux");
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

            } else if (AppConstants.platform.equalsIgnoreCase("remote_git")) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--remote-allow-origins=*");
                //options.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(options);

            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            if (AppConstants.platform.equalsIgnoreCase("local")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (AppConstants.platform.equalsIgnoreCase("Remote")) {
                firefoxOptions.setPlatformName("linux");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), firefoxOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            logger.info("Browser name entered is not supported!!!" +browser);
            //System.out.println("Browser name entered is not supported!!!");
        }

        ExtentTest test = REPORTS.createTest(iTestResult.getMethod().getMethodName());
        threadLocal.set(test);
        threadLocal.get().log(Status.INFO, "Driver start time: " + LocalDateTime.now());
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) {
        if (iTestResult.isSuccess()) {
            threadLocal.get().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName() + "is successfull", ExtentColor.GREEN));
        } else {
            threadLocal.get().log(Status.FAIL, "Test failed due to: " + iTestResult.getThrowable());
            String screenshot = BasePage.getScreenshot(iTestResult.getMethod().getMethodName()+".jpg", driver);
            threadLocal.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.convertImg_Base64(screenshot)).build());
        }
        threadLocal.get().log(Status.INFO, "driver end time: " + LocalDateTime.now());
        driver.quit();
    }

    @AfterClass
    public void flushTestReport() {
        REPORTS.flush();
    }
}
