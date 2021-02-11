package com.shop.db;

import com.shop.entity.Book;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {


    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_BOOK = "SELECT * FROM books where title = (?)";
    private static final String SQL_FIND_BOOK_BY_ID = "SELECT * FROM books where id = (?)";

    private static final String SQL_FIND_ALL_BOOKS = "SELECT * FROM books";
    private static final String SQL_FIND_BOOKS_FROM_TO = "SELECT * FROM books where id BETWEEN ? AND ? ";

    private static final String SQL_FIND_BOOKS_FROM_TO_BY_ORDER_BY_TITLE = "SELECT * FROM books where id BETWEEN 1 AND (?) ORDER BY title";
    private static final String SQL_FIND_BOOKS_FROM_TO_BY_ORDER_BY_AUTHOR = "SELECT * FROM books where id BETWEEN 1 AND (?) ORDER BY author";
    private static final String SQL_FIND_BOOKS_FROM_TO_BY_ORDER_BY_PRICE = "SELECT * FROM books where id BETWEEN 1 AND (?) ORDER BY price";
    private static final String SQL_FIND_BOOKS_FROM_TO_BY_ORDER_BY_YEAR = "SELECT * FROM books where id BETWEEN 1 AND (?) ORDER BY year";


    public Book getBookParam(PreparedStatement prstatement){
        Book book = null;

        try (ResultSet result = prstatement.executeQuery()) {
            if (result.next()) {
                book = new Book();
                book.setId(result.getInt("id"));
                book.setAuthor(result.getString("Author"));
                book.setDescription(result.getString("description"));
                book.setImage(result.getString("image"));
                book.setMaterials(result.getString("materials"));
                book.setPrice(result.getInt("price"));
                book.setHeight(result.getInt("height"));
                book.setWidth(result.getInt("width"));
                book.setDepth(result.getInt("depth"));
                book.setYear(result.getString("year"));
                book.setIn_stock(result.getInt("in_stock"));
                //book.setCreated_at(result.getDate("created_at"));
                //book.setUpdate_at(result.getDate("update-at"));
            }
        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }
        return book;

    }


    public Book checkBook(String title) throws ClassNotFoundException {

        Book book = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_BOOK)) {
            prstatement.setString(1, title);

            book = getBookParam(prstatement);

        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return book;

    }


    public Book checkBookById(int id) throws ClassNotFoundException {

        Book book = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_BOOK_BY_ID)) {
            prstatement.setInt(1, id);

            book = getBookParam(prstatement);

        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return book;

    }

    public List<Book> findAllBooks() throws ClassNotFoundException {

        List<Book> books = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); Statement statement = con.createStatement(); ResultSet result = statement.executeQuery(SQL_FIND_ALL_BOOKS)) {
            while (result.next()) {
                Book book = new Book();
                book.setId(result.getInt("id"));
                book.setAuthor(result.getString("author"));
                book.setTitle(result.getString("title"));
                book.setDescription(result.getString("description"));
                book.setImage(result.getString("image"));
                book.setMaterials(result.getString("materials"));
                book.setPrice(result.getInt("price"));
                book.setHeight(result.getInt("height"));
                book.setWidth(result.getInt("width"));
                book.setDepth(result.getInt("depth"));
                book.setYear(result.getString("year"));
                book.setIn_stock(result.getInt("in_stock"));
                //book.setCreated_at(result.getDate("created_at"));
                //book.setUpdate_at(result.getDate("update-at"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return books;
    }

    public static List<Book> findFromTO(int begin, int end) throws ClassNotFoundException {
        List<Book> books = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Book book = null;

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_BOOKS_FROM_TO)){

        prstatement.setInt(1, begin);
        prstatement.setInt(2, end);

        ResultSet result = prstatement.executeQuery();

            while (result.next()) {
                book = new Book();
                book.setId(result.getInt("id"));
                book.setAuthor(result.getString("author"));
                book.setTitle(result.getString("title"));
                book.setDescription(result.getString("description"));
                book.setImage(result.getString("image"));
                book.setMaterials(result.getString("materials"));
                book.setPrice(result.getInt("price"));
                book.setHeight(result.getInt("height"));
                book.setWidth(result.getInt("width"));
                book.setDepth(result.getInt("depth"));
                book.setYear(result.getString("year"));
                book.setIn_stock(result.getInt("in_stock"));
                //book.setCreated_at(result.getDate("created_at"));
                //book.setUpdate_at(result.getDate("update-at"));

                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return books;

    }

    public int totalPrice(String temp) throws ClassNotFoundException {
       // System.out.println("price total");

        String id = null;
        String value = null;
        int total = 0;
        Book book = new Book();

        JSONObject json = new JSONObject(temp);

        JSONArray array = json.getJSONArray("books");
        int i = 0;
        JSONObject myJsonObject = new JSONObject();
        while(i < array.length()){
            myJsonObject = array.getJSONObject(i);
            id = (String) myJsonObject.get("id");
            value = myJsonObject.getString("value");
            if (!value.equals("undefined") && value != null) {
                book = checkBookById(Integer.parseInt(id));
                total += book.getPrice() * Integer.parseInt(value);
            }
            i++;
        }
        //System.out.println(" total " + total);
        return total;
    }

    public List<Book> findOrderTOByTitle(int rows) throws ClassNotFoundException {


        List<Book> books = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Book book = null;

        try (Connection con = DriverManager.getConnection(URL)) {
            try (PreparedStatement prstatement = con.prepareStatement(SQL_FIND_BOOKS_FROM_TO_BY_ORDER_BY_TITLE)) {

                prstatement.setInt(1, rows);

                ResultSet result = prstatement.executeQuery();

                while (result.next()) {
                    book = new Book();
                    book.setId(result.getInt("id"));
                    book.setAuthor(result.getString("author"));
                    book.setTitle(result.getString("title"));
                    book.setDescription(result.getString("description"));
                    book.setImage(result.getString("image"));
                    book.setMaterials(result.getString("materials"));
                    book.setPrice(result.getInt("price"));
                    book.setHeight(result.getInt("height"));
                    book.setWidth(result.getInt("width"));
                    book.setDepth(result.getInt("depth"));
                    book.setYear(result.getString("year"));
                    book.setIn_stock(result.getInt("in_stock"));
                    //book.setCreated_at(result.getDate("created_at"));
                    //book.setUpdate_at(result.getDate("update-at"));

                    books.add(book);
                }

            }
        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return books;

    }

    public List<Book> findOrderTOByAuthor(int rows) throws ClassNotFoundException {


        List<Book> books = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Book book = null;

        try (Connection con = DriverManager.getConnection(URL)) {
            try (PreparedStatement prstatement = con.prepareStatement(SQL_FIND_BOOKS_FROM_TO_BY_ORDER_BY_AUTHOR)) {

                prstatement.setInt(1, rows);

                ResultSet result = prstatement.executeQuery();

                while (result.next()) {
                    book = new Book();
                    book.setId(result.getInt("id"));
                    book.setAuthor(result.getString("author"));
                    book.setTitle(result.getString("title"));
                    book.setDescription(result.getString("description"));
                    book.setImage(result.getString("image"));
                    book.setMaterials(result.getString("materials"));
                    book.setPrice(result.getInt("price"));
                    book.setHeight(result.getInt("height"));
                    book.setWidth(result.getInt("width"));
                    book.setDepth(result.getInt("depth"));
                    book.setYear(result.getString("year"));
                    book.setIn_stock(result.getInt("in_stock"));
                    //book.setCreated_at(result.getDate("created_at"));
                    //book.setUpdate_at(result.getDate("update-at"));

                    books.add(book);
                }

            }
        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return books;

    }


    public List<Book> findOrderTOByPrice(int rows) throws ClassNotFoundException {


        List<Book> books = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Book book = null;

        try (Connection con = DriverManager.getConnection(URL)) {
            try (PreparedStatement prstatement = con.prepareStatement(SQL_FIND_BOOKS_FROM_TO_BY_ORDER_BY_PRICE)) {

                prstatement.setInt(1, rows);

                ResultSet result = prstatement.executeQuery();

                while (result.next()) {
                    book = new Book();
                    book.setId(result.getInt("id"));
                    book.setAuthor(result.getString("author"));
                    book.setTitle(result.getString("title"));
                    book.setDescription(result.getString("description"));
                    book.setImage(result.getString("image"));
                    book.setMaterials(result.getString("materials"));
                    book.setPrice(result.getInt("price"));
                    book.setHeight(result.getInt("height"));
                    book.setWidth(result.getInt("width"));
                    book.setDepth(result.getInt("depth"));
                    book.setYear(result.getString("year"));
                    book.setIn_stock(result.getInt("in_stock"));
                    //book.setCreated_at(result.getDate("created_at"));
                    //book.setUpdate_at(result.getDate("update-at"));

                    books.add(book);
                }

            }
        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return books;

    }



    public List<Book> findOrderTOByYear(int rows) throws ClassNotFoundException {


        List<Book> books = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");

        Book book = null;

        try (Connection con = DriverManager.getConnection(URL)) {
            try (PreparedStatement prstatement = con.prepareStatement(SQL_FIND_BOOKS_FROM_TO_BY_ORDER_BY_YEAR)) {

                prstatement.setInt(1, rows);

                ResultSet result = prstatement.executeQuery();

                while (result.next()) {
                    book = new Book();
                    book.setId(result.getInt("id"));
                    book.setAuthor(result.getString("author"));
                    book.setTitle(result.getString("title"));
                    book.setDescription(result.getString("description"));
                    book.setImage(result.getString("image"));
                    book.setMaterials(result.getString("materials"));
                    book.setPrice(result.getInt("price"));
                    book.setHeight(result.getInt("height"));
                    book.setWidth(result.getInt("width"));
                    book.setDepth(result.getInt("depth"));
                    book.setYear(result.getString("year"));
                    book.setIn_stock(result.getInt("in_stock"));
                    //book.setCreated_at(result.getDate("created_at"));
                    //book.setUpdate_at(result.getDate("update-at"));

                    books.add(book);
                }

            }
        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

        return books;

    }


    public static void main(String[] args) throws ClassNotFoundException{

    }

}
