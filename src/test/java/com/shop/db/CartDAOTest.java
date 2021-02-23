package com.shop.db;

import com.shop.entity.Book;
import com.shop.entity.Cart;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAOTest extends TestCase {


    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_CART_WITH_CHECKOUT_STEP = "SELECT * FROM carts where id = (?) AND checkout_step = (?)";

    @Test
    public void testGetCartParam() throws ClassNotFoundException {

        Cart cart = new Cart();
        CartDAO cartDAO = new CartDAO();

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_CART_WITH_CHECKOUT_STEP)) {

            prstatement.setInt(1, 6);
            prstatement.setString(2, "complete");
            cart = cartDAO.getCartParam(prstatement);

        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        Assert.assertEquals(49, cart.order_total_price);

    }

    @Test
    public void testCheckCartByStep() throws ClassNotFoundException {

        Cart cart = new Cart();
        CartDAO cartDAO = new CartDAO();
        cart = cartDAO.checkCartByStep(6, "complete");

        Assert.assertEquals(49, cart.order_total_price);

    }

    @Test
    public void testInsertCart() throws ClassNotFoundException {

        Cart cart = new Cart();
        CartDAO cartDAO = new CartDAO();
        cart = cartDAO.insertCart(33,50, 48, "complete");

        Assert.assertEquals(9, cart.getId());
    }

    @Test
    public void testUpdateCartDelivery() throws ClassNotFoundException {
        Boolean flag;
        CartDAO cartDAO = new CartDAO();
        flag = cartDAO.updateCartDelivery(8, 7, "complete");
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdateCart() throws ClassNotFoundException {
        Boolean flag;
        CartDAO cartDAO = new CartDAO();
        flag = cartDAO.updateCart(8, "complete");
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdateCartCoupon() throws ClassNotFoundException {
        CartDAO cartDAO = new CartDAO();
        Boolean flag;
        flag = cartDAO.updateCartCoupon(8, 321321);
        Assert.assertTrue(flag);
    }

    @Test
    public void testFindAllCarts() throws ClassNotFoundException {

        List<Cart> carts = new ArrayList<>();
        CartDAO cartDAO = new CartDAO();
        carts = cartDAO.findAllCarts();
        Assert.assertEquals(7, carts.size());

    }

    @Test
    public void testFindallBooksByCartID() throws ClassNotFoundException {

        List<Book> books = new ArrayList<>();
        CartDAO cartDAO = new CartDAO();
        books = cartDAO.findallBooksByCartID(1);
        Assert.assertEquals(3, books.size());

    }

    @Test
    public void testFindallBookValueByCartID() throws ClassNotFoundException {

        int value = 0;
        CartDAO cartDAO = new CartDAO();
        value = cartDAO.findallBookValueByCartID(1, 7);
        Assert.assertEquals(1, value);
    }


}