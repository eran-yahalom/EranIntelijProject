package components;

import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class PopularTagsComponent extends BaseComponent {

    @FindBy(css = "[class='tags'] ul li a")
    private List<WebElement> popularTagsList;

    @FindBy(css = "[class^='view-all'] a")
    private WebElement viewAllTagsLink;

    @FindBy(css = "[class='block block-popular-tags']>div[class='title']")
    private WebElement popularTagsTitle;

    @FindBy(css = "[class='block block-popular-tags']")
    private WebElement popularTagsBlock;

    @Inject
    public PopularTagsComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean clickOnSelectedTag(String tagName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(popularTagsList));

            for (WebElement element : popularTagsList) {
                if (element.getText().trim().equals(tagName)) {
                    return click(element);
                }
            }
            log.warn("Tag '{}' was not found in popularTagsList", tagName);

        } catch (Exception e) {
            log.error("Failed to click on tag: {}. Exception: {}", tagName, e.getMessage());
        }

        return false;
    }

    public boolean clickOnViewAllTagsLink() {
        try {
            wait.until(ExpectedConditions.visibilityOf(viewAllTagsLink));
            return click(viewAllTagsLink);
        } catch (Exception e) {
            log.error("Failed to click on View All Tags link. Exception:{}", e.getMessage());
            return false;
        }
    }

//    public boolean clickOnSelectedNameTag(String tagName) {
//        int attempts = 0;
//        while (attempts < 2) {
//            try {
//                wait.until(ExpectedConditions.visibilityOfAllElements(popularTagsList));
//
//                for (WebElement element : popularTagsList) {
//                    if (element.getText().trim().equals(tagName)) {
//                        return click(element);
//                    }
//                }
//
//                log.warn("Tag '{}' was not found in popularTagsList", tagName);
//                return false;
//
//            } catch (StaleElementReferenceException e) {
//                attempts++;
//                log.warn("StaleElement encountered while scanning tags. Retrying attempt {}/2...", attempts);
//            } catch (Exception e) {
//                log.error("Failed to click on tag: {}. Exception: {}", tagName, e.getMessage());
//                return false;
//            }
//        }
//        return false;
//    }

    public boolean clickOnTagName(String tagName) {
        return clickOnSelectedNameTag(popularTagsList, tagName);
    }


    public boolean isPopularTagsBlockDisplayed() {
        try {
            shortWait.until(ExpectedConditions.visibilityOf(popularTagsBlock));
            return true;
        } catch (Exception e) {
            log.error("Popular Tags block is not displayed. Exception: {}", e.getMessage());
            return false;
        }
    }

    public int getPopularTagsCount() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(popularTagsList));
            return popularTagsList.size();
        } catch (Exception e) {
            log.error("Failed to get popular tags count. Exception: {}", e.getMessage());
            return 0;
        }
    }
}