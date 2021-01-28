package net.codeshop.java.db;

import net.codeshop.java.entity.Book;
import net.codeshop.java.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {


    private static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=root";
    private static final String SQL_FIND_BOOK = "SELECT * FROM books where title = (?)";
    private static final String SQL_FIND_ALL_BOOKS = "SELECT * FROM books";


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

}
