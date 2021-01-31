package ek.trans;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class J4yToWord
{
    private static class ParserCB implements J4yParser.Callback
    {
        private XWPFDocument doc;
                
        public ParserCB(XWPFDocument doc)
        {
            this.doc = doc;
        }
        
        
        @Override
        public void onRecord(String id, String jp, String en, String romaji) throws Exception
        {
            // Japanese
            XWPFParagraph para = doc.createParagraph();
            XWPFRun run = para.createRun();
            run.setText(jp);
            run.setFontSize(18);
            run.setColor("0072BC");
            run.setFontFamily("Yu Gothic Medium");

            // English
            para = doc.createParagraph();
            run = para.createRun();
            run.setText(en);
            run.setFontSize(12);

            // Romaji
            para = doc.createParagraph();
            run = para.createRun();
            run.setText(romaji);
            run.setColor("0072BC");
            run.setFontSize(12);

            // ID
            para = doc.createParagraph();
            run = para.createRun();
            run.setText(id);
            run.setColor("AAAAAA");
            run.setFontSize(10);

            // Blank line
            para = doc.createParagraph();
            run = para.createRun();
            run.setText("");
        }
    }
    
    
    public J4yToWord()
    {
    }

    
    public void process(File dataFile, File wordFile) throws Exception
    {
        XWPFDocument doc = new XWPFDocument();

        // Parse data file
        ParserCB cb = new ParserCB(doc);
        J4yParser.parse(dataFile, cb);
    
        // Write doc
        FileOutputStream out = new FileOutputStream(wordFile);
        doc.write(out);
        doc.close();
        out.close();
    }
    
}
