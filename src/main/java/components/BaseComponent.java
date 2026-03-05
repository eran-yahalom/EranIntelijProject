package components;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public abstract class BaseComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static String mainWindow;

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getPageHeader() {
        return driver.findElement(By.cssSelector(".page-title h1"))
                .getText()
                .trim();
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean click(WebElement element) {
        try {
            waitForClickability(element).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean fillText(WebElement element, String text) {
        try {
            waitForVisibility(element);
            element.clear();
            element.sendKeys(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected String getText(WebElement element) {
        try {
            return waitForVisibility(element).getText();
        } catch (Exception e) {
            return "";
        }
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return waitForVisibility(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void hover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void moveToNewWindow() {

        mainWindow = driver.getWindowHandle();

        Set<String> windows = driver.getWindowHandles();

        for (String win : windows) {
            if (!win.equals(mainWindow)) {
                driver.switchTo().window(win);
            }
        }
    }

    public void backToMainWindow() {
        driver.close();
        driver.switchTo().window(mainWindow);
    }

    public boolean clickAndMoveToSelectedSocialMedia(WebElement element) {
        try {
            click(element);
            moveToNewWindow();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public boolean clickOnAlertOKButton() {
        try {
            driver.switchTo().alert().accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
