package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.MyInfoPage;

import java.net.URL;
import java.nio.file.Path;

@Epic("Profile")
@Feature("Upload Avatar")

public class AvatarUploadTest  extends BaseTest{

    private String getAvatarPath() throws Exception {
        URL url = getClass().getResource("/avatar/testing11.jpg");
        Assert.assertNotNull(url, "Dat anh vao duong dan /src/main/resources/avatar/testing11.jpg");
        return Path.of(url.toURI()).toAbsolutePath().toString();
    }
    @Test(description = "Login -> My Info -> Click avatar -> Click + btn -> choose file -> upload")
    public void testUploadAvatar() throws Exception {
        String avatarPath = getAvatarPath();

        Allure.step("Login Admin account and waiting dashboard", () ->  {
                    LoginPage loginPage = new LoginPage(driver, wait);
                    loginPage.login("Admin", "admin123");
                    wait.until(ExpectedConditions.urlContains("dashboard"));
                }
                );
        Allure.step("Access My Info page and waiting", () -> {
            DashboardPage dashboardPage = new DashboardPage(driver, wait);
            dashboardPage.clickMenuMyInfo();
        });

        Allure.step("Upload avatar", () -> {
            MyInfoPage myInfoPage = new MyInfoPage(driver, wait);
            myInfoPage.uploadAvatar(avatarPath);
            Thread.sleep(3000);
        });

        Allure.step("Verify My Info page", () -> {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(
                    currentUrl.contains("pim/viewPhotograph"),
                    ("Sau khi ")

            );

        });
    }
}
