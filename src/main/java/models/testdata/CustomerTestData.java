package models.testdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerTestData {

    private String firstName;
    private String lastName;
    private String email;

    // Billing Details
    private String billingCountry;
    private String billingCity;
    private String billingAddress;
    private String billingZip;
    private String phone;

    // Shipping Details
    private String shippingCountry;
    private String shippingCity;
    private String shippingAddress;
    private String shippingZip;

    // Payment Details
    private String creditCardType;
    private String creditCardName;
    private String creditCardNumber;

    @JsonProperty("creditCardTypeCVV")
    private String creditCardTypeCVV;

    private String creditCardExpirationYear;

    // התאמה למפתח PONumberer שמופיע ב-customer_data.json
    @JsonProperty("PONumberer")
    private String poNumber;
}