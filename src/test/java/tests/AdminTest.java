package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;

@Epic("Dashboard")
@Feature("AdminPage")
public class AdminTest extends BaseTest{
    @Test(description = "Filter admin account")
    public void testFilterAdmin() throws Exception {
        Allure.step("Login voi Admin account", () -> {
            LoginPage loginPage = new LoginPage(driver, wait);
            loginPage.login("Admin", "admin123");
        });

        Allure.step("clickadmin menu", () -> {
            DashboardPage dashboardPage = new DashboardPage(driver, wait);
            dashboardPage.clickMenuAdmin();
        });

        AdminPage adminPage = new AdminPage(driver, wait);

        Allure.step("Fill admin input", () -> {
            adminPage.enterAdminName();
        });

        Allure.step("Click user role dropdown", () -> {
            adminPage.clickUserRoleDropdown();
        });

        Allure.step("Click user role dropdown", () -> {
            adminPage.selectAdminRole();
        });

        Allure.step("Click Search Button", () ->{
            adminPage.clickSearchBtn();
        });

        Allure.step("Verify data", () -> {
            Assert.assertTrue(adminPage.hasData(), "Phai co data voi filter Admin");
        });
    }
}
