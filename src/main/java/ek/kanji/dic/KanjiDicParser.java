package ek.kanji.dic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class KanjiDicParser
{
    public static interface Callback
    {
        boolean onEntry(KanjiDicEntry entry) throws Exception;
    }

    private Callback callback;
    private KanjiDicEntry entry;

    
    public KanjiDicParser(Callback cb)
    {
        this.callback = cb;   
    }
    
    
    public void parse(File file) throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while((line = reader.readLine()) != null) 
        {
            if(line.equals("<character>"))
            {
                entry = new KanjiDicEntry();
            }
            else if(line.equals("</character>"))
            {
                if(!callback.onEntry(entry)) break;
            }
            else if(line.startsWith("<literal>"))
            {
                entry.kanji = line.charAt("<literal>".length());
            }
            else if(line.startsWith("<grade>"))
            {
                int idx = line.indexOf("</");
                entry.grade = Integer.parseInt(line.substring("<grade>".length(), idx));
            }
            else if(line.startsWith("<reading "))
            {
                if(line.contains("\"ja_on\""))
                {
                    entry.onReading.add(extractReading(line));
                }
                else if(line.contains("\"ja_kun\""))
                {
                    entry.kunReading.add(extractReading(line));
                }
            }            
            
        }
        
        reader.close();
    }
    
    
    private String extractReading(String line)
    {
        int start = line.indexOf(">", 15);
        int end = line.indexOf("</", 15);
        return line.substring(start + 1, end);
    }
}
