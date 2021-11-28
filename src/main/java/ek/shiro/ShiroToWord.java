package ek.shiro;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class ShiroToWord implements AssParser.Callback
{
    private XWPFDocument doc;
    
    public ShiroToWord()
    {
        doc = new XWPFDocument();
    }

    
    @Override
    public boolean onEvent(String start, String style, String text)
    {
        if(text == null) return true;
        
        //if("日文".equals(style))
        //{
            // Line 1: timestamp
            String time = start.substring(2, start.length()-3);
            
            XWPFParagraph para = doc.createParagraph();
            XWPFRun run = para.createRun();
            run.setText(time);
            run.setFontSize(10);
            run.setColor("BBBBBB");

            // Line 2: Text
            para = doc.createParagraph();
            run = para.createRun();
            run.setText(text);
            run.setFontSize(20);
            //run.setColor("0072BC");
            run.setColor("000000");
            run.setFontFamily("Yu Gothic Medium");
        //}
        
        return true;
    }
    
    
    public void write(File file) throws Exception
    {
        FileOutputStream out = new FileOutputStream(file);
        doc.write(out);
        doc.close();
        out.close();
    }
    
}
