package com.shop.db;
import com.shop.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO{

    private static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_USER = "SELECT * FROM users where email = (?) and encrypted_password = (?)";
    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users where email = (?)";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users where id = (?)";
    private static final String SQL_INSERT_USER = "INSERT INTO users (email, name, remember_created_at, current_sign_in_at, encrypted_password) VALUES (?, ?, NOW(), NOW(), ?)";

    //private static final String SQL_INSERT_USER = "INSERT INTO users (email, name, encrypted_password) VALUES (?, ?, ?)";


    public static User getUserParam(PreparedStatement prstatement){
        User user = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setEmail(result.getString("email"));
                user.setCurrent_sign_in_at(result.getDate("current_sign_in_at"));
                user.setRemember_created_at(result.getDate("remember_created_at"));
                user.setEncrypted_password(result.getString("encrypted_password"));
            } else {
                System.out.println("You are not valid");

                return null;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("error find user" + e);
        }
        return user;

    }



    public static User checkLogin(String email) throws ClassNotFoundException {

        User user = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {

            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }

            prstatement.setString(1, email);

            user = getUserParam(prstatement);

            if (user == null){
                System.out.println("user is null");
                con.rollback();
                return null;
            }

        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return user;

    }

    public static User checkLoginandPassword(String email, String encrypted_password) throws ClassNotFoundException {

        User userCheckLogin=checkLogin(email);

        if (userCheckLogin == null) {
            return null;
        }

        if (userCheckLogin != null) {



            User user = null;
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_USER)) {

                if (con.getAutoCommit()) {
                    con.setAutoCommit(false);
                }

                prstatement.setString(1, email);
                prstatement.setString(2, encrypted_password);

                user = getUserParam(prstatement);

                if (user == null) {
                    con.rollback();
                    return null;
                }

            } catch (SQLException e) {
                System.out.println("error find user" + e);
            }

            return user;
        }
        return null;
    }

    public static User checkUserbyId(int id) throws ClassNotFoundException {
        User user = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_USER_BY_ID)) {
            prstatement.setInt(1, id);
            user = getUserParam(prstatement);
        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }
        return user;

    }


    public static User inputUser(String username, String email, String password, String password_confirm) throws ClassNotFoundException {
        User login = null;
        login = checkLogin(email);

        User user = null;
        user = checkLoginandPassword(email, password);

        if (user == null && login == null) {

            if (password.equals(password_confirm)) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
                    prstatement.setString(1, email);
                    prstatement.setString(2, username);
                    prstatement.setString(3, password);
                    if (prstatement.executeUpdate() > 0) {
                        ResultSet result = prstatement.getGeneratedKeys();
                        user = new User();
                        if (result.next()) {
                            user.setId(result.getInt(1));
                        }
                    }
                    return user;
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }

        return null;
    }

    public List<User> findAllUsers() throws ClassNotFoundException {

        List<User> users = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); Statement statement = con.createStatement(); ResultSet result = statement.executeQuery(SQL_FIND_ALL_USERS)) {
            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setEmail(result.getString("email"));
                user.setName(result.getString("name"));
                user.setRemember_created_at(result.getDate("remember_created_at"));
                user.setCurrent_sign_in_at(result.getDate("current_sign_in_at"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        User user3 = new User();
        user3 = checkLoginandPassword("test2@gmail.com", "123456");
        if (user3 == null){
            System.out.println("exit");
        }
//        System.out.println(" User connect " + user3.getEmail());


        User user4 = new User();
        user4 = inputUser("Petrov", "test2@gmail.com", "123456", "123456");
        if (user4 != null){
            System.out.println("input user correct id = " + user4.getId());
        } else {
            System.out.println(" user already connect ");
        }
/*
        User user5 = checkLogin("test@gmail.com");
        if (user5 == null){
            System.out.println("sdsd");
        } else {
            System.out.println("we have such user");
        }
*/
        //setConnect();
    }



}
