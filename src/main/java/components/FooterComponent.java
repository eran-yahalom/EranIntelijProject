package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;
import java.util.function.Supplier;

public class FooterComponent extends BaseComponent {

    @FindBy(css = ".facebook a")
    private WebElement facebookLink;

    @FindBy(css = ".twitter a")
    private WebElement twitterLink;

    @FindBy(css = ".rss a")
    private WebElement rssLink;

    @FindBy(css = ".youtube a")
    private WebElement youtubeLink;

    @FindBy(css = ".google-plus a")
    private WebElement googlePlusLink;

    public FooterComponent(WebDriver driver) {
        super(driver);
    }

    public boolean clickAndMoveToFacebookPage() {
        return clickAndMoveToSelectedSocialMedia(facebookLink);
    }

    public boolean clickAndMoveToTwitterPage() {
        return clickAndMoveToSelectedSocialMedia(twitterLink);
    }

    public boolean clickAndMoveToRssPage() {
        return clickAndMoveToSelectedSocialMedia(rssLink);
    }

    public boolean clickAndMoveToYoutubePage() {
        return clickAndMoveToSelectedSocialMedia(youtubeLink);
    }

    public boolean clickAndMoveToGooglePlusPage() {
        return clickAndMoveToSelectedSocialMedia(googlePlusLink);
    }

    public boolean clickAndMoveToSelectedSocialMedia(String socialMedia) {

        Map<String, Supplier<Boolean>> socialMediaMap = Map.of(
                "facebook", this::clickAndMoveToFacebookPage,
                "youtube", this::clickAndMoveToYoutubePage,
                "google", this::clickAndMoveToGooglePlusPage,
                "twitter", this::clickAndMoveToTwitterPage,
                "rss", this::clickAndMoveToRssPage
        );

        return socialMediaMap.getOrDefault(
                socialMedia.toLowerCase(),
                () -> {
                    throw new IllegalArgumentException("Invalid social media: " + socialMedia);
                }
        ).get();
    }

    public boolean isCorrectSocialMediaPageOpened(String socialMedia) {
        try {
            String currentUrl = driver.getCurrentUrl().toLowerCase();
            if (socialMedia.equalsIgnoreCase("twitter")) {
                socialMedia = "x";
            }
            return currentUrl.contains(socialMedia.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
}
