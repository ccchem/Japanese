package tt.rad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;


public class ExtractRadicals
{

    public static void main(String[] args) throws Exception
    {
        Set<Character> filter = readYazawa("2");
        //System.out.println(yazawa);
        parseRadicals(filter);
    }

    
    private static Set<Character> readYazawa(String grade) throws Exception
    {
        Set<Character> list = new HashSet<>();
        
        BufferedReader rd = new BufferedReader(new FileReader("/ws4/Dic/RAD/yazawa.txt"));
        String line;
        while((line = rd.readLine()) != null)
        {
            String[] tokens = line.split(" ");
            if(tokens.length != 3) continue;
            
            if(tokens[1].equals(grade))
            {
                list.add(tokens[0].charAt(0));
            }
        }

        rd.close();
        
        return list;
    }


    private static void parseRadicals(Set<Character> filter) throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader("/ws4/Dic/RAD/kradfile-u"));
        String line;
        while((line = rd.readLine()) != null)
        {
            if(line.length() == 0) continue;
            char kanji = line.charAt(0);
            
            if(filter.contains(kanji))
            {
                System.out.println(line);
            }
        }
        
        rd.close();
    }

}
