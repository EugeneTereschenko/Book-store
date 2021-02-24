package com.shop.db;

import com.shop.entity.Delivery;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeliveryDAOTest extends TestCase {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_DELIVERY = "SELECT * FROM deliveries where id = (?)";

    @Test
    public void testGetDeliveryParam() throws ClassNotFoundException {

        Delivery delivery = new Delivery();
        DeliveryDAO deliveryDAO = new DeliveryDAO();

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_DELIVERY)) {
            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setInt(1, 3);
            delivery = deliveryDAO.getDeliveryParam(prstatement);

            Assert.assertEquals(3, delivery.getId());

        } catch (SQLException e) {
        }

    }

    @Test
    public void testCheckDeliveryById() throws ClassNotFoundException {

        Delivery delivery = new Delivery();
        DeliveryDAO deliveryDAO = new DeliveryDAO();
        delivery = deliveryDAO.checkDeliveryById(3);

        Assert.assertEquals(3, delivery.getId());
    }


    @Test
    public void testInsertDelivery() throws ClassNotFoundException {
        Delivery delivery = new Delivery();
        DeliveryDAO deliveryDAO = new DeliveryDAO();
        delivery = deliveryDAO.insertDelivery("standart", "02/23/2021", 10);

        Assert.assertEquals(8, delivery.getId());
    }


}