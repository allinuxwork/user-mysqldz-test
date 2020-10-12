package usermysqltest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usermysqltest.model.User;
import usermysqltest.model.UserCollections;
import usermysqltest.operation.DeleteDataMysql;
import usermysqltest.operation.InsertDataMysql;
import usermysqltest.operation.SelectDataMysql;
import usermysqltest.operation.SelectUsersByAge;

public class UserMysqlTest {
    
    @Test
    void UserAllSelect() {
        System.out.println("---------------------------------");
        System.out.println("Вывести всю информацию о пользователях");
        SelectDataMysql selectDataMysql = new SelectDataMysql();
        selectDataMysql.operationSelect();
    }

    @Test
    void operationSelectBetweenAge() {
        System.out.println("---------------------------------");
        System.out.println("Вывести в консоль всех пользвателей от 18 до 20 лет");
        SelectUsersByAge selectUsersByAge = new SelectUsersByAge();
        selectUsersByAge.showUsersByAge(18, 20);
    }

    @Test
    void operationInsert() {
        User user1 = new User();
        System.out.println("Bob 32 года c эл-почтой boo32@gmail.com добален в базу users");
        System.out.println("---------------------------------");
        user1.setName("Bob");
        user1.setAge(32);
        user1.setEmail("bob32@gmail.com");
        InsertDataMysql insertDataMysql = new InsertDataMysql();
        insertDataMysql.operationInsert(user1);
        SelectDataMysql selectDataMysql = new SelectDataMysql();
        UserCollections users = new UserCollections();
        users = selectDataMysql.operationSelect();
        for (User user : users.getUsers()) {
            if (user.getName() == "Bob") {
                Assertions.assertEquals("bob32@gmail.com", user.getEmail());
            }
        }
    }

    @Test
    void operationDelete() {
        DeleteDataMysql deleteDataMysql = new DeleteDataMysql();
        System.out.println("Bob из базы удален");
        System.out.println("---------------------------------");
        deleteDataMysql.operationDelete("Bob");
        SelectDataMysql selectDataMysql = new SelectDataMysql();
        UserCollections users = new UserCollections();
        users = selectDataMysql.operationSelect();
        Assertions.assertTrue(users.getUsers().size() == 1);
    }
}