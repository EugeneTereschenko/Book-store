package com.shop.db;

import com.shop.entity.Orderitem;

import java.sql.*;

public class OrderitemDAO {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_ORDER_ITEM = "SELECT * FROM order_items where book_id = (?) and delivery_id = (?)";
    private static final String SQL_INSERT_BOOK_ID_ORDER_ITEM_ID = "INSERT INTO order_items (book_id, quantity, delivery_id, created_at, update_at) " +
            "SELECT book_id, quantity, (?), now(), now() FROM items WHERE cart_id = (?);";

    public static Orderitem getOrderitemParam(PreparedStatement prstatement){
        Orderitem orderitem = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                orderitem = new Orderitem();
                orderitem.setBook_id(result.getInt("book_id"));
                orderitem.setOrder_id(result.getInt("order_id"));
                orderitem.setQuantity(result.getInt("quantity"));
            } else {
                System.out.println("orderitem is not valid");

                return null;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("error find orderitem" + e);
        }
        return orderitem;

    }



    public static Orderitem checkOrderitem(int book_id, int order_id) throws ClassNotFoundException {

        Orderitem orderitem = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ORDER_ITEM)) {

            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }

            prstatement.setInt(1, book_id);
            prstatement.setInt(2, order_id);

            orderitem = getOrderitemParam(prstatement);

            if (orderitem == null){
                System.out.println("orderitem is null");
                con.rollback();
                return null;
            }

        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return orderitem;

    }

    public static boolean insertOrderitem(int order_id, int cart_id) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_BOOK_ID_ORDER_ITEM_ID)) {
            prstatement.setInt(1, order_id);
            prstatement.setInt(2, cart_id);
            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

}
