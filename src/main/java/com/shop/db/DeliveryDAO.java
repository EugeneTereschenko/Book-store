package com.shop.db;

import com.shop.entity.Delivery;

import java.sql.*;
import java.util.logging.Logger;

public class DeliveryDAO {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_DELIVERY = "SELECT * FROM deliveries where id = (?)";
    private static final String SQL_INSERT_DELIVERY = "INSERT INTO deliveries (created_at, update_at, name, time, price) VALUES (NOW(), NOW(), ?, ?, ?)";
    //private static final String SQL_UPDATE_DELIVERY = "UPDATE carts set checkout_step = (?) where id = (?)";
    static final Logger logger = Logger.getLogger(String.valueOf(DeliveryDAO.class));


    /**
     *
     * @param prstatement
     * @return
     */
    public Delivery getDeliveryParam(PreparedStatement prstatement){
        Delivery delivery = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                delivery = new Delivery();
                delivery.setId(result.getInt("id"));
                delivery.setName(result.getString("name"));
                delivery.setTime(result.getString("time"));
                delivery.setPrice(result.getInt("price"));
            } else {
                logger.info("Delivery is not valid");
                return null;
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("error find order" + e);
        }
        return delivery;
    }

    /**
     *
     * @param id
     * @return
     * @throws ClassNotFoundException
     */

    public Delivery checkDeliveryById(int id) throws ClassNotFoundException {

        logger.info(Integer.toString(id));

        Delivery delivery = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_DELIVERY)) {
            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setInt(1, id);
            delivery = getDeliveryParam(prstatement);
            if (delivery == null){
                con.rollback();
            }
        } catch (SQLException e) {
            logger.info("error find user" + e);
        }
        return delivery;
    }

    /**
     *
     * @param name
     * @param time
     * @param price
     * @return
     * @throws ClassNotFoundException
     */

    public Delivery insertDelivery(String name, String time, int price) throws ClassNotFoundException {
        Delivery delivery = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_DELIVERY, Statement.RETURN_GENERATED_KEYS)) {
            prstatement.setString(1, name);
            prstatement.setString(2, time);
            prstatement.setInt(3, price);
            if (prstatement.executeUpdate() > 0) {
                ResultSet result = prstatement.getGeneratedKeys();
                delivery = new Delivery();
                if (result.next()) {
                    delivery.setId(result.getInt(1));
                }
            }
            return delivery;
        } catch (SQLException e) {
            logger.info("" + e);
        }
        return null;
    }


}
