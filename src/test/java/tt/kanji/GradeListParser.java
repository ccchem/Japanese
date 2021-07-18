package tt.kanji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GradeListParser
{
    public static List<Set<Character>> read(File file) throws Exception
    {
        List<Set<Character>> list = new ArrayList<>(6);
        
        BufferedReader rd = new BufferedReader(new FileReader(file));
        for(int i = 0; i < 6; i++)
        {
            String line = rd.readLine();
            Set<Character> set = processLine(line);
            list.add(set);
        }
        
        rd.close();
        
        return list;
    }
    
    
    private static Set<Character> processLine(String line)
    {
        Set<Character> set = new TreeSet<>();
        
        for(int i = 3; i < line.length(); i++)
        {
            Character ch = line.charAt(i);
            if(Character.isSpaceChar(ch)) continue;
            
            set.add(ch);
        }
        
        return set;
    }
}
