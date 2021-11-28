package tt.dic;

import java.io.File;

import ek.jmdict.JMdictEntry;
import ek.jmdict.JMdictParser;

public class TestJMdicParser
{
    private static class CB implements JMdictParser.Callback
    {
        private int count = 0;

        @Override
        public boolean onEntry(JMdictEntry entry) throws Exception
        {
            if(!entry.pos.contains("exp")) return true;
            
            count++;
            
            System.out.println(entry.kanji + " " + entry.reading 
                    + " " + entry.gloss + " " + entry.pos);
            
            if(count > 10) return false;
            
            return true;
        }
        
    }
    
    
    public static void main(String[] args) throws Exception
    {
        //File file = new File("/tmp/ja/jmdict_1.xml");
        File file = new File("/ws4/Dic/JMdict_e");
        
        JMdictParser parser = new JMdictParser(new CB());
        parser.parse(file);
    }

}
