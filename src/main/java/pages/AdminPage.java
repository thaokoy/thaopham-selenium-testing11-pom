package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdminPage extends BasePage{
    private static final By userInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");




//    <div  data-v-40acfd38="" data-v-13cf171c="" role="listbox" class="oxd-select-dropdown --positon-bottom" loading="false">
//        <div data-v-d130bb63="" data-v-13cf171c="" role="option" class="oxd-select-option">-- Select --
//        </div>
//        <div data-v-d130bb63="" data-v-13cf171c="" role="option" class="oxd-select-option --selected">
//            <span data-v-13cf171c="">Admin</span>
//        </div>
//        <div data-v-d130bb63="" data-v-13cf171c="" role="option" class="oxd-select-option">
//            <span data-v-13cf171c="">ESS</span>
//        </div>
//    </div>
    private static final By adminRoleOption = By.xpath("(//div[@role='option'])[2]");

    private static final  By userRoleDropdown = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]");

    private static final By searchBtn = By.xpath("//button[@type='submit']");

    private static final By dataRows = By.xpath("//div[@class='old-table-card']");

    public AdminPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    public void enterAdminName() throws Exception {
//        WebElement input = driver.findElement(userInput);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(userInput));
        highlight(input);
        input.sendKeys("Admin");
        Thread.sleep(2000);
        unHighlight(input);
    }

    public void  clickUserRoleDropdown() throws Exception {
        WebElement dropdown = driver.findElement(userRoleDropdown);
        highlight(dropdown);
        dropdown.click();
        Thread.sleep(2000);
        unHighlight(dropdown);
    }

    public void selectAdminRole() throws Exception {
        WebElement adminRole = driver.findElement(adminRoleOption);
        highlight(adminRole);
        adminRole.click();
    }


    public void clickSearchBtn() throws Exception {
        WebElement searchbtn = driver.findElement(searchBtn);
        highlight(searchbtn);
        Thread.sleep(10000);
        unHighlight(searchbtn);
    }

    public boolean hasData() {
        List<WebElement> rows = driver.findElements(dataRows);
        return rows.size() > 0;
    }
}


