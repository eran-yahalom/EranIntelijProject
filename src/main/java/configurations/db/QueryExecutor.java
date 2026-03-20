package configurations.db;

import java.sql.*;
import java.util.*;

public class QueryExecutor {

    public static List<Map<String, Object>> executeQueryAsTable(String queryKey, Object... params) {

        String query = QueryProvider.getQuery(queryKey);

        try {
            Connection conn = DBThreadLocal.get();
            PreparedStatement stmt = conn.prepareStatement(query);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            ResultSet rs = stmt.executeQuery();

            List<Map<String, Object>> results = new ArrayList<>();
            ResultSetMetaData meta = rs.getMetaData();
            int columns = meta.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columns; i++) {
                    row.put(meta.getColumnName(i), rs.getObject(i));
                }

                results.add(row);
            }

            return results;

        } catch (SQLException e) {
            throw new RuntimeException("Query execution failed: " + queryKey, e);
        }
    }
}