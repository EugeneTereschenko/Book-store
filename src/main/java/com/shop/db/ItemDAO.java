package com.shop.db;

import com.shop.entity.Book;
import com.shop.entity.Item;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;


public class ItemDAO {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_ITEM = "SELECT * FROM items where book_id = (?) and cart_id = (?)";
    private static final String SQL_INSERT_ITEM = "INSERT INTO items (book_id, cart_id, quantity, created_at, update_at) VALUES (?, ?, ?, NOW(), NOW())";

    private static final Logger logger = LogManager.getLogger(ItemDAO.class);



    /**
     *
     * @param prstatement
     * @return
     */
    public Item getItemParam(PreparedStatement prstatement){
        Item item = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                item = new Item();
                item.setBook_id(result.getInt("book_id"));
                item.setCart_id(result.getInt("cart_id"));
                item.setQuantity(result.getInt("quantity"));

            } else {
                logger.info("item is not valid");

                return null;
            }
        } catch (SQLException | NullPointerException e) {
            logger.info("error find user" + e);
        }
        return item;

    }

    /**
     *
     * @param book_id
     * @param cart_id
     * @return
     * @throws ClassNotFoundException
     */

    public Item checkItem(int book_id, int cart_id) throws ClassNotFoundException {

        Item item = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ITEM)) {

            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }

            prstatement.setInt(1, book_id);
            prstatement.setInt(2, cart_id);

            item = getItemParam(prstatement);

            if (item == null){
                con.rollback();
            }

        } catch (SQLException e) {
            logger.info("error find user" + e);
        }

        return item;

    }

    /**
     *
     * @param book_id
     * @param cart_id
     * @param quantity
     * @return
     * @throws ClassNotFoundException
     */


    public boolean insertItem(int book_id, int cart_id, int quantity) throws ClassNotFoundException {
        Item item = null;
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_INSERT_ITEM)) {
                    prstatement.setInt(1, book_id);
                    prstatement.setInt(2, cart_id);
                    prstatement.setInt(3, quantity);
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
     * @param temp
     * @param cart_id
     * @return
     * @throws ClassNotFoundException
     */
    public boolean insertItem(String temp, int cart_id) throws ClassNotFoundException {

        String id = null;
        String value = null;

        Book book = new Book();
        BookDAO bookDAO = new BookDAO();


        JSONObject json = new JSONObject(temp);
        JSONArray array = json.getJSONArray("books");

        JSONObject myJsonObject = new JSONObject();

        for (int i=0; i < array.length(); i++ ){

            myJsonObject = array.getJSONObject(i);
            id = (String) myJsonObject.get("id");
            value = myJsonObject.getString("value");
            if (value != null && !value.equals("undefined")) {
                book = bookDAO.checkBookById(Integer.parseInt(id));
                //
                Boolean flag = insertItem(book.getId(), cart_id, Integer.parseInt(value));
                if (!flag){
                    logger.info(" book getId " + book.getId() + " cart " + cart_id + " value " + Integer.parseInt(value));
                    return false;
                }
            }

        }
        return true;
    }

}
