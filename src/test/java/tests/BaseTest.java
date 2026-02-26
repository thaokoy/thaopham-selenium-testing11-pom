package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import listener.ExtentTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;

@Listeners(ExtentTestListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

//    setup môi trường test
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-gpu", "--no-sandbox");

        if ("true".equals(System.getenv("CI"))){
            options.addArguments("--headless=new");
        }

//        khởi tạo ChromeDriver với cấu hình đang xét
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
