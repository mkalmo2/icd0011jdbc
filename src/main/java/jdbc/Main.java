package jdbc;

import util.DbUtil;
import util.FileUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static String URL = "jdbc:hsqldb:file:${user.home}/data/jdbc/db;shutdown=true";

    public static void main(String[] args) throws Exception {

//        createTable();
//        insertPersons();

        String contents = FileUtil.readFileFromClasspath("schema.sql");

        try (Connection conn = DriverManager.getConnection(URL)) {
            DbUtil.insertFromFile(conn, contents);

            System.out.println(conn.getClass());

        }

        List<Person> persons = getPersons();
        System.out.println(persons);



    }

    private static List<Person> getPersons() throws SQLException {
        List<Person> persons = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            try (ResultSet resultSet = stmt.executeQuery("select * from person")) {
                while (resultSet.next()) {
                    Person person = new Person(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getInt(3));


                    persons.add(person);
                }
            }
        }

        return persons;
    }

    private static void executeUpdate(String sql) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
        }
    }

    private static void insertPersons() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(
                    "insert into person (id, name) values (1, 'Jill')");
            stmt.executeUpdate(
                    "insert into person (id, name) values (2, 'Jack')");
        }
    }

    private static void createTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE PERSON (\n" +
                    "      id BIGINT NOT NULL PRIMARY KEY,\n" +
                    "      name VARCHAR(255) NOT NULL,\n" +
                    "      age int\n" +
                    "   );");
        }
    }

}
