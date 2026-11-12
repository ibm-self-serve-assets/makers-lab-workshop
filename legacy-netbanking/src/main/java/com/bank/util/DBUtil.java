package com.bank.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Legacy JDBC utility for SQLite connections.
 * Business logic and SQL inline in Actions - this is just connection management.
 */
public class DBUtil {

    private static String getDbPath() {
        String custom = System.getProperty("netbanking.db.path");
        if (custom != null) {
            return custom;
        }
        String base = System.getProperty("user.dir");
        File dataDir = new File(base, "data");
        dataDir.mkdirs();
        return new File(dataDir, "netbanking.db").getAbsolutePath();
    }

    private static final String DB_DRIVER = "org.sqlite.JDBC";

    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQLite JDBC driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + getDbPath();
        return DriverManager.getConnection(url);
    }

    public static void closeQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // ignore
            }
        }
    }
}
