package components;

import org.openqa.selenium.*;

import java.util.List;
import java.util.stream.Collectors;

public class BreadcrumbComponent {

    private WebDriver driver;
    private By breadcrumbElements = By.cssSelector(".breadcrumb ul li");
    private By breadcrumbContainer = By.cssSelector(".breadcrumb");

    public BreadcrumbComponent(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDisplayedCorrectly(String... expectedBreadcrumbs) {
        try {
            WebElement container = driver.findElement(breadcrumbContainer);
            String[] actual = container.getText().split("/");

            if (actual.length != expectedBreadcrumbs.length) {
                return false;
            }

            for (int i = 0; i < expectedBreadcrumbs.length; i++) {
                if (!actual[i].trim().equalsIgnoreCase(expectedBreadcrumbs[i].trim())) {
                    return false;
                }
            }

            return true;

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public List<String> getBreadcrumbSteps() {
        return driver.findElements(breadcrumbElements)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getLastBreadcrumbStep() {
        List<WebElement> breadCrumbs = driver.findElements(breadcrumbElements);
        if (breadCrumbs.isEmpty()) {
            return null;
        }
        return breadCrumbs.get(breadCrumbs.size() - 1).getText();
    }

    public boolean selectAndClickOnSpecificBreadCrumb(String breadCrumbName) {
        try {
            List<WebElement> breadCrumbs = driver.findElements(breadcrumbElements);
            for (WebElement breadCrumb : breadCrumbs) {
                String breadCrumbText = breadCrumb.getText().replace("/", "").trim();
                if (breadCrumbText.equalsIgnoreCase(breadCrumbName)) {
                    breadCrumb.click();
                    return true;
                }
            }
        } catch (NoSuchElementException ignored) {

        }
        return false;
    }

    public int countNumberOfBreadcrumbs() {
        try {
            return driver.findElements(breadcrumbElements).size();
        } catch (NoSuchElementException e) {
            return 0;
        }
    }
}
