package usermysqltest.operation;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import usermysqltest.model.User;
import usermysqltest.model.UserCollections;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectUsersByAge {

    UserCollections users = new UserCollections();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;



    public void showUsersByAge(int min, int max) {
        try {
            DataSource dataSource = createDataSource();
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE age BETWEEN ? AND ?");
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);
            resultSet = preparedStatement.executeQuery();
            users.setUsers(new ArrayList<User>());
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setEmail(resultSet.getString("email"));
                users.getUsers().add(user);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        for(User user: users.getUsers()){
            System.out.println(user);
        };
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
