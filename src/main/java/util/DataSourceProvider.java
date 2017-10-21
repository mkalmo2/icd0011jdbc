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

    private static String dbUrl = null;

    private static BasicDataSource dataSource = null;

    public static void setDbUrl(String url) {
        dbUrl = url;
    }

    public static DataSource getDataSource() {
        if (dataSource != null) {
            return dataSource;
        }

        if (dbUrl == null) {
            throw new IllegalStateException(
                    "Database url not configured. Use setDbUrl()");
        }

        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(JDBCDriver.class.getCanonicalName());
        dataSource.setUrl(dbUrl);

        return dataSource;
    }

}
