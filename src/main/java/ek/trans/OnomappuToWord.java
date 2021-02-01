package ek.trans;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;


public class OnomappuToWord
{
    private static class YTCB implements YouTubeTranscriptParser.Callback
    {
        private XWPFDocument doc;

        
        public YTCB(XWPFDocument doc)
        {
            this.doc = doc;
        }
        
        
        @Override
        public void onTimestamp(String ts)
        {
            XWPFParagraph para = doc.createParagraph();
            XWPFRun run = para.createRun();
            run.setText(ts);
            run.setColor("BBBBBB");
        }

        
        @Override
        public void onText(String text)
        {
            int idx = findAsciIndex(text);
            if(idx > 1)
            {
                String line1 = text.substring(0, idx);
                String line2 = text.substring(idx);
                
                // Japanese
                XWPFParagraph para = doc.createParagraph();
                XWPFRun run = para.createRun();
                run.setText(line1);
                run.setFontSize(18);
                run.setColor("0072BC");
                run.setFontFamily("Yu Gothic Medium");

                // English
                para = doc.createParagraph();
                run = para.createRun();
                run.setText(line2);
                run.setFontSize(12);

                // Blank line
                para = doc.createParagraph();
                run = para.createRun();
                run.setText("");
            }
            else
            {
                System.out.println("WARNING:");
                System.out.println(text);
                
                XWPFParagraph para = doc.createParagraph();
                XWPFRun run = para.createRun();
                run.setText(text);
                run.setFontSize(18);
                run.setColor("0072BC");
                run.setFontFamily("Yu Gothic Medium");
            }

        }
        
        
        private int findAsciIndex(String str)
        {
            for(int i = 0; i < str.length(); i++)
            {
                char ch = str.charAt(i);
                if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) return i;
            }
            
            return -1;
        }

    }

    
    public OnomappuToWord()
    {
    }
    
    
    public void process(File trans, File word) throws Exception
    {
        XWPFDocument doc = new XWPFDocument();
        YTCB cb = new YTCB(doc);
        
        // Create doc
        YouTubeTranscriptParser pp = new YouTubeTranscriptParser();
        pp.parse(trans, cb);

        // Write doc
        FileOutputStream out = new FileOutputStream(word);
        doc.write(out);
        doc.close();
        out.close();
    }
    
}
