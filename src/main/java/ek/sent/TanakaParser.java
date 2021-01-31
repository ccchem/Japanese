package ek.sent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;

public class TanakaParser
{
    public static interface Callback
    {
        boolean onRecord(String id, String jp, String en) throws Exception;
    }
    
    
    public TanakaParser()
    {
    }


    public static void parse(File file, Callback cb) throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader(file, Charset.forName("UTF-8")));
        
        try
        {
            String line;
            while((line = rd.readLine()) != null)
            {
                if(line.startsWith("A: "))
                {
                    String tokens[] = line.split("\t");
                    if(tokens.length != 2)
                    {
                        System.out.println("WARN: Missing TAB separator: " + line);
                        continue;
                    }
                    
                    int idx = tokens[1].indexOf("#ID=");
                    if(idx < 0)
                    {
                        System.out.println("WARN: Missing ID: " + line);
                        continue;
                    }
    
                    String jp = tokens[0].substring(3);
                    String en = tokens[1].substring(0, idx);
                    String id = tokens[1].substring(idx + 4);
                    
                    if(!cb.onRecord(id, jp, en)) break;
                }
            }
        }
        finally
        {
            rd.close();
        }
    }
}
