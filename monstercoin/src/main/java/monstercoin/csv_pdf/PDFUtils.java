package monstercoin.csv_pdf;

import com.itextpdf.text.*;
import org.springframework.stereotype.Component;

@Component
public class PDFUtils
{
    public static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);

    public void addMetaData(Document document) {
        document.addTitle("Wallet");
        document.addKeywords("Java, PDF, Wallet");
        document.addAuthor("Kacper Zmijewski");
        document.addCreator("Kacper Zmijewski");
    }

    public void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("Wallet", catFont));
        addEmptyLine(preface, 1);

        document.add(preface);
    }

    public void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}

