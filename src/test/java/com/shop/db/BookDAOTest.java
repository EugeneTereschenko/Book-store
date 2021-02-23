package com.shop.db;

import com.shop.entity.Book;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAOTest  {

    static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";
    private static final String SQL_FIND_BOOK = "SELECT * FROM books where title = (?)";


    @Test
    public void testGetBookParam() throws ClassNotFoundException {

        Book book = null;
        BookDAO bookDAO = new BookDAO();
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_BOOK)) {
            prstatement.setString(1, "The Mirror and the Light");
            book = bookDAO.getBookParam(prstatement);
            Assert.assertEquals(3, book.getId());
        } catch (SQLException e) {
            System.out.println("error find user" + e);
        }

    }
    @Test
    public void testCheckBook() throws ClassNotFoundException {

        Book book = new Book();
        BookDAO bookDAO = new BookDAO();
        book = bookDAO.checkBook("The Mirror and the Light");
        Assert.assertEquals(3 , book.getId());

    }

    @Test
    public void testCheckBookById() throws ClassNotFoundException {
        Book book = new Book();
        BookDAO bookDAO = new BookDAO();
        book = bookDAO.checkBookById(3);
        Assert.assertEquals("The Mirror and the Light", book.getTitle());
    }

    @Test
    public void testFindAllBooks() throws ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();

        books = (ArrayList<Book>) bookDAO.findAllBooks();

        Assert.assertEquals(books.size(), 15);

    }

    @Test
    public void testFindFromTO() throws ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();

        books = (ArrayList<Book>) bookDAO.findFromTO(1, 4);

        Assert.assertEquals(books.size(), 4);

    }

    @Test
    public void testTotalPrice() throws ClassNotFoundException {

        String str = "{\"books\":[{\"id\":\"1\",\"value\":\"1\"},{\"id\":\"2\",\"value\":\"1\"}]}";

        BookDAO bookDAO = new BookDAO();
        int temp = bookDAO.totalPrice(str);
        Assert.assertEquals(temp, 44);

    }

    @Test
    public void testFindOrderTOByTitle() throws ClassNotFoundException {
       ArrayList<Book> books = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();

        books = (ArrayList<Book>) bookDAO.findOrderTOByTitle(4);

        Assert.assertEquals(books.size(), 4);


    }

    @Test
    public void testFindOrderTOByAuthor() throws ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();

        books = (ArrayList<Book>) bookDAO.findOrderTOByAuthor(4);

        Assert.assertEquals(books.size(), 4);


    }

    @Test
    public void testFindOrderTOByPrice() throws ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();

        books = (ArrayList<Book>) bookDAO.findOrderTOByPrice(4);

        Assert.assertEquals(books.size(), 4);




    }

    @Test
    public void testFindOrderTOByYear() throws ClassNotFoundException {

        ArrayList<Book> books = new ArrayList<>();
        BookDAO bookDAO = new BookDAO();

        books = (ArrayList<Book>) bookDAO.findOrderTOByYear(4);

        Assert.assertEquals(books.size(), 4);



    }

    @Test
    public void testInsertBook() throws ClassNotFoundException {

        Boolean flag;
        BookDAO bookDAO = new BookDAO();
        flag = bookDAO.insertBook("Test", "Test", "000099988", "paper", 40, 200, 220, 220, "2020", 4, "Test");
        Assert.assertTrue(flag);

    }



    @Test
    public void testUpdateBook() throws ClassNotFoundException {

        Boolean flag;
        BookDAO bookDAO = new BookDAO();
        flag = bookDAO.updateBook("Test", "Test", "000099900", "paper", 40, 200, 220, 220, "2020", 4, "Test", 21);
        Assert.assertTrue(flag);

    }


    @Test
    public void testDeleteBook() throws ClassNotFoundException {

        Boolean flag;
        BookDAO bookDAO = new BookDAO();

        flag = bookDAO.deleteBook(21);

        Assert.assertTrue(flag);
    }

}