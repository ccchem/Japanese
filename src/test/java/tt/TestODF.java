package tt;

import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;


public class TestODF
{

    private static void addJapStyle(XWPFStyles styles)
    {
        String styleId = "Jap";
        
        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.addNewName().setVal(styleId);
        ctStyle.setCustomStyle(STOnOff.TRUE);

        CTRPr rpr = ctStyle.getRPr();
        if(rpr == null)
        {
            rpr = ctStyle.addNewRPr();
        }
        
        rpr.addNewColor().setVal("FF0000");
        
        XWPFStyle style = new XWPFStyle(ctStyle);
        style.setType(STStyleType.PARAGRAPH);
        style.setStyleId(styleId);
        
        styles.addStyle(style);
    }
    
    
    public static void main(String[] args) throws Exception
    {
        XWPFDocument doc = new XWPFDocument();
        
        XWPFStyles styles = doc.createStyles();
        addJapStyle(styles);
        
        XWPFParagraph para = doc.createParagraph();
        XWPFRun run = para.createRun();
        run.setText("みなさんこんにちはひときです");
        run.setFontSize(18);
        run.setColor("0072BC");
        run.setFontFamily("Yu Gothic Medium");

        
        para = doc.createParagraph();
        para.setStyle("Jap");
        run = para.createRun();
        run.setText("みなさんこんにちはひときです");


        FileOutputStream out = new FileOutputStream("/tmp/t2.docx");
        doc.write(out);
        doc.close();
        out.close();
    }

}
