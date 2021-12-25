package tt;

import java.io.File;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;


public class TestPdf
{

    public static void main(String[] args) throws Exception
    {
        PDDocument doc = new PDDocument();
        
        TrueTypeCollection ttc = new TrueTypeCollection(new File("/Windows/Fonts/UDDigiKyokashoN-R.ttc"));
        System.out.println(ttc);
        
        // UDDigiKyokashoN-R
        // UDDigiKyokashoNP-R
        // UDDigiKyokashoNK-R

        TrueTypeFont ttFont = ttc.getFontByName("UDDigiKyokashoN-R");
        PDFont font = PDType0Font.load(doc, ttFont, true);
        
        PDPage page = new PDPage();
        doc.addPage(page);

        PDPageContentStream cs = new PDPageContentStream(doc, page);
        
        //font.getStringWidth(text);
        
        cs.beginText();
        cs.setFont(font, 20);
        cs.newLineAtOffset(100, 700);
        cs.showText("ほとんどいない");
        cs.endText();
        
        cs.close();
        
        doc.save("/tmp/t.pdf");
        doc.close();
    }

}
