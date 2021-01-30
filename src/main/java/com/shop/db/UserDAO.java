package com.shop.db;
import com.shop.entity.User;

import java.sql.*;

public class UserDAO{

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=root";
    static final String SQL_FIND_USER = "SELECT * FROM users where email = (?) and encrypted_password = (?)";


    public User getUserParam(PreparedStatement prstatement){
        User user = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setEmail(result.getString("email"));
                user.setEncrypted_password(result.getString("encrypted_password"));
            }
        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }
        return user;

    }


    public User checkLogin(String email, String encrypted_password) throws ClassNotFoundException {

        User user = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_USER)) {
            prstatement.setString(1, email);
            prstatement.setString(2, encrypted_password);

            user = getUserParam(prstatement);

        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return user;

    }

}
