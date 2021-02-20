package com.shop;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.FileNotFoundException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Stream;

public class Utilrep {


    private static String FILE_PATH = "./web/report/new.pdf";

    public static void preparePDFreport() throws FileNotFoundException, DocumentException {

        Document doc = new Document();
        //PdfWriter.getInstance(doc, new FileOutputStream("new3.pdf"));
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

        //Create cells
        // PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Cell 1"));
        // PdfPCell pdfPCell2 = new PdfPCell(new Paragraph("Cell 2"));
        //PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Cell 3"));
        // PdfPCell pdfPCell4 = new PdfPCell(new Paragraph("Cell 4"));

        //Add cells to table
        // table.addCell(pdfPCell1);
        // table.addCell(pdfPCell2);
        // table.addCell(pdfPCell3);
        // table.addCell(pdfPCell4);
        //Arrays.stream(ChronoUnit.values())
        //        .forEach(val ->{
        //            table.addCell(val.toString());
        //            table.addCell(val.getDuration().toString());
        //        });



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
