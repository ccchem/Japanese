package ek.jmdict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class JMdictParser
{
    public static interface Callback
    {
        boolean onEntry(JMdictEntry entry) throws Exception;
    }
    
    
    private Callback callback;
    private JMdictEntry entry;


    public JMdictParser(Callback cb)
    {
        this.callback = cb;
    }
    
    
    public void parse(File file) throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while((line = reader.readLine()) != null) 
        {
            if(line.equals("<entry>"))
            {
                entry = new JMdictEntry();
            }
            else if(line.equals("</entry>"))
            {
                if(!callback.onEntry(entry)) break;
            }
            else if(line.startsWith("<ent_seq>"))
            {
                int idx = line.indexOf("</");
                entry.id = line.substring(9, idx);
            }
            else if(line.startsWith("<keb>"))
            {
                int idx = line.indexOf("</");
                entry.addKanji(line.substring(5, idx));
            }
            else if(line.startsWith("<reb>"))
            {
                int idx = line.indexOf("</");
                entry.addReading(line.substring(5, idx));
            }
            else if(line.startsWith("<pos>"))
            {
                int idx = line.indexOf("</");
                entry.addPos(line.substring(6, idx-1));
            }
            else if(line.startsWith("<gloss>"))
            {
                int idx = line.indexOf("</");
                entry.addGloss(line.substring(7, idx));
            }
        }

        reader.close();
    }

}
