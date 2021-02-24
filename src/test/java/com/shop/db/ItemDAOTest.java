package com.shop.db;

import com.shop.entity.Item;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDAOTest extends TestCase {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_ITEM = "SELECT * FROM items where book_id = (?) and cart_id = (?)";

    @Test
    public void testGetItemParam() throws ClassNotFoundException {
        Item item = null;
        ItemDAO itemDAO = new ItemDAO();
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ITEM)) {

            prstatement.setInt(1, 7);
            prstatement.setInt(2, 1);

            item = itemDAO.getItemParam(prstatement);

            Assert.assertEquals(1, item.getQuantity());

        } catch (SQLException e) {

        }

    }

    @Test
    public void testCheckItem() throws ClassNotFoundException {
        Item item = new Item();
        ItemDAO itemDAO = new ItemDAO();

        item = itemDAO.checkItem(7, 1);

        Assert.assertEquals(1, item.getQuantity());

    }

    @Test
    public void testInsertItem() throws ClassNotFoundException {

        Boolean flag;
        ItemDAO itemDAO = new ItemDAO();
        flag = itemDAO.insertItem(9, 11, 1);
        Assert.assertTrue(flag);

    }

    @Test
    public void testTestInsertItem() throws ClassNotFoundException {

        String temp = "{\"books\":[{\"id\":\"8\",\"value\":\"1\"},{\"id\":\"9\",\"value\":\"2\"}]}";
        Boolean flag;
        ItemDAO itemDAO = new ItemDAO();
        flag = itemDAO.insertItem(temp, 1);
        Assert.assertTrue(flag);

    }

}