package ek.jmdict;

import java.util.ArrayList;
import java.util.List;


public class JMdictEntry
{
    public String id;
    public List<String> kanji = new ArrayList<>();
    public List<String> reading = new ArrayList<>();
    public List<String> gloss = new ArrayList<>();
    public List<String> pos = new ArrayList<>();
    
    public void addKanji(String str)
    {
        kanji.add(str);
    }

    public void addReading(String str)
    {
        reading.add(str);
    }
    
    public void addGloss(String str)
    {
        gloss.add(str);
    }

    public void addPos(String str)
    {
        pos.add(str);
    }

}
