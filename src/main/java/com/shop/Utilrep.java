package com.shop;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Stream;

public class Utilrep {


    private static String FILE_PATH = "./web/report/new.pdf";

    public static void preparePDFreport() throws FileNotFoundException, DocumentException {

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("new3.pdf"));
        doc.open();
        Font bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph paragraph = new Paragraph("user ");

        //doc.add(paragraph);
       // doc.close();

        PdfPTable table = new PdfPTable(5);
        Stream.of("Chrono Unit", "Duration").forEach(table::addCell);

        Arrays.stream(ChronoUnit.values())
                .forEach(val ->{
                    table.addCell(val.toString());
                    table.addCell(val.getDuration().toString());
               });



        //Stream.of("Email user", "Books title", "value", "cost").forEach(table::addCell);
        //PdfPCell = new PdfPCell();
        //Stream.of("", "Test", "Stop", "cWhat").forEach(table::addCell);


        paragraph.add(table);
        doc.add(paragraph);
        doc.close();

    }


    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        preparePDFreport();
    }
}
