package com.shop.db;

import com.shop.entity.Orderitem;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderitemDAOTest extends TestCase {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_ORDER_ITEM = "SELECT * FROM order_items where book_id = (?) and delivery_id = (?)";


    @Test
    public void testGetOrderitemParam() throws ClassNotFoundException {

        Orderitem orderitem = null;
        OrderitemDAO orderitemdao = new OrderitemDAO();
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ORDER_ITEM)) {

            prstatement.setInt(1, 11);
            prstatement.setInt(2, 1);
            orderitem = orderitemdao.getOrderitemParam(prstatement);
            Assert.assertEquals(2, orderitem.getQuantity());

        } catch (SQLException e) {

        }

    }

    @Test
    public void testCheckOrderitem() throws ClassNotFoundException {

        Orderitem orderitem = null;
        OrderitemDAO orderitemdao = new OrderitemDAO();
        orderitem = orderitemdao.checkOrderitem(11, 1);

        Assert.assertEquals(2, orderitem.getQuantity());

    }

    @Test
    public void testInsertOrderitem() throws ClassNotFoundException {
        Boolean flag = null;
        OrderitemDAO orderitemdao = new OrderitemDAO();
        flag = orderitemdao.insertOrderitem(1, 1);
        Assert.assertTrue(flag);
    }

}