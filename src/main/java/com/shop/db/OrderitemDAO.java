package com.shop.db;

import com.shop.entity.Orderitem;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.function.Supplier;


public class OrderitemDAO {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_ORDER_ITEM = "SELECT * FROM order_items where book_id = (?) and delivery_id = (?)";
    private static final String SQL_INSERT_BOOK_ID_ORDER_ITEM_ID = "INSERT INTO order_items (book_id, quantity, delivery_id, created_at, update_at) " +
            "SELECT book_id, quantity, (?), now(), now() FROM items WHERE cart_id = (?);";

    private static final Logger logger = LogManager.getLogger(OrderitemDAO.class);


    /**
     *
     * @param prstatement
     * @return
     */
    public static Orderitem getOrderitemParam(PreparedStatement prstatement){
        Orderitem orderitem = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                orderitem = new Orderitem();
                orderitem.setBook_id(result.getInt("book_id"));
                orderitem.setOrder_id(result.getInt("delivery_id"));
                orderitem.setQuantity(result.getInt("quantity"));
            } else {
                logger.info("orderitem is not valid");

                return null;
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("error find orderitem" + e);
        }
        return orderitem;

    }

    /**
     *
     * @param book_id
     * @param order_id
     * @return
     * @throws ClassNotFoundException
     */

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
                logger.info("orderitem is null");
                con.rollback();
                return null;
            }

        } catch (SQLException e) {
            logger.info("error find user" + e);
        }

        return orderitem;

    }


    /**
     *
     * @param order_id
     * @param cart_id
     * @return
     * @throws ClassNotFoundException
     */
    public static boolean insertOrderitem(int order_id, int cart_id) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_BOOK_ID_ORDER_ITEM_ID)) {
            prstatement.setInt(1, order_id);
            prstatement.setInt(2, cart_id);
            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.info((Supplier<String>) e);
        }
        return false;
    }

}
