package util;

import org.apache.commons.dbcp.BasicDataSource;
import org.hsqldb.jdbc.JDBCDriver;

import javax.sql.DataSource;

public class DataSourceProvider {

    static {
        try {
            Class.forName(JDBCDriver.class.getCanonicalName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String DB_URL = "jdbc:hsqldb:mem:db1";

    private static BasicDataSource dataSource = null;

    public static void setDbUrl(String dbUrl) {
        DB_URL = dbUrl;
    }

    public static DataSource getDataSource() {
        if (dataSource != null) {
            return dataSource;
        }

        dataSource = new BasicDataSource();
        dataSource.setMaxActive(3);
        dataSource.setDriverClassName(JDBCDriver.class.getCanonicalName());
        dataSource.setUrl(DB_URL);

        return dataSource;
    }

}
