package configurations.db;

import java.sql.Connection;
import java.sql.SQLException;

public class DBSetupService {

    public static void init() {
        Connection connection = DBServiceProvider.createSqliteConnection();
        DBThreadLocal.set(connection);
    }

    public static void close() {
        Connection connection = DBThreadLocal.get();

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        DBThreadLocal.remove();
    }
}