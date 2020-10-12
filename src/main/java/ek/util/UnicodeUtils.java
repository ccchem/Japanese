package ek.util;

public class UnicodeUtils
{
    public static boolean isHiragana(int code)
    {
        return (code >= 0x3041 && code <= 0x309F);
    }

    
    public static boolean isKatakana(int code)
    {
        return (code >= 0x30A0 && code <= 0x30FF);
    }

    
    public static boolean isKanji(int code)
    {
        return (code >= 0x4e00 && code <= 0x9FAF) || (code >= 0x3400 && code <= 0x4DB5);
    }
}
