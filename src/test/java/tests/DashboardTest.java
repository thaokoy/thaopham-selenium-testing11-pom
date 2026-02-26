package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTest extends BaseTest{

    @Test(description = "Test logout")
    public void testLogout() throws InterruptedException {
//        khởi tạo LoginPage => login thành công
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "admin123");
//        khởi tạo DashboardPage => logout
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.logout();

//        verify
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("auth/login"),
                "Sau khi logout, URL phải quay về trang login"
        );
    }

    @Test(description = "Kiểm tra menu item đầy đủ hay chưa")
    public void testMenusDisplayed() throws  InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        boolean allMenusDisplayed = dashboardPage.areAllMenusDisplayed();

        Assert.assertTrue(
                allMenusDisplayed,
                "Tất cả menu chính phải được hiển thị trên Dashboard"
        );
    }

    @Test
    public void testScroll() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("Admin", "admin123");

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        Thread.sleep(3000);
        dashboardPage.scrollToBottom();
        Thread.sleep(3000);

        dashboardPage.scrollToTop();
        Thread.sleep(3000);

        dashboardPage.scrollToFooterAndHighlight();
        Thread.sleep(3000);
        Assert.assertTrue(true);
    }
}
