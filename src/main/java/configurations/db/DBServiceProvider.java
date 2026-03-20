package configurations.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBServiceProvider {

    public static Connection createSqliteConnection() {
        try {
            return DriverManager.getConnection(DBConfig.SQLITE_URL);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to SQLite DB", e);
        }
    }
}