package com.shop.db;

import com.shop.entity.Address;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressDAOTest {

    private static final String URL = "jdbc:mysql://localhost:3306/test" + "?user=testcomauser&password=AcPqw.TO,CYU.dcP12";

    private static final String SQL_FIND_ADDRESS_BY_USER_ID = "SELECT * FROM addresses where user_id = (?)";

    @Test
    public void getAddressParam() throws ClassNotFoundException {
        AddressDAO addressDAO = new AddressDAO();

        Address address = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement prstatement = con.prepareStatement(SQL_FIND_ADDRESS_BY_USER_ID)) {
            if (con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            prstatement.setInt(1, 1);
            address = addressDAO.getAddressParam(prstatement);
            if (address == null) {
                System.out.println("address is null");
                con.rollback();
            }
        } catch (SQLException e) {
            System.out.println("error find user address" + e);
        }

        Assert.assertEquals("Yevhen", address.getFirst_name());

    }

    @Test
    public void checkAddressByUserId() throws ClassNotFoundException {
        AddressDAO addressDAO = new AddressDAO();
        Address address = new Address();
        address = addressDAO.checkAddressByUserId(1);

        Assert.assertEquals("Yevhen", address.getFirst_name());

    }

    @Test
    public void checkAddressById() throws ClassNotFoundException {

        AddressDAO addressDAO = new AddressDAO();
        Address address = new Address();
        address = addressDAO.checkAddressById(1);

        Assert.assertEquals("Yevhen", address.getFirst_name());
    }

    @Test
    public void insertAddress() throws ClassNotFoundException {
        Address address = new Address();
        AddressDAO addressDAO = new AddressDAO();
        address = addressDAO.insertAddress("shipping", "Yevhen", "Tereshchenko", "street teststreet", "Dnipro", 49006, "Ukraine", "055113323", 34);
        Assert.assertEquals(14, address.getId());
    }


    @Test
    public void updateDeliveryId() throws ClassNotFoundException {
      //  updateDeliveryId(6, 5);

        AddressDAO addressDAO = new AddressDAO();
        Boolean flag;
        flag = addressDAO.updateDeliveryId(34, 6);
        Assert.assertTrue(flag);

    }

    @Test
    public void updateDeliveryById() throws ClassNotFoundException {

        AddressDAO addressDAO = new AddressDAO();
        Boolean flag;
        flag = addressDAO.updateDeliveryById(6, 6, 34 );
        Assert.assertTrue(flag);

    }


}