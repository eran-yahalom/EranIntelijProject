package services;

import configurations.db.QueryExecutor;
import utils.GeneratorUtils;

import java.util.List;
import java.util.Map;

public class RegistrationService {
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String address;
    private String phoneNumber;
    private String city;
    private String country;
    private String dateOfBirth;
    List<String> creds;

    public List<String> registerRandomUserInDB() {

        try {
            email = GeneratorUtils.generateEmail();
            password = GeneratorUtils.generatePassword();
            first_name = GeneratorUtils.generateFirstName();
            last_name = GeneratorUtils.generateLastName();
            address = GeneratorUtils.address();
            phoneNumber = GeneratorUtils.phoneNumber();
            city = GeneratorUtils.city();
            country = GeneratorUtils.country();
            dateOfBirth = GeneratorUtils.dateOfBirth().toString();

            QueryExecutor.executeUpdate(
                    "set_new_customer",
                    first_name, last_name, email, phoneNumber, address, city, country);

            String id = QueryExecutor.executeQueryAsTable(
                    "get_customer_id_by_email",
                    email).getFirst().get("id").toString();


            QueryExecutor.executeUpdate(
                    "set_new_customer_auth",
                    password, 1, 0, 3, id);

            return List.of(email, password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register user in DB", e);
        }
    }

    public List<String> getRandomUserLoginCredentials() {
        try {
            Map<String, Object> credentials = QueryExecutor.executeQueryAsTable("get_customer_user_and_password").getFirst();
            ;
            String email = credentials.get("email").toString();
            String password = credentials.get("password_hash").toString();


            if (credentials.isEmpty()) {
                throw new RuntimeException("No credentials found for email: " + email);
            }

            return List.of(email, password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user credentials from DB", e);
        }
    }
}
