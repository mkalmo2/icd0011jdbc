package jdbc;

import util.DataSourceProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BasicDataSourceExample {

    public static void main(String[] args) throws SQLException {

        DataSourceProvider.setDbUrl("jdbc:hsqldb:mem:my-database");
        DataSource dataSource = DataSourceProvider.getDataSource(); // max 3 connections
        System.out.println("DataSource class: " + dataSource.getClass());

        Connection c1 = dataSource.getConnection();
        System.out.println("Connection instance identity: " + c1.hashCode());
        c1.close();
        Connection c2 = dataSource.getConnection();
        Connection c3 = dataSource.getConnection();

        System.out.println("Connection class: " + c1.getClass());
        System.out.println("Connection instance identity: " + c2.hashCode());
        System.out.println("Connection instance identity: " + c3.hashCode());
    }

}
