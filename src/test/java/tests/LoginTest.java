package tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import listener.ExtentTestListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CSVDataProvider;
import utils.ExtentManager;

@Epic("Auth")
@Feature("login")
public class LoginTest extends BaseTest {

    @Test(description = "Test login thành công")
    public void testLoginSuccess() throws InterruptedException {
//        khởi tạo đối tượng LoginPage để viết test case
        LoginPage loginPage = new LoginPage(driver, wait);

////        khoi tao test log
//        ExtentTest currentTest = ExtentManager.getCurrentTest();
//        System.out.println("info currentTest 1: " + currentTest);
//
//        loginPage.login("Admin", "admin123");
//        currentTest.log(Status.INFO, "Login với account Admin");
//
//        wait.until(ExpectedConditions.urlContains("dashboard"));
//        currentTest.log(Status.INFO, "Đợi xuất hiện URL có chu 'dashboard'");

    Allure.step("Login voi account Admin", () -> {
        loginPage.login("Admin", "admin123");
    });

//
    Allure.step("Doi URL xuat hien chu dashboard", () -> {
        wait.until(ExpectedConditions.urlContains("dashboard"));
    });

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("dashboard"),
                "Sau khi login thành công, URL phải chứa 'dashboard'"
        );
    }

    @Test(dataProvider = "csvData", dataProviderClass = CSVDataProvider.class,
            description = "test login data tu csv file")
    public void testLoginData(String username, String password, String expectedResult) throws InterruptedException {
        //        khởi tạo đối tượng LoginPage để viết test case
        LoginPage loginPage = new LoginPage(driver, wait);

//        khoi tao test log
        ExtentTest currentTest = ExtentManager.getCurrentTest();
        System.out.println("info currentTest 1: " + currentTest);

        loginPage.login(username, password);
        currentTest.log(Status.INFO, "Login với account Admin");

//        wait.until(ExpectedConditions.urlContains("dashboard"));
//        currentTest.log(Status.INFO, "Đợi xuất hiện URL có chu 'dashboard'");
//
//        String currentUrl = driver.getCurrentUrl();
//        Assert.assertTrue(
//                currentUrl.contains("dashboard"),
//                "Sau khi login thành công, URL phải chứa 'dashboard'"
//        );
        if(expectedResult.equals("success")) {
            wait.until(ExpectedConditions.urlContains("dashboard"));
            currentTest.log(Status.INFO, "Đợi xuất hiện URL có chu 'dashboard'");

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(
                    currentUrl.contains("dashboard"),
                    "Sau khi login thành công, URL phải chứa 'dashboard'"
            );
        } else {
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(
                    currentUrl.contains("login"),
                    "Van phai o trang login cho truong hop login fail");
        }
    }
}
