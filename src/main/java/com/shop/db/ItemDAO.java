package com.shop.db;

import com.shop.entity.Book;
import com.shop.entity.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class ItemDAO {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_ITEM = "SELECT * FROM items where book_id = (?) and cart_id = (?)";
    private static final String SQL_INSERT_ITEM = "INSERT INTO items (book_id, cart_id, quantity, created_at, update_at) VALUES (?, ?, ?, NOW(), NOW())";



    public static Item getUserParam(PreparedStatement prstatement){
        Item item = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                item = new Item();
                item.setBook_id(result.getInt("book_id"));
                item.setCart_id(result.getInt("cart_id"));
                item.setQuantity(result.getInt("quantity"));

            } else {
                System.out.println("item is not valid");

                return null;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("error find user" + e);
        }
        return item;

    }



    public static Item checkItem(String book_id, String cart_id) throws ClassNotFoundException {

        Item item = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ITEM)) {

            if(con.getAutoCommit()) {
                con.setAutoCommit(false);
            }

            prstatement.setString(1, book_id);
            prstatement.setString(2, cart_id);

            item = getUserParam(prstatement);

            if (item == null){
                con.rollback();
            }

        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return item;

    }




    public static boolean insertItem(int book_id, int cart_id, int quantity) throws ClassNotFoundException {
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
                    System.out.println(e);
                }
        return false;
    }

    public static boolean insertItem(String temp, int cart_id) throws ClassNotFoundException {

        String id = null;
        String value = null;

        Book book = new Book();
        BookDAO bookDAO = new BookDAO();

        JSONObject json = new JSONObject(temp);
        JSONArray array = json.getJSONArray("books");
        int i = 0;
        JSONObject myJsonObject = new JSONObject();
        while(i < array.length()){
            myJsonObject = array.getJSONObject(i);
            id = (String) myJsonObject.get("id");
            value = myJsonObject.getString("value");
            if (!value.equals("undefined") && value != null) {
                book = bookDAO.checkBookById(Integer.parseInt(id));
                //
                insertItem(book.getId(), cart_id, Integer.parseInt(value));
            }
            i++;
        }
        return false;
    }

    public static void main(String[] args){

    }

}
