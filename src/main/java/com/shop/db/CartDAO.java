package com.shop.db;

import com.shop.entity.Book;
import com.shop.entity.Cart;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CartDAO {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_CART = "SELECT * FROM carts where user_id = (?)";
    private static final String SQL_FIND_CART_WITH_CHECKOUT_STEP = "SELECT * FROM carts where id = (?) AND checkout_step = (?)";
    private static final String SQL_INSERT_CART = "INSERT INTO carts (created_at, update_at, user_id, order_total_price, item_total_price, checkout_step) VALUES (NOW(), NOW(), ?, ?, ?, ?)";
    private static final String SQL_UPDATE_CART = "UPDATE carts set checkout_step = (?) where id = (?)";
    private static final String SQL_UPDATE_CART_DELIVERY = "UPDATE carts set checkout_step = (?), delivery_id =(?) where id = (?)";
    private static final String SQL_UPDATE_CART_COUPON = "UPDATE carts set coupon = (?) where id = (?)";
    private static final String SQL_FIND_ALL_CARTS = "SELECT * FROM carts";
    private static final String SQL_SELECT_BOOKS_BY_CART_ID ="SELECT id, title, price, author FROM books where id IN (select book_id from items where cart_id = (?))";
    private static final String SQL_SELECT_QUANTITY_BOOK_BY_CART_ID ="select quantity from items where cart_id = (?) and book_id = (?)";


    private static final Logger logger = LogManager.getLogger(CartDAO.class);


    /**
     *
     * @param prstatement
     * @return
     */
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
                cart.setItem_total_price(result.getInt("item_total_price"));
               // cart.setCreated_at(result.getDate("created_at"));
            } else {
                logger.info("Cart is not valid");
                return null;
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("error find user" + e);
        }
        return cart;
    }

    /**
     *
     * @param id
     * @param checkout_step
     * @return
     * @throws ClassNotFoundException
     */

    public Cart checkCartByStep(int id, String checkout_step) throws ClassNotFoundException {

        Cart cart = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_CART_WITH_CHECKOUT_STEP)) {
            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setInt(1, id);
            prstatement.setString(2, checkout_step);
            cart = getCartParam(prstatement);
            if (cart == null){
                con.rollback();
            }
        } catch (SQLException e) {
            logger.info("error find user" + e);
        }
        return cart;
    }

    /**
     *
     * @param user_id
     * @param order_total_price
     * @param item_total_price
     * @param checkout_step
     * @return
     * @throws ClassNotFoundException
     */

    public Cart insertCart(int user_id, int order_total_price, int item_total_price, String checkout_step) throws ClassNotFoundException {
            Cart cart = null;
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_CART, Statement.RETURN_GENERATED_KEYS)) {
                    prstatement.setInt(1, user_id);
                    prstatement.setInt(2, order_total_price);
                    prstatement.setInt(3, item_total_price);
                    prstatement.setString(4, checkout_step);
                    if (prstatement.executeUpdate() > 0) {
                        ResultSet result = prstatement.getGeneratedKeys();
                        cart = new Cart();
                        if (result.next()) {
                            cart.setId(result.getInt(1));
                        }
                    }
                    return cart;
                } catch (SQLException e) {
                    logger.info("" + e);
                }
        return null;
    }


    /**
     *
     * @param id
     * @param delivery_id
     * @param checkout_step
     * @return
     * @throws ClassNotFoundException
     */
    public Boolean updateCartDelivery(int id, int delivery_id, String checkout_step) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_CART_DELIVERY)) {
            prstatement.setString(1, checkout_step);
            prstatement.setInt(2, delivery_id);
            prstatement.setInt(3, id);

            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.info("" + e);
        }
        return false;
    }

    /**
     *
     * @param id
     * @param checkout_step
     * @return
     * @throws ClassNotFoundException
     */
    public Boolean updateCart(int id, String checkout_step) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_CART)) {
            prstatement.setString(1, checkout_step);
            prstatement.setInt(2, id);
            if (prstatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            logger.info("" + e);
        }
        return false;
    }


    /**
     *
     * @param id
     * @param coupon
     * @return
     * @throws ClassNotFoundException
     */
    public Boolean updateCartCoupon(int id, int coupon) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_UPDATE_CART_COUPON, Statement.RETURN_GENERATED_KEYS)) {
            prstatement.setInt(1, coupon);
            prstatement.setInt(2, id);
            if (prstatement.executeUpdate() > 0) {
                ResultSet result = prstatement.getGeneratedKeys();
                return true;
            }

        } catch (SQLException e) {
            logger.info("" + e);
        }
        return false;
    }


    /**
     *
     * @return
     * @throws ClassNotFoundException
     */
    public List<Cart> findAllCarts() throws ClassNotFoundException {

        List<Cart> carts = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); Statement statement = con.createStatement(); ResultSet result = statement.executeQuery(SQL_FIND_ALL_CARTS)) {
            while (result.next()) {
                Cart cart = new Cart();
                cart.setId(result.getInt("id"));
                cart.setOrder_total_price(result.getInt("order_total_price"));
                cart.setItem_total_price(result.getInt("item_total_price"));
                cart.setUser_id(result.getInt("user_id"));
                cart.setCoupon(result.getInt("coupon"));
                cart.setCheckout_step(result.getString("checkout_step"));
                cart.setDelivery_id(result.getInt("delivery_id"));
                carts.add(cart);
            }
        } catch (SQLException e) {
            logger.info("" + e);
        }
        return carts;
    }


    /**
     *
     * @param cart_id
     * @return
     * @throws ClassNotFoundException
     */
    public static List<Book> findallBooksByCartID(int cart_id) throws ClassNotFoundException {
        List<Book> books = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_SELECT_BOOKS_BY_CART_ID)) {
            prstatement.setInt(1, cart_id);

                ResultSet result = prstatement.executeQuery();
                while (result.next()) {
                    Book book = new Book();
                    book.setId(result.getInt("id"));
                    book.setTitle(result.getString("title"));
                    book.setPrice(result.getInt("price"));
                    book.setAuthor(result.getString("author"));
                    books.add(book);
                }


        } catch (SQLException e) {
            logger.info("" + e);
        }
        return books;
    }

    /**
     *
     * @param cart_id
     * @param book_id
     * @return
     * @throws ClassNotFoundException
     */


    public static int findallBookValueByCartID(int cart_id, int book_id) throws ClassNotFoundException {
        int quantity = 0;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_SELECT_QUANTITY_BOOK_BY_CART_ID)) {
            prstatement.setInt(1, cart_id);
            prstatement.setInt(2, book_id);
            ResultSet result = prstatement.executeQuery();
            while (result.next()) {
                quantity = result.getInt("quantity");
            }
        } catch (SQLException e) {
            logger.info( ""  + e);
        }
        return quantity;
    }

}
