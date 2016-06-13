
package vicody.pool.db;

import java.sql.*;

/**
 * Created by wzm on 2016/6/8.
 */
public class DataSourceClient {

    private static String sql = "select 1";

    public static Connection getClient(DataSource dataSource) throws Exception {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s",dataSource.getHost(),
                    dataSource.getPort(),dataSource.getDbName()), dataSource.getUser(), dataSource.getPassword());
        } catch (Exception e) {
            throw e;
//            throw new Exception("get datasource client occur error:" + dataSource.getHost());
        }
        return connection;
    }

    public static int getTestResult(DataSource dataSource) throws Exception {
        try (Connection client = getClient(dataSource);
             PreparedStatement preparedStatement = client.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        DataSource dataSource = new DataSource("192.168.83.128","3306","vicody","root","root");
//        String sql = "select * from ip1 limit 10";
        try (Connection client = getClient(dataSource);
             PreparedStatement preparedStatement = client.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
