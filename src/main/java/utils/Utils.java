package utils;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utils {


    public static String readProperty(String key) {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/configuration.properties");
            prop.load(fis);
        } catch (IOException e) {
            System.err.println("שגיאה: לא ניתן למצוא את קובץ ה-Properties בנתיב המוגדר!");
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public static boolean isPageHeaderCorrect(WebElement element, String pageHeader) {
        return element.getText().equalsIgnoreCase(Utils.readProperty(pageHeader));
    }

    public static String formatDateAsDDMMYYYY(LocalDate departureDate) {
        if (departureDate == null)
            return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return departureDate.format(formatter);
    }

    public static String extractNumberFromText(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String fullText = wait.until(ExpectedConditions.visibilityOf(element)).getText();

        return fullText.replaceAll("\\D+", "");
    }

    public static boolean addProductToCart(WebDriver driver, List<WebElement> titles, List<WebElement> buttons, String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(titles.get(titles.size() - 1)));
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).getText().equalsIgnoreCase(productName)) {
                buttons.get(i).click();
                return true;
            }
        }
        return false;
    }

    public static boolean isMessageDisplayedCorrectly(WebDriver driver, WebElement element, String propertyKey) {
        try {
            System.out.println("aaaa:" + element.getText());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("xxxxx:" + element.getText());
            return element.getText().equalsIgnoreCase(readProperty(propertyKey));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public List<Double> getPricesFromUI(List<WebElement> priceElements) {

        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String priceText = element.getText()
                    .replaceAll("[^0-9.]", "");
            prices.add(Double.parseDouble(priceText));
        }

        return prices;
    }

    public List<Double> verifyPricesSortedHighToLow(List<Double> actualPrices) {

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Comparator.reverseOrder());
        return expectedPrices;
    }

    public List<Double> verifyPricesSortedLowToHigh(List<Double> actualPrices) {

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Comparator.naturalOrder());
        return expectedPrices;
    }

    public List<String> getProducesNamesFromUI(List<WebElement> productNameElements) {

        List<String> productNames = new ArrayList<>();
        for (WebElement element : productNameElements) {
            String productName = element.getText();
            productNames.add(productName);
        }
        return productNames;
    }

    public List<String> sortItemNamesHighToLow(List<String> actualNames) {
        actualNames.sort(Collections.reverseOrder());

        return actualNames;
    }

    public List<String> sortItemNamesRegularOrder(List<String> actualNames) {
        Collections.sort(actualNames);
        return actualNames;
    }

    public boolean areItemsDisplayedAInCorrectOrder(List<WebElement> elements) {
        int size = elements.size();
        return size > 0;
    }
}

