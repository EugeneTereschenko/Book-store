package com.shop.db;

import com.shop.entity.Order;

import java.sql.*;

public class OrderDAO {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_ORDER = "SELECT * FROM orders where id = (?)";
    private static final String SQL_INSERT_ORDER = "INSERT INTO orders (created_at, update_at, item_total_price, order_total_price, user_id, state, delivery_id) VALUES (NOW(), NOW(), ?, ?, ?, ?, ?)";
    //private static final String SQL_UPDATE_ORDER = "UPDATE carts set checkout_step = (?) where id = (?)";


    public Order getOrderParam(PreparedStatement prstatement){
        Order order = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                order = new Order();
                order.setId(result.getInt("id"));
                order.setUser_id(result.getInt("user_id"));
                order.setState(result.getString("state"));
                order.setItem_total_price(result.getInt("order_total_price"));
                order.setItem_total_price(result.getInt("item_total_price"));
                order.setDelivery_id(result.getInt("delivery_id"));
            } else {
                System.out.println("Order is not valid");
                return null;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("error find order" + e);
        }
        return order;
    }



    public Order checkOrderById(int id) throws ClassNotFoundException {

        Order order = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ORDER)) {
            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setInt(1, id);
            order = getOrderParam(prstatement);
            if (order == null){
                con.rollback();
            }
        } catch (SQLException e) {
            System.out.println("error find order" + e);
        }
        return order;
    }

    public Order insertOrder(int user_id, int item_total_price, int order_total_price, String state, int delivery_id) throws ClassNotFoundException {
        Order order = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            prstatement.setInt(1, item_total_price);
            prstatement.setInt(2, order_total_price);
            prstatement.setInt(3, user_id);
            prstatement.setString(4, state);
            prstatement.setInt(5,delivery_id);
            if (prstatement.executeUpdate() > 0) {
                ResultSet result = prstatement.getGeneratedKeys();
                order = new Order();
                if (result.next()) {
                    order.setId(result.getInt(1));
                }
            }
            return order;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
