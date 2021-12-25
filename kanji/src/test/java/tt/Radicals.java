package tt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;

import ek.ja.kanji.KanjiLists;

public class Radicals
{
    public static void main(String[] args) throws Exception
    {
        Set<Character> set = KanjiLists.createCharSet(KanjiLists.GRADE_2); 
        
        BufferedReader rd = new BufferedReader(new FileReader("/ws4/Dic/RAD/kradfile-u"));
        
        String line;
        while((line = rd.readLine()) != null)
        {
            if(line.startsWith("#") || line.length() == 0) continue;
            if(set.contains(line.charAt(0)))
            {
                System.out.println(line);
            }
        }
        
        rd.close();
    }

}
