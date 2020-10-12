package usermysqltest.operation;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DeleteDataMysql {

    Connection connection = null;
    Statement statement = null;
//    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;



    public void operationDelete(String name) {
        try {
            DataSource dataSource = createDataSource();
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM users WHERE name = '%s'", name));


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

    }


    public DataSource createDataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("alex");
        dataSource.setPassword("alexpass");
        dataSource.setUrl("jdbc:mysql://localhost:3306/newdb");
        dataSource.setServerTimezone("UTC");
        return dataSource;
    }
}
