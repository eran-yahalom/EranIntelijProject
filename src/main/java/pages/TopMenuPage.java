package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

public class TopMenuPage extends BasePage {

    private List<WebElement> subMenuLink;
    @FindBy(css = ".header-menu .top-menu>li>a")
    private List<WebElement> topMenuLinks;

    @FindBy(css = ".header-menu .top-menu li:nth-child(3) ul li a")
    private List<WebElement> electronicsSubMenuLink;

    @FindBy(css = ".header-menu .top-menu li:nth-child(2) ul li a")
    private List<WebElement> computersSubMenuLink;

    @FindBy(css = ".header-logo a")
    private WebElement logoLink;

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    public boolean clickOnTopMenuLink(String linkText) {
        waitForElementToBeClickable(topMenuLinks.get(topMenuLinks.size() - 1));
        for (WebElement link : topMenuLinks) {
            if (getText(link).equalsIgnoreCase(linkText)) {
                return click(link);
            }
        }
        return false;
    }

    public boolean clickOnSubMenuLink(String linkText, String subLinkText) {
        for (WebElement link : topMenuLinks) {
            if (getText(link).equalsIgnoreCase(linkText)) {
                hover(link);

                subMenuLink=getSubMenuByText(linkText);
                wait.until(ExpectedConditions.visibilityOfAllElements(subMenuLink));

                for (WebElement subLink : subMenuLink) {
                    if (getText(subLink).equalsIgnoreCase(subLinkText)) {

                        wait.until(ExpectedConditions.elementToBeClickable(subLink));
                        return click(subLink);
                    }
                }
            }
        }
        return false;
    }

    public boolean clickOnLogoLink() {
        return click(logoLink);
    }

    public List<WebElement> getSubMenuByText(String linkText) {

        Map<String, List<WebElement>> menuMap = Map.of(
                "electronics", electronicsSubMenuLink,
                "computers", computersSubMenuLink
        );

        return Optional.ofNullable(menuMap.get(linkText.toLowerCase()))
                .orElse(Collections.emptyList());
    }

}
