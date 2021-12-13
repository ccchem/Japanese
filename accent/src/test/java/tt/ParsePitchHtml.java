package tt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import ek.ja.accent.etl.DataExtractor;


public class ParsePitchHtml
{
    public static void main(String[] args) throws Exception
    {
        File dir = new File("D:\\Jap\\Accent");
        DataExtractor.extractRows(dir, "a");
    }
    
    
    public static void test1() throws Exception
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
                break;
            }
        }

        rd.close();
        
        System.out.println(writer.toString());
        
        writer.close();
    }

    
}
