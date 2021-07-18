package ek.shiro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import ek.util.CloseUtils;
import ek.util.Logger;


public class AssParser
{
    public static interface Callback
    {
        public boolean onEvent(String start, String style, String text);
    }
    
    
    public void parse(File file, Callback cb) throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader(file));
        
        try
        {
            boolean foundEvents = false;
            
            // Skip all lines before [Events]
            String line;
            while((line = rd.readLine()) != null)
            {
                if("[Events]".equals(line)) 
                {
                    foundEvents = true;
                    break;
                }
            }
            
            if(!foundEvents)
            {
                Logger.warn("Missing [Evenets] section.");
            }
            
            while((line = rd.readLine()) != null)
            {
                if(line.startsWith("Dialogue: "))
                {
                    line = line.substring(10);
                    String[] tokens = line.split(",");
                    String start = tokens[1];
                    String style = tokens[3];
                    String text = (tokens.length == 10) ? tokens[9] : null;
                    
                    if(!cb.onEvent(start, style, text))
                    {
                        break;
                    }
                }
            }            
        }
        finally
        {
            CloseUtils.close(rd);
        }
    }
}
