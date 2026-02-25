package utils;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

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

    public static boolean addProductToCart(WebDriver driver,List<WebElement> titles, List<WebElement> buttons, String productName) {
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
            System.out.println("aaaa:"+element.getText());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            System.out.println("xxxxx:"+element.getText());
            return element.getText().equalsIgnoreCase(readProperty(propertyKey));
        } catch (TimeoutException e) {
            return false;
        }
    }
}

