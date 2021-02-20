package com.shop;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.shop.db.CartDAO;
import com.shop.db.DeliveryDAO;
import com.shop.db.UserDAO;
import com.shop.entity.Book;
import com.shop.entity.Cart;
import com.shop.entity.Delivery;
import com.shop.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@WebServlet("/set")
public class BookShopSettings extends HttpServlet {
    private static final String FILE_DIR = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();


        switch (action) {
            case "/upload":
                try {
                    upload(request, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();


        switch (action) {
            case "/settings":
                try {
                    settings(request, response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "/createpdfdoc":
                createpdfdoc(request, response);
                break;
            default:
                break;
        }

    }

    private void createpdfdoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String temp = request.getParameter("cartid");

            //System.out.println(temp);

            response.setContentType("application/pdf");

            try {

                Cart cart = new Cart();
                CartDAO cartDAO = new CartDAO();
                cart = cartDAO.checkCartByStep(Integer.parseInt(temp), "complete");

                User user = new User();
                UserDAO userDAO = new UserDAO();
                user = userDAO.checkUserbyId(cart.getUser_id());
                List<Book> books = new ArrayList<>();

                Delivery delivery = new Delivery();
                DeliveryDAO deliveryDAO = new DeliveryDAO();

                books = cartDAO.findallBooksByCartID(cart.getId());

                int quantity = 0;


                Document doc = new Document();
                PdfWriter.getInstance(doc, response.getOutputStream());
               // PdfWriter.getInstance(doc, response.set)
                doc.open();
                Font bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                Paragraph paragraph = new Paragraph(user.getEmail());

                PdfPTable table = new PdfPTable(2);
                Stream.of("Book", "Value").forEach(table::addCell);

                PdfPCell pdfPCell5;
                PdfPCell pdfPCell6;
                for (Book book : books){
                    quantity = cartDAO.findallBookValueByCartID(cart.getId(), book.getId());
                    pdfPCell5 = new PdfPCell(new Paragraph(book.getTitle()));
                    pdfPCell6 = new PdfPCell(new Paragraph(Integer.toString(quantity)));
                    table.addCell(pdfPCell5);
                    table.addCell(pdfPCell6);
                    // System.out.println("quantity " + quantity + "i" + i + "book get id" + book.getId());
                }

                int totalcost = 0;
                delivery = deliveryDAO.checkDeliveryById(cart.getDelivery_id());
                totalcost = delivery.getPrice() + cart.getOrder_total_price() - 5;

                Stream.of("Total price", "Order price").forEach(table::addCell);
                Stream.of(Integer.toString(cart.getItem_total_price()) + "$", Integer.toString(cart.getOrder_total_price()) + "$").forEach(table::addCell);

                Paragraph paragraphCost = new Paragraph("Total price with delivery $" + Integer.toString(totalcost));

                paragraph.add(table);
                doc.add(paragraph);
                doc.add(paragraphCost);
                doc.close();

            }catch (DocumentException | ClassNotFoundException e){
                throw new IOException(e.getMessage());
            }
    }

    private void settings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("settings.jsp");
        dispatcher.forward(request, response);
    }

    private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*
        Part file = request.getPart("file");
        String filename = getFilename(file);
        InputStream filecontent = file.getInputStream();
        // ... Do your file saving job here.

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("File " + filename + " successfully uploaded");


        //get the file chosen by the user
        Part filePart = request.getPart("file");


        final String fileName = get
        //get the InputStream to store the file somewhere
        InputStream fileInputStream = filePart.getInputStream();

        //for example, you can copy the uploaded file to the server
        //note that you probably don't want to do this in real life!
        //upload it to a file host like S3 or GCS instead
        File fileToSave = new File("/home/yevhen/IdeaProjects/BookStore/out/artifacts/BookStore_war_exploded" + filePart.getSubmittedFileName());
        Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

*/

        String path = getServletContext().getRealPath("") + File.separator + FILE_DIR;
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
            System.out.println("dir exists");
        }
        System.out.println(dir.getAbsolutePath());

        try {
            String fileName ="";
            for (Part part : request.getParts()){
                fileName = part.getSubmittedFileName();
                if (fileName != null && !fileName.isEmpty()){
                    part.write(path + File.separator + fileName);
                    System.out.println("-->" + fileName);
                }
            }
        } catch (Exception e){
            System.out.println("Error! " + e.getMessage());
        }

    }


}