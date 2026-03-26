package configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class EnvManager {
    private static EnvConfig.EnvironmentDetails currentEnv;

    static {
        try {
            // 1. טעינת קובץ ה-JSON
            ObjectMapper mapper = new ObjectMapper();
            EnvConfig config = mapper.readValue(new File("src/test/resources/environments.json"), EnvConfig.class);

            // 2. קביעת הסביבה: עדיפות ל-Terminal (-Denv), אחרת ל-global.properties
            String envName = System.getProperty("env");

            if (envName == null || envName.isEmpty()) {
                Properties globalProps = new Properties();
                try (FileInputStream fis = new FileInputStream("src/test/resources/global.properties")) {
                    globalProps.load(fis);
                    envName = globalProps.getProperty("default.env", "qa1");
                } catch (IOException e) {
                    envName = "qa1";
                }
            }

            envName = envName.toLowerCase();
            currentEnv = config.getEnvironments().get(envName);

            if (currentEnv == null) {
                throw new RuntimeException("Environment '" + envName + "' not found in JSON!");
            }

            log.info(">>>> ACTIVE ENVIRONMENT: {} <<<<", envName.toUpperCase());

        } catch (IOException e) {
            log.error("Critical failure loading configuration", e);
            throw new RuntimeException(e);
        }
    }

    public static EnvConfig.EnvironmentDetails get() {
        return currentEnv;
    }
}