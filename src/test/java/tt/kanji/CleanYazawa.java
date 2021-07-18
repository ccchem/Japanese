package tt.kanji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CleanYazawa
{
    private static class Item
    {
        public String kanji;
        public int page;
    }
    
    
    private Set<String> allKanji;
    private List<Set<Character>> gradeList;
    
    
    public CleanYazawa() throws Exception
    {
        allKanji = new TreeSet<>();
        gradeList = GradeListParser.read(new File("/tmp/ja/kyoiku.txt"));
    }
    
    
    public void processFile(File file, int grade) throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader(file));

        int rowNum = 0;
        List<Item> list = new ArrayList<>();
        
        while(true)
        {
            String kanjiLine = rd.readLine();
            if(kanjiLine == null || kanjiLine.isBlank()) break;
            String pageLine = rd.readLine();
            
            rowNum++;
            
            String[] kTokens = kanjiLine.split(" ");
            String[] pTokens = pageLine.split(" ");

            if(kTokens.length != pTokens.length) 
            {
                throw new Exception("Wrong count. Line " + rowNum + " : " + file);
            }
            
            for(int i = 0; i < kTokens.length; i++)
            {
                Item item = new Item();
                item.kanji = kTokens[i];
                item.page = Integer.parseInt(pTokens[i]);

                if(allKanji.contains(item.kanji)) 
                {
                    throw new Exception("Duplicate kanji " + item.kanji + " : " + file);
                }
                
                allKanji.add(item.kanji);
                
                list.add(item);
            }
        }
                
        rd.close();
        
        print(list, grade);
        
        //compare(list, grade);
    }
    

    private void compare(List<Item> list, int grade)
    {
        Set<Character> correct = gradeList.get(grade-1);
        Set<Character> current = new TreeSet<>();
        
        for(Item item: list)
        {
            Character ch = item.kanji.charAt(0);
            current.add(ch);
            
            if(!correct.contains(ch))
            {
                System.out.format("Extra: %d (%d) %s\n", grade, item.page, ch);
            }
        }
        
        for(Character ch: correct)
        {
            if(!current.contains(ch))
            {
                System.out.println("Missing: " + grade + "  " + ch);
            }
        }
        
    }
    
    
    private void print(List<Item> list, int grade)
    {
        Comparator<Item> comp = new Comparator<>()
        {
            @Override
            public int compare(Item o1, Item o2)
            {
                return Integer.compare(o1.page, o2.page);
            }
        };
        
        Collections.sort(list, comp);
        
        for(Item item: list)
        {
            System.out.println(item.kanji + " " + grade + " " + item.page);
        }
    }
    
    
    public static void main(String[] args) throws Exception
    {
        CleanYazawa proc = new CleanYazawa();

        for(int i = 1; i <= 6; i++)
        {
            File file = new File("/ws4/Work/Kanji/lists/yazawa/yazawa-" + i + ".txt");
            proc.processFile(file, i);
        }
    }

}
