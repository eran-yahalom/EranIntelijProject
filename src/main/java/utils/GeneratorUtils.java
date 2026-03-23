package utils;

import net.datafaker.Faker;
import net.datafaker.providers.base.Gender;

import java.util.UUID;

import java.sql.Timestamp;

public class GeneratorUtils {
    private static final ThreadLocal<Faker> faker =
            ThreadLocal.withInitial(Faker::new);

    public static String generateEmail() {
        return faker.get().internet().emailAddress();
    }

    public static String generatePassword() {
        return faker.get().internet().password(8, 16);
    }

    public static String generateFirstName() {
        return faker.get().name().firstName();
    }

    public static String generateLastName() {
        return faker.get().name().lastName();
    }

    public static Gender generateGender() {
        return faker.get().gender();
    }

    public static String address() {
        return faker.get().address().fullAddress();
    }

    public static String phoneNumber() {
        return faker.get().phoneNumber().phoneNumber();
    }

    public static String company() {
        return faker.get().company().name();
    }

    public static String city() {
        return faker.get().address().city();
    }

    public static String country() {
        return faker.get().address().country();
    }

    public static Timestamp dateOfBirth() {
        return faker.get().date().birthday();
    }

    public static String generateUserName() {
        return faker.get().name() + UUID.randomUUID().toString().substring(0, 5);
    }
}
