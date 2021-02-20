package com.shop;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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
            case "/createpdfdocument":
                createpdfdoc(request, response);
                break;
            default:
                break;
        }

    }

    private void createpdfdoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("application/pdf");

            try {

                Document doc = new Document();
                PdfWriter.getInstance(doc, response.getOutputStream());
               // PdfWriter.getInstance(doc, response.set)
                doc.open();
                Font bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                Paragraph paragraph = new Paragraph("user ");

                PdfPTable table = new PdfPTable(5);
                Stream.of("Chrono Unit", "Duration").forEach(table::addCell);

                Arrays.stream(ChronoUnit.values())
                        .forEach(val ->{
                            table.addCell(val.toString());
                            table.addCell(val.getDuration().toString());
                        });

                paragraph.add(table);
                doc.add(paragraph);
                doc.close();

            }catch (DocumentException e){
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