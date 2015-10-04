package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String URL = "jdbc:hsqldb:file:${user.home}/data/jdbc/db;shutdown=true";

    public static void main(String[] args) throws Exception {
        String contents = FileUtil.readFileFromClasspath("schema.sql");

        for (String statement : contents.split(";")) {
            if (statement.matches("\\s*")) {
                continue;
            }

            executeUpdate(statement);
        }

        System.out.println(getPersons());

        deletePerson(0);

        System.out.println(getPersons());
    }

    private static void deletePerson(Integer id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM PERSON WHERE ID = ?")) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    private static List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            try (ResultSet r = stmt.executeQuery("SELECT * FROM PERSON")) {
                while (r.next()) {
                    persons.add(new Person(r.getInt(1), r.getString(2)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return persons;
    }

    private static void executeUpdate(String sql) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            System.out.println("executing: " + sql);

            stmt.executeUpdate(sql);
        }
    }

}
