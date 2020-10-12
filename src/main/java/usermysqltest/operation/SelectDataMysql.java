package usermysqltest.operation;

import com.mysql.cj.jdbc.MysqlDataSource;
import usermysqltest.model.User;
import usermysqltest.model.UserCollections;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectDataMysql {

    UserCollections users = new UserCollections();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public UserCollections operationSelect() {
        try {
            DataSource dataSource = createDataSource();
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
                user.setEmail(resultSet.getString("email"));
                System.out.println(user.toString());
                users.setUsers(new ArrayList<User>());
                users.getUsers().add(user);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
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
        return users;
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
