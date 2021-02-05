package com.shop.db;

import com.shop.entity.Cart;

import java.sql.*;

public class CartDAO {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_ITEM = "SELECT * FROM carts where user_id = (?)";
    private static final String SQL_FIND_ITEM_WITH_CHECKOUT_STEP = "SELECT * FROM carts where checkout_step = (?)";
    private static final String SQL_INSERT_ITEM = "INSERT INTO carts (created_at, update_at, user_id, item_total_price, checkout_step) VALUES (NOW(), NOW(), ?, ?, ?)";


    public Cart getCartParam(PreparedStatement prstatement){
         Cart cart = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                cart = new Cart();
                cart.setId(result.getInt("id"));
                cart.setUser_id(result.getInt("user_id"));
                cart.setCoupon(result.getInt("coupon"));
                cart.setDelivery_id(result.getInt("delivery_id"));
                cart.setOrder_total_price(result.getInt("order_total_price"));
                cart.setItem_total_price(result.getInt("order_total_price"));
                cart.setCreated_at(result.getDate("created_at"));
            } else {
                System.out.println("Cart is not valid");
                return null;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("error find user" + e);
        }
        return cart;
    }



    public Cart checkCartByStep(String checkout_step) throws ClassNotFoundException {

        Cart cart = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ITEM_WITH_CHECKOUT_STEP)) {
            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setString(1, checkout_step);
            cart = getCartParam(prstatement);
            if (cart == null){
                con.rollback();
            }
        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }
        return cart;
    }



    public Cart insertCart(int user_id, int item_total_price, String checkout_step) throws ClassNotFoundException {
            Cart cart = null;
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_ITEM, Statement.RETURN_GENERATED_KEYS)) {
                    prstatement.setInt(1, user_id);
                    prstatement.setInt(2, item_total_price);
                    prstatement.setString(3, checkout_step);
                    if (prstatement.executeUpdate() > 0) {
                        ResultSet result = prstatement.getGeneratedKeys();
                        cart = new Cart();
                        if (result.next()) {
                            cart.setId(result.getInt(1));
                        }
                    }
                    return cart;
                } catch (SQLException e) {
                    System.out.println(e);
                }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        CartDAO cartdao = new CartDAO();
        Cart cart = new Cart();
        cart = cartdao.insertCart(1, 23, "address");
    }

}