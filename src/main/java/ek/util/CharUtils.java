package ek.util;

public class CharUtils
{
    public static boolean isHiragana(char ch)
    {
        return (ch >= '\u3041' && ch <= '\u309F');
    }

    
    public static boolean isKatakana(char ch)
    {
        return (ch >= '\u30A0' && ch <= '\u30FF');
    }

    
    public static boolean isKanji(char ch)
    {
        return (ch >= '\u4e00' && ch <= '\u9FAF') || (ch >= '\u3400' && ch <= '\u4DB5');
    }


    public static char katakanaToHiragana(char ch)
    {
        if(isKatakana(ch))
        {
            return (char)(ch - 0x60);
        } 

        return ch;
    }

    
    public static String katakanaToHiragana(String str)
    {
        if(str == null) return null;
        
        StringBuilder bld = new StringBuilder();
        
        for(int i = 0; i < str.length(); i++)
        {
            char ch = katakanaToHiragana(str.charAt(i));
            bld.append(ch);
        }
        
        return bld.toString();
    }
}
