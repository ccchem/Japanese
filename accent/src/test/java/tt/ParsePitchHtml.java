package tt;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

public class ParsePitchHtml
{

    public static void main(String[] args) throws Exception
    {
        String src = "D:\\Jap\\Accent\\a.html";
        BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(src), "SHIFT-JIS"));

        StringWriter writer = new StringWriter();

        String line;
        while ((line = rd.readLine()) != null)
        {
            if (line.startsWith("<tr><td><span "))
            {
                //System.out.println(line);
                extractText(writer, line);
                break;
            }
        }

        rd.close();
        
        System.out.println(writer.toString());
        
        writer.close();
    }

    
    private static void extractText(Writer wr, String line) throws Exception
    {
        int start = 8; // <tr><td>
        int idx1;

        StringBuilder accent = new StringBuilder();
        StringBuilder reading = new StringBuilder();
        
        wr.write("<item>\n");
        
        while((idx1 = line.indexOf("<span class=\"", start)) > 0)
        {
            start = idx1 + 13;
            int idx2 = line.indexOf("\">", start);
            if(idx2 < 0) throw new Exception("Could not parse line: " + line);
            
            String type = line.substring(idx1 + 13, idx2);
            start = idx2 + 2;
            
            int idx3 = line.indexOf("</span>", start);
            if(idx3 < 0) throw new Exception("Could not parse line: " + line);
            
            String text = line.substring(idx2 + 2, idx3);
            reading.append(text);
            start = idx3 + 7;
            
            //System.out.println(type + ";" + text);
            wr.write("<accent type=\"" + type + "\">");
            wr.write(text);
            wr.write("</accent>\n");
        }
        
        
        idx1 = line.indexOf("</td>", start);
        if(idx1 < 0) throw new Exception("Could not parse line: " + line);
        
        String text = line.substring(start, idx1).trim();
        if(text.startsWith("(") && text.endsWith(")")) text = text.substring(1, text.length()-1);
        
        //System.out.println(text);
        wr.write("<reading>");
        wr.write(reading.toString());
        wr.write("</reading>\n");
        
        wr.write("<word>");
        wr.write(text);
        wr.write("</word>\n");
        
        wr.write("</item>\n");
    }
}
