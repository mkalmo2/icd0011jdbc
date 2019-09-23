package jdbc;

import util.ConnectionInfo;
import util.DataSourceProvider;
import util.DbUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BasicDataSourceExample {

    public static void main(String[] args) throws SQLException {

        ConnectionInfo connectionInfo = DbUtil.loadConnectionInfo();

        DataSourceProvider.setConnectionInfo(connectionInfo);
        DataSource dataSource = DataSourceProvider.getDataSource(); // max 2 connections

        Connection c1 = dataSource.getConnection();

        System.out.println("Connection class: " + c1.getClass());
        System.out.println("Connection instance identity: " + c1.hashCode());

        Connection c2 = dataSource.getConnection();

        System.out.println("Connection instance identity: " + c2.hashCode());
    }

}
