package ek.ja.accent.etl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.Charset;

public class DataExtractor
{
    
    public static void extractRows(File dir, String fileName) throws Exception
    {
        String name = null;
        if(fileName.endsWith(".html")) 
        {
            name = fileName.substring(0, fileName.length() - 5);
        }
        else
        {
            name = fileName;
        }
        
        File srcFile = new File(dir, name + ".html");
        File tgtFile = new File(dir, name + "-u" + ".html");
        
        BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "SHIFT-JIS"));
        FileWriter wr = new FileWriter(tgtFile, Charset.forName("UTF-8"));
        
        writeHeader(wr);
        
        String line;
        while((line = rd.readLine()) != null)
        {
            if(line.startsWith("<tr><td"))
            {
                int startIdx = line.indexOf(">", 7) + 1;
                int endIdx = line.endsWith("</td></tr>") ? line.length() - 10 : line.length();
                
                line = line.substring(startIdx, endIdx);
                if(line.length() < 1) continue;
                
                if(line.startsWith("<a "))
                {
                    line = clean(line);
                }
                                
                AccentEntry entry = parseLine(line);
                writeEntry(wr, entry);
                wr.write("\n");
            }
        }

        writeFooter(wr);
        
        wr.close();
        rd.close();
    }
    
    
    public static String clean(String line)
    {
        line = removeTag(line, "<a ");
        line = removeTag(line, "<script ");
        line = line.replaceAll("</a>|</script>", "");
        
        return line;
    }
    
    
    private static String removeTag(String line, String tag)
    {
        int idx1;
        
        while((idx1 = line.indexOf(tag)) >= 0)
        {
            int idx2 = line.indexOf(">", idx1);
            line = line.substring(0, idx1) + line.substring(idx2 + 1);
        }
        
        return line;
    }
    
    
    public static String extractReading(String line) throws Exception
    {
        int start = 0;
        int idx1;

        StringBuilder reading = new StringBuilder();
        
        while((idx1 = line.indexOf("<span class=\"", start)) >= 0)
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
        }

        return reading.toString();
    }
    
    
    public static AccentEntry parseLine(String line) throws Exception
    {
        AccentEntry entry = new AccentEntry();
        
        int idx = line.lastIndexOf("</span>") + 7;
                
        entry.accentHtml = line.substring(0, idx);

        if(idx < line.length())
        {
            entry.word = line.substring(idx + 1);
            entry.word = entry.word.replaceAll("\\(|\\)", "");
        }

        idx = entry.accentHtml.indexOf(" / ");
        String tmp = idx > 0 ? entry.accentHtml.substring(0, idx) : entry.accentHtml;
        entry.reading = extractReading(tmp);
        
        return entry;
    }
    
    
    private static void writeHeader(Writer wr) throws Exception
    {
        wr.write("<html lang=\"ja\">");
        wr.write("<head>");
        wr.write("<link href=\"accent2.css\" type=\"text/css\" rel=\"stylesheet\">");
        wr.write("</head>");
        wr.write("<body>");
        wr.write("<table>");
        wr.write("\n");
    }


    private static void writeFooter(Writer wr) throws Exception
    {
        wr.write("</table>");
        wr.write("</body>");
        wr.write("</html>");
    }
    
    
    private static void writeEntry(Writer wr, AccentEntry entry) throws Exception
    {
        if(entry == null) return;
        
        wr.write("<tr><td>");
        wr.write(entry.reading);
        wr.write("</td><td>");
        wr.write(entry.accentHtml);
        wr.write("</td><td>");
        wr.write(entry.word == null ? entry.reading : entry.word);
        wr.write("</td></tr>");
    }

}
