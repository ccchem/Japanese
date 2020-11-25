package ek.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;


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
            if(CharUtils.isKanji(ch))
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
    
    
    public List<Character> topKeys(int n)
    {
        return counter.topKeys(n);
    }
    
    
    public int size()
    {
        return counter.size();
    }
}
