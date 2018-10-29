/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcx.web.smartmeter.models;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author Leolen
 */
public class PDF {
    
    public static final String PDF_DESTINATION = "results/itext_example/hello_print.pdf";
    
    public PDF() throws IOException{
        File file = new File(PDF_DESTINATION);
        file.getParentFile().mkdirs();
    }
    
    public int createPDF() throws IOException{
        int out_ = -1;
        FileOutputStream file_out_stream = new FileOutputStream(PDF_DESTINATION);
        PdfWriter pdf_writer = new PdfWriter(file_out_stream);
        
        PdfDocument pdf_document = new PdfDocument(pdf_writer);
        Document document = new Document(pdf_document);
        
        document.add(new Paragraph("Hello"));
        
        document.close();
        
        out_ = 1;
        return out_;
    }
}
