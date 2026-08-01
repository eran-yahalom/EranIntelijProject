package components;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

@Log4j2
public abstract class BaseComponent {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    protected String mainWindow;

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
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
            wait.until(ExpectedConditions.visibilityOf(element));
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            return true;

        } catch (Exception e) {
            try {
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", element);
                return true;

            } catch (Exception ex) {
                log.error("Fail to click on button{}", element.getText());
                return false;
            }
        }
    }

    protected boolean fillText(WebElement element, String text) {
        try {
            waitForVisibility(element);
            element.clear();
            element.sendKeys(text);
            return true;
        } catch (Exception e) {
            log.error("Fail to fill text in element: {} | Error: {}", element.getText(), e.getMessage());
            return false;
        }
    }

    protected String getText(WebElement element) {
        try {
            return waitForVisibility(element).getText();
        } catch (Exception e) {
            log.error("Fail to get text from element: {} | Error: {}", element.getText(), e.getMessage());
            return "";
        }
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return waitForVisibility(element).isDisplayed();
        } catch (Exception e) {
            log.error("Fail to check visibility of element: {} | Error: {}", element.getText(), e.getMessage());
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

    public void waitForElementToBeClickable(By element) {
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
            log.error("Fail to click and move to selected social media: {} | Error: {}", element.getText(), e.getMessage());
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
            log.error("Fail to click on alert OK button | Error: {}", e.getMessage());
            return false;
        }
    }

    public boolean selectOptionFromDropdownByVisibleText(WebElement element, String value) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Select select = new Select(element);
            select.selectByVisibleText(value);
            return true;
        } catch (StaleElementReferenceException e) {
            log.error("Stale element reference: {} | Error: {}", element.getText(), e.getMessage());
            return false;
        }
    }

    public boolean clickOnFooterLinkByIndex(By footerLinksLocator, int index) {
        try {
            List<WebElement> freshLinks = driver.findElements(footerLinksLocator);

            if (index < freshLinks.size()) {
                return clickOnStaleElement(freshLinks.get(index));
            }
            log.info("Index " + index + " not found in footer links.");
            return false;
        } catch (StaleElementReferenceException e) {
            return clickOnStaleElement(driver.findElements(footerLinksLocator).get(index));
        }
    }

    protected boolean clickOnStaleElement(WebElement element) {
        int maxRetries = 3;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                click(element);
                return true;
            } catch (StaleElementReferenceException e) {
                log.warn("Attempt {}/{} failed due to StaleElementReferenceException. Retrying...", attempt, maxRetries);
                if (attempt == maxRetries) {
                    log.error("Reached maximum retry attempts for element.");
                    return false;
                }
                waitForSmallInterval();
            } catch (Exception e) {
                log.error("Uncaught exception while clicking element: {}", e.getMessage());
                return false;
            }
        }

        return false;
    }

    protected void waitForSmallInterval() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Interrupted during small interval wait", e);
        }
    }

    public String getPageTitle() {
        // return driver.getTitle();
        return driver.findElement(By.cssSelector("[class='page-title'] h1")).getText();
    }

    public boolean clickOnSelectedNameTag(List<WebElement> elements, String tagName) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                wait.until(ExpectedConditions.visibilityOfAllElements(elements));

                for (WebElement element : elements) {
                    if (element.getText().trim().equals(tagName)) {
                        return click(element);
                    }
                }

                log.warn("Tag '{}' was not found in popularTagsList", tagName);
                return false;

            } catch (StaleElementReferenceException e) {
                attempts++;
                log.warn("StaleElement encountered while scanning tags. Retrying attempt {}/2...", attempts);
            } catch (Exception e) {
                log.error("Failed to click on tag: {}. Exception: {}", tagName, e.getMessage());
                return false;
            }
        }
        return false;
    }

        public boolean clickOnCategoryNameLink(List<WebElement> mainCategoryElement,List<WebElement> subCategoryElement,String mainCategoryName, String subCategoryName) {
            int attempts = 0;
            while (attempts < 2) {
                try {
                    // 1. מחכים לרשימת הקטגוריות הראשיות ומוצאים את המבוקשת
                    List<WebElement> mainCategories = wait.until(
                            ExpectedConditions.visibilityOfAllElements(mainCategoryElement)
                    );

                    WebElement targetMainCategory = null;
                    for (WebElement category : mainCategories) {
                        if (category.getText().trim().equalsIgnoreCase(mainCategoryName)) {
                            targetMainCategory = category;
                            break;
                        }
                    }

                    if (targetMainCategory == null) {
                        log.warn("Main category '{}' was not found", mainCategoryName);
                        return false;
                    }

                    // לחיצה על הקטגוריה הראשית
                    click(targetMainCategory);

                    // 2. תרחיש 1: אם לא הוגדרה תת-קטגוריה -> סיימנו בהצלחה!
                    if (subCategoryName == null || subCategoryName.trim().isEmpty()) {
                        return true;
                    }

                    // 3. תרחיש 2: מחכים שהתת-קטגוריות מתוך ה-sublist יופיעו ב-DOM ובלוחצים עליה
                    List<WebElement> subCategories = wait.until(
                            ExpectedConditions.visibilityOfAllElements(subCategoryElement)
                    );

                    for (WebElement subCategory : subCategories) {
                        if (subCategory.getText().trim().equalsIgnoreCase(subCategoryName)) {
                            return click(subCategory);
                        }
                    }

                    log.warn("Sub-category '{}' was not found under '{}'", subCategoryName, mainCategoryName);
                    return false;

                } catch (StaleElementReferenceException e) {
                    attempts++;
                    log.warn("StaleElement encountered while clicking categories. Attempt {}/2...", attempts);
                } catch (Exception e) {
                    log.error("Failed to click category: {} -> {}. Exception: {}",
                            mainCategoryName, subCategoryName, e.getMessage());
                    return false;
                }
            }
            return false;
        }
    }

