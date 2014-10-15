package ex3;

import java.sql.*;

import org.apache.commons.dbutils.DbUtils;

public class Main {

    public static void main(String[] args) throws Exception {

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection(
                    "jdbc:hsqldb:file:${user.home}/data/jdbc/db;shutdown=true");

            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE person (name varchar(100))");
        } finally {
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }

    }

}
