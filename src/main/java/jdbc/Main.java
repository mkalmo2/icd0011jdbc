package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {

    public static String URL = "jdbc:hsqldb:file:${user.home}/data/jdbc/db;shutdown=true";

    public static void main(String[] args) throws Exception {

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("...");
        }

    }

}
