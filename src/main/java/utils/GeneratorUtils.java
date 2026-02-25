package utils;

import net.datafaker.Faker;
import net.datafaker.providers.base.Gender;

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
}
