package ek.trans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class YouTubeTranscriptParser
{
    public static interface Callback
    {
        public void onTimestamp(String ts);
        public void onText(String text);
    }
    
    
    public YouTubeTranscriptParser()
    {
    }

    
    public void parse(File file, Callback cb) throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader(file));
        
        String line;
        while((line = rd.readLine()) != null)
        {
            if(line.isBlank()) continue;

            line = line.trim();
            
            // Timestamp
            if(line.length() == 5 
                    && Character.isDigit(line.charAt(0)) && Character.isDigit(line.charAt(1))
                    && line.charAt(2) == ':'
                    && Character.isDigit(line.charAt(3)) && Character.isDigit(line.charAt(4))
                    )
            {
                cb.onTimestamp(line);
                continue;
            }

            cb.onText(line);
        }
        
        rd.close();
    }
    
}
