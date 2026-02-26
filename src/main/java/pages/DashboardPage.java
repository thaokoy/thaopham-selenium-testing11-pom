package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends BasePage{
    private static final By USER_DROPDOWN = By.xpath("//li[@class='oxd-userdropdown']");
//    By.className("oxd-userdropdown")
//    By.cssSelector("li.oxd-userdropdown")

    private static final By LOGOUT_LINK = By.linkText("Logout");
//    By.xpath("//a[text()='Logout']")
//    By.xpath("//a[@href='web/index.php/auth/logout']")

    private static final By FOOTER = By.xpath("//div[@class='oxd-layout-footer']");

//    locator chung cho casc menu item bên trái, tìm theo text
    private static By sideMenuByText(String text) {
        return By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text()='" + text + "']");
    }

    public void clickMenuMyInfo() {
       WebElement myInfo = wait.until(ExpectedConditions.elementToBeClickable(sideMenuByText("My Info")));

       myInfo.click();

       wait.until(ExpectedConditions.urlContains("pim/viewPersonalDetails"));
    }

    public void clickMenuAdmin() throws Exception {
        WebElement admin = wait.until(ExpectedConditions.elementToBeClickable(sideMenuByText("Admin")));
        highlight(admin);
        admin.click();
//        unHighlight(myInfo);

        Thread.sleep(3000);
//        wait.until(ExpectedConditions.urlContains("admin/viewSystemUsers"));
    }

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

//    define các step cho test case
    public void logout() {
//        đợi đến khi phần tử user dropdown có thể click được, sau đó
//        thực hiện click
        wait.until(ExpectedConditions.elementToBeClickable(USER_DROPDOWN)).click();

//        đợi đến khi link Logout có thể click được, sau đó sẽ click
        wait.until(ExpectedConditions.elementToBeClickable(LOGOUT_LINK)).click();

//        sau khi click logout, đợi đến khi điều hướng về trang login
        wait.until(ExpectedConditions.urlContains("auth/login"));
    }

//    hàm kiểm tra các menu có đầy đủ không
    public boolean areAllMenusDisplayed() {
//        tạo danh sách các menu item
        String[] menus = {
            "Admin",
            "PIM",
            "Leave",
            "Time",
            "Recruitment",
            "My Info",
            "Performance",
            "Dashboard",
            "Directory",
            "Maintenance",
            "Claim",
            "Buzz"
        };

        for (String menu: menus) {
            By locator = sideMenuByText(menu);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0,0);");
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'end'});", element);
        js.executeScript("arguments[0].style.border='2px solid red';", element);
        js.executeScript("arguments[0].style.backgroundColor='yellow';", element);
    }

    public void scrollToFooterAndHighlight() {
        WebElement footer = driver.findElement(FOOTER);
        scrollToElement(footer);
    }
}
