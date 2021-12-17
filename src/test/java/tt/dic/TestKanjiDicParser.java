package tt.dic;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ek.kanji.dic.KanjiDicEntry;
import ek.kanji.dic.KanjiDicParser;


public class TestKanjiDicParser
{
    private static class CB implements KanjiDicParser.Callback
    {
        private int count = 0;

        @Override
        public boolean onEntry(KanjiDicEntry entry) throws Exception
        {
            if(entry.grade != 1) return true;
            
            count++;

            System.out.print(entry.kanji); 
            System.out.print("|");
            System.out.print(entry.grade);
            System.out.print("|");
            printList(entry.kunReading);
            System.out.print("|");
            printList(entry.onReading);
            System.out.println();
            
            if(count > 10) return false;
            
            return true;
        }
    }
 
    
    private static class TestCB implements KanjiDicParser.Callback
    {
        public int count = 0;
        public Set<String> on = new TreeSet<>();

        @Override
        public boolean onEntry(KanjiDicEntry entry) throws Exception
        {
            if(entry.grade >= 1 && entry.grade <= 6)
            {
                on.addAll(entry.onReading);
            }

            return true;
        }
    }


    private static class SearchCB implements KanjiDicParser.Callback
    {
        public int count = 0;

        @Override
        public boolean onEntry(KanjiDicEntry entry) throws Exception
        {
            if(entry.grade >= 1 && entry.grade <= 6)
            {
                if(entry.onReading.contains("ワ"))
                {
                    count++;
                    System.out.println(count + ": " + entry.kanji + ", " + entry.grade + ", " 
                        + entry.onReading + ", " + entry.kunReading);
                }
            }

            return true;
        }
    }

    
    public static void printList(List<String> list)
    {
        if(list == null || list.isEmpty()) return;
        
        System.out.print(list.get(0));
        for(int i = 1; i < list.size(); i++)
        {
            System.out.print("," + list.get(i));
        }
    }
    

    public static void main(String[] args) throws Exception
    {
        search1();
    }
    

    public static void search1() throws Exception
    {
        File file = new File("/ws4/Dic/kanjidic2.xml");
        
        SearchCB cb = new SearchCB();
        KanjiDicParser parser = new KanjiDicParser(cb);
        parser.parse(file);
    }
    
    
    public static void test1() throws Exception
    {
        File file = new File("/ws4/Dic/kanjidic2.xml");
        
        TestCB cb = new TestCB();
        KanjiDicParser parser = new KanjiDicParser(cb);
        parser.parse(file);
        
        System.out.println(cb.on.size());
        
        int count = 0;
        for(String str: cb.on)
        {
            count++;
            System.out.print(str + "  ");
            if(count % 10 == 0) System.out.println();
        }
    }

}
