package ex3;

import java.sql.*;

import org.apache.commons.dbutils.DbUtils;

import ex3.dao.PersonDao;
import ex3.dao.SetupDao;

public class Main {

    public static void main(String[] args) throws Exception {
    	executeUpdate("DROP SCHEMA public CASCADE");
        executeUpdate("CREATE TABLE person (name varchar(100))");
        executeUpdate("Insert into person values ('Jack')");
        executeUpdate("Insert into person values ('Jill')");
        new PersonDao().printPersons();
    }

    private static void executeUpdate(String query) {
		Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection(
                    "jdbc:hsqldb:file:${user.home}/data/jdbc/db;shutdown=true");

            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }
	}

}
