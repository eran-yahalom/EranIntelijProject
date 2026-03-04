package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.function.Predicate;
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

    @FindBy(css = ".account")
    private WebElement myAccountLink;

    @FindBy(css = ".column.my-account ul li a")
    private List<WebElement> myAccountFooterLinks;


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

    public boolean clickOnMyAccountAccountLink() {
        return click(myAccountFooterLinks.get(0));
    }

    public boolean clickOnMyAccountOrdersLink() {
        return click(myAccountFooterLinks.get(1));
    }

    public boolean clickOnMyAccountAddressesLink() {
        return click(myAccountFooterLinks.get(2));
    }

    public boolean clickOnMyAccountShoppingCartLink() {
        return click(myAccountFooterLinks.get(3));
    }

    public boolean clickOnMyAccountWishlistLink() {
        return click(myAccountFooterLinks.get(4));
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

    public boolean clickOnMyAccountLinks(String myAccountLink) {

        Map<String, Supplier<Boolean>> socialMediaMap = Map.of(
                "my account", this::clickOnMyAccountAccountLink,
                "orders", this::clickOnMyAccountOrdersLink,
                "addresses", this::clickOnMyAccountAddressesLink,
                "shopping cart", this::clickOnMyAccountShoppingCartLink,
                "wishlist", this::clickOnMyAccountWishlistLink
        );

        return socialMediaMap.getOrDefault(
                myAccountLink.toLowerCase(),
                () -> {
                    throw new IllegalArgumentException("Invalid my account page: " + myAccountLink);
                }
        ).get();
    }

    public boolean isCorrectMyAccountPageOpenedForAnonymousUser(String pageName) {
        String actualPage = getPageHeader();

        Map<String, Predicate<String>> pageValidationMap = Map.of(
                "my account", actualPageValue -> actualPageValue.equalsIgnoreCase("Welcome, Please Sign In!"),
                "orders", actualPageValue -> actualPageValue.equalsIgnoreCase("Welcome, Please Sign In!"),
                "addresses", actualPageValue -> actualPageValue.equalsIgnoreCase("Welcome, Please Sign In!"),
                "shopping cart", actualPageValue -> actualPageValue.equalsIgnoreCase("Shopping cart"),
                "wishlist", actualPageValue -> actualPageValue.equalsIgnoreCase("Wishlist")
        );

        return pageValidationMap
                .getOrDefault(pageName.toLowerCase(), p -> false)
                .test(actualPage);
    }

    public boolean isCorrectMyAccountPageOpenedForLoggedInUser(String pageName) {
        String actualPage = getPageHeader();

        Map<String, Predicate<String>> pageValidationMap = Map.of(
                "my account", actualPageValue -> actualPageValue.equalsIgnoreCase("My account - Customer info"),
                "orders", actualPageValue -> actualPageValue.equalsIgnoreCase("My account - Orders"),
                "addresses", actualPageValue -> actualPageValue.equalsIgnoreCase("My account - Addresses"),
                "shopping cart", actualPageValue -> actualPageValue.equalsIgnoreCase("Shopping cart"),
                "wishlist", actualPageValue -> actualPageValue.equalsIgnoreCase("Wishlist")
        );

        return pageValidationMap
                .getOrDefault(pageName.toLowerCase(), p -> false)
                .test(actualPage);
    }
}
