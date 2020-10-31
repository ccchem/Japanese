package tt;

import java.io.File;

import ek.util.KanjiCounter;
import ek.util.UnicodeUtils;


public class Test1
{
    public static void main(String[] args) throws Exception    
    {
        test2();
    }
    
    
    public static void test1() throws Exception
    {
        String t1 = "なんていう車なんですか";
        
        for(int i = 0; i < t1.length(); i++)
        {
            char ch = t1.charAt(i);
            if(UnicodeUtils.isKanji(ch))
            {
                System.out.println(ch);
            }
        }
    }
    
    
    public static void test2() throws Exception
    {
        File file = new File("/ws4/Jap/Books/1Q84_BOOK01.txt");
        
        KanjiCounter counter = new KanjiCounter();
        counter.add(file);
        
        System.out.println("Total: " + counter.size());
        System.out.println();
        
        int num = 0;
        for(Character ch: counter.mostCommon(100))
        {
            num++;
            System.out.format("%3d -> %s\n", num, ch);
        }
    }
    
}
