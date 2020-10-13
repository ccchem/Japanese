package tt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import ek.util.Counter;
import ek.util.UnicodeUtils;


public class KanjiCounter
{
    private Counter<Character> counter;
    
    
    public KanjiCounter()
    {
        counter = new Counter<>();
    }

    
    public void add(String text)
    {
        for(int i = 0; i < text.length(); i++)
        {
            char ch = text.charAt(i);
            if(UnicodeUtils.isKanji(ch))
            {
                counter.add(ch);
            }
        }
    }

    
    public void add(File file) throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader(file));

        String line;
        while((line = rd.readLine()) != null)
        {
            add(line);
        }
        
        rd.close();
    }
    
    
    public List<Character> mostCommon(int n)
    {
        return counter.mostCommon(n);
    }
    
    
    public int size()
    {
        return counter.size();
    }
}
