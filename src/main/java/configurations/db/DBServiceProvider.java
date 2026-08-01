package configurations.db;

import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
public class DBServiceProvider {

    public static Connection createSqliteConnection(String schemaURL, String dbName, String username, String password) {
        String finalUrl = schemaURL;

        if (schemaURL.contains("/Users/eranyahalom/")) {
            String projectPath = System.getProperty("user.dir");
            String relativePath = "/src/test/resources/db/Chinook.db";
            finalUrl = "jdbc:sqlite:" + projectPath + relativePath;

            log.info("Detected hardcoded Mac path. Redirecting to: " + finalUrl);
        }

        try {
            return DriverManager.getConnection(finalUrl);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to SQLite DB at: " + finalUrl, e);
        }
    }
}