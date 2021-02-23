package com.shop.db;

import com.shop.entity.User;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOTest extends TestCase {

    private static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users where email = (?)";


    @Test
    public void testGetUserParam() throws ClassNotFoundException {

        User user = null;
        UserDAO userDAO = new UserDAO();
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {

            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }

            prstatement.setString(1, "test10@gmail.com");

            user = userDAO.getUserParam(prstatement);
            Assert.assertEquals(17, user.getId());

        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }


    }

    @Test
    public void testCheckLogin() throws ClassNotFoundException {

        User user = null;
        UserDAO userDAO = new UserDAO();
        user = userDAO.checkLogin("test10@gmail.com");
        Assert.assertEquals(17, user.getId());
    }

    @Test
    public void testCheckLoginandPassword() throws ClassNotFoundException {

        User user = null;
        UserDAO userDAO = new UserDAO();
        user = userDAO.checkLoginandPassword("test10@gmail.com", "123456");
        Assert.assertEquals(17, user.getId());

    }

    @Test
    public void testCheckUserbyId() throws ClassNotFoundException {
        User user = null;
        UserDAO userDAO = new UserDAO();
        user = userDAO.checkUserbyId(17);
        Assert.assertEquals("test10@gmail.com", user.getEmail());
    }

    @Test
    public void testInputUser() throws ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        Boolean flag;
        flag = userDAO.inputUser("Yevhen_Tereshchenko", "test43@gmail", "123456", "123456", "user");
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdatecurrentimageUser() throws ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        Boolean flag;
        flag = userDAO.updatecurrentimageUser("29.jpg", 24);
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdatecurrentsignUser() throws ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        Boolean flag;
        flag = userDAO.updatecurrentsignUser(38);
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdateUser() throws ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        Boolean flag;
        flag = userDAO.inputUser("Yevhen_Tereshchenko", "test46@gmail", "123456", "123456", "user");
        Assert.assertTrue(flag);
    }

    @Test
    public void testDeleteUser() throws ClassNotFoundException {
        UserDAO userDAO = new UserDAO();
        Boolean flag;
        flag = userDAO.deleteUser(38);
        Assert.assertTrue(flag);

    }

    @Test
    public void testFindAllUsers() throws ClassNotFoundException {

        List<User> users = new ArrayList<>();
        UserDAO userDAO = new UserDAO();

        users = userDAO.findAllUsers();
        Assert.assertEquals(32, users.size());
    }

}