package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
//    định nghĩa các elements, locators cho page Login
    //    private static final By USERNAME_INPUT = By.xpath("//input[@name='username']");
    //    c2:
    private static final By USERNAME_INPUT = By.cssSelector("input[name='username']");
//    By.cssSelector("input[name='username']")
//    By.cssSelector("input[placeholder='Username']")


    private static final By PASSWORD_INPUT = By.xpath("//input[@name='password']");
//    private static final By PASSWORD_INPUT = By.cssSelector("input[name='password']");

    private static final By LOGIN_BTN = By.xpath("//button[@type='submit']");
//    By.cssSelector("button[type='submit']")
//    By.cssSelector("button.oxd-button--main")

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

//    define các step test
    public void open() {
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        driver.get(url);

//        đợi cho đến khi input của username xuất hiện
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
    }

    public void enterUsername(String username) throws InterruptedException {
//        tìm element ô input username
        WebElement usernameInput = driver.findElement(USERNAME_INPUT);
        highlight(usernameInput);

//        clear để xóa sạch value trên ô input
        usernameInput.clear();
//        fill value username vào ô input
        usernameInput.sendKeys(username);
//        thêm wait nếu cần
        Thread.sleep(2000);
        unHighlight(usernameInput);
    }

    public void enterPassword(String password) throws InterruptedException {
        WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
        highlight(passwordInput);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        Thread.sleep(2000);
        unHighlight(passwordInput);
    }

    public void clickLoginBtn() {
        WebElement loginBtn = driver.findElement(LOGIN_BTN);
        highlight(loginBtn);
        loginBtn.click();
    }

//    hàm để login - tổng hợp 4 step ở trên
//    truy cập page -> enterUsername -> enterPassword -> click login btn
    public void login(String username, String password) throws InterruptedException {
        open();
        enterUsername(username);
        enterPassword(password);
        clickLoginBtn();
    }

}
