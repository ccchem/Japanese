package tt.dic;

import java.io.File;
import java.util.List;

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
        File file = new File("/ws4/Dic/kanjidic2.xml");
        
        KanjiDicParser parser = new KanjiDicParser(new CB());
        parser.parse(file);
        
    }

}
