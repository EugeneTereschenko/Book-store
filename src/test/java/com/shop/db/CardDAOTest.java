package com.shop.db;

import com.shop.entity.Card;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CardDAOTest extends TestCase {


    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_CARD = "SELECT * FROM cards where user_id = (?)";

    @Test
    public void testGetCardParam() throws ClassNotFoundException {

        Card card = null;
        CardDAO cardDAO = new CardDAO();
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_CARD)) {

            prstatement.setInt(1, 1);

            card = cardDAO.getCardParam(prstatement);

            Assert.assertEquals(0, card.getCvv());

        } catch (SQLException e) {
        }

    }

    @Test
    public void testCheckCardByUserId() throws ClassNotFoundException {

        Card card = null;
        CardDAO cardDAO = new CardDAO();
        card = cardDAO.checkCardByUserId(1);

        Assert.assertEquals(0, card.getCvv());
    }

    @Test
    public void testInsertCard() throws ClassNotFoundException {

        Card card = null;
        CardDAO cardDAO = new CardDAO();
        card = cardDAO.insertCard(1, "0320", 0, "Yevhen Teresch", "7777888899991111");

        Assert.assertEquals(0, card.getUser_id());
    }

}