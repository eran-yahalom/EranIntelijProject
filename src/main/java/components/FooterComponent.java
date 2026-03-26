package components;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Log4j2
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

    @FindBy(css = ".column.customer-service ul li a")
    private List<WebElement> customerServiceFooterLinks;

    @FindBy(css = ".column.information ul li a")
    private List<WebElement> informationLinks;

    @Inject
    public FooterComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

    public boolean clickOnCustomerServiceSearchLink() {
        return click(customerServiceFooterLinks.get(0));
    }

    public boolean clickOnCustomerServiceNewsLink() {
        return click(customerServiceFooterLinks.get(1));
    }

    public boolean clickOnCustomerServiceBlogLink() {
        return click(customerServiceFooterLinks.get(2));
    }

    public boolean clickOnCustomerServiceRecentlyViewedProductsLink() {
        return click(customerServiceFooterLinks.get(3));
    }

    public boolean clickOnCustomerServiceCompareProductsListLink() {
        return click(customerServiceFooterLinks.get(4));
    }

    public boolean clickOnCustomerServiceNewProductsListLink() {
        return click(customerServiceFooterLinks.get(5));
    }

    public boolean clickOnInformationSiteLink() {
        return click(informationLinks.get(0));
    }

    public boolean clickOnInformationShippingAndReturnsLink() {
        return click(informationLinks.get(1));
    }

    public boolean clickOnInformationSPrivacyNoticeLink() {
        return click(informationLinks.get(2));
    }

    public boolean clickOnInformationConditionsOfUseLink() {
        return click(informationLinks.get(3));
    }

    public boolean clickOnInformationAboutUsLink() {
        return click(informationLinks.get(4));
    }

    public boolean clickOnInformationContactUsLink() {
        return click(informationLinks.get(5));
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
            log.error("Failed to get current URL or validate social media page: {}", e.getMessage());
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

    public boolean clickOnCustomerServiceLinks(String myAccountLink) {

        Map<String, Supplier<Boolean>> socialMediaMap = Map.of(
                "search", this::clickOnCustomerServiceSearchLink,
                "news", this::clickOnCustomerServiceNewsLink,
                "blog", this::clickOnCustomerServiceBlogLink,
                "recently viewed products", this::clickOnCustomerServiceRecentlyViewedProductsLink,
                "compare products list", this::clickOnCustomerServiceCompareProductsListLink,
                "new products", this::clickOnCustomerServiceNewProductsListLink
        );

        return socialMediaMap.getOrDefault(
                myAccountLink.toLowerCase(),
                () -> {
                    throw new IllegalArgumentException("Invalid customer service page: " + myAccountLink);
                }
        ).get();
    }

    public boolean isCorrectCustomerServicePageOpenedF(String pageName) {
        String actualPage = getPageHeader();

        Map<String, Predicate<String>> pageValidationMap = Map.of(
                "search", actualPageValue -> actualPageValue.equalsIgnoreCase("Search"),
                "news", actualPageValue -> actualPageValue.equalsIgnoreCase("News"),
                "blog", actualPageValue -> actualPageValue.equalsIgnoreCase("Blog"),
                "recently viewed products", actualPageValue -> actualPageValue.equalsIgnoreCase("Recently viewed products"),
                "compare products list", actualPageValue -> actualPageValue.equalsIgnoreCase("Compare products"),
                "new products", actualPageValue -> actualPageValue.equalsIgnoreCase("New products")
        );

        return pageValidationMap
                .getOrDefault(pageName.toLowerCase(), p -> false)
                .test(actualPage);
    }

    public boolean clickOnInformationLinks(String informationLink) {

        Map<String, Supplier<Boolean>> socialMediaMap = Map.of(
                "sitemap", this::clickOnInformationSiteLink,
                "shipping & returns", this::clickOnInformationShippingAndReturnsLink,
                "privacy notice", this::clickOnInformationSPrivacyNoticeLink,
                "conditions of use", this::clickOnInformationConditionsOfUseLink,
                "about us", this::clickOnInformationAboutUsLink,
                "contact us", this::clickOnInformationContactUsLink
        );

        return socialMediaMap.getOrDefault(
                informationLink.toLowerCase(),
                () -> {
                    throw new IllegalArgumentException("Invalid information page: " + myAccountLink);
                }
        ).get();
    }

    public boolean isCorrectInformationPageOpenedF(String pageName) {
        String actualPage = getPageHeader();

        Map<String, Predicate<String>> pageValidationMap = Map.of(
                "sitemap", actualPageValue -> actualPageValue.equalsIgnoreCase("Sitemap"),
                "shipping & returns", actualPageValue -> actualPageValue.equalsIgnoreCase("Shipping & Returns"),
                "privacy notice", actualPageValue -> actualPageValue.equalsIgnoreCase("Privacy policy"),
                "conditions of use", actualPageValue -> actualPageValue.equalsIgnoreCase("Conditions of use"),
                "about us", actualPageValue -> actualPageValue.equalsIgnoreCase("About Us"),
                "contact us", actualPageValue -> actualPageValue.equalsIgnoreCase("Contact Us")

        );

        return pageValidationMap
                .getOrDefault(pageName.toLowerCase(), p -> false)
                .test(actualPage);
    }
}
