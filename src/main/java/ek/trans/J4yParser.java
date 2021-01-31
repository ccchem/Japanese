package ek.trans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import ek.util.CharUtils;

public class J4yParser
{
    public static interface Callback
    {
        public void onRecord(String id, String jp, String en, String romaji) throws Exception;
    }
    
    
    private J4yParser()
    {
    }

    
    public static void parse(File dataFile, Callback cb) throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader(dataFile));
        
        try
        {
            String line;
            while((line = rd.readLine()) != null)
            {
                if(line.isBlank()) continue;
                
                if(isJapanese(line))
                {
                    String jp = line;
                    String en = rd.readLine();
                    String romaji = rd.readLine();
                    String mp3 = rd.readLine();
                    if(!mp3.endsWith(".mp3")) throw new Exception("Invalid sound file: " + mp3);
                    String id = mp3.substring(0, mp3.length() - 4);
                    
                    cb.onRecord(id, jp, en, romaji);
                }
            }
        }
        finally
        {
            rd.close();
        }
    }
    
    
    private static boolean isJapanese(String str)
    {
        if(str.length() < 5) return false;
        
        for(int i = 0; i < 5; i++)
        {
            char ch = str.charAt(i);
            if(CharUtils.isKanji(ch) || CharUtils.isHiragana(ch) 
                    || CharUtils.isKatakana(ch)) return true;
        }
        
        return false;
    }
}
