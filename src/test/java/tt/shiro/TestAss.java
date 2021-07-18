package tt.shiro;

import java.io.File;
import java.util.List;

import ek.shiro.AssParser;
import ek.util.Counter;

public class TestAss
{
    private static class WordFrequencyCB implements AssParser.Callback
    {
        private com.atilika.kuromoji.ipadic.Tokenizer tkz;
        
        public Counter<String> counter = new Counter();
        
        
        public WordFrequencyCB()
        {
            tkz = new com.atilika.kuromoji.ipadic.Tokenizer();
        }
        

        @Override
        public boolean onEvent(String start, String style, String text)
        {
            if("日文".equals(style))
            {
                String time = start.substring(2, start.length()-3);
                //System.out.println(time + ": " + text);
                if(text == null) return true;
                
                List<com.atilika.kuromoji.ipadic.Token> tokens = tkz.tokenize(text);
                
                for(com.atilika.kuromoji.ipadic.Token token : tokens) 
                {
                    String base = token.getBaseForm();
                    String pos = token.getPartOfSpeechLevel1();
                    if(!pos.equals("名詞") && !pos.equals("動詞") 
                            && !pos.equals("形容詞") && !pos.equals("副詞")
                            && !pos.equals("接続詞")) continue;
                    
                    if(base.equals("*") || base.equals("さん") || base.equals("する")) continue;
                
                    counter.add(base);
                }
                
                return true;
            }
            else
            {
                return true;
            }
        }
    }
    
    
    public static void main(String[] args) throws Exception
    {
        File file = new File("/tmp/ja/shiro-01.ass");
        AssParser ass = new AssParser();
        WordFrequencyCB cb = new WordFrequencyCB();
        
        ass.parse(file, cb);
        
        System.out.println(cb.counter.size());
        for(String key: cb.counter.getMap().keySet())
        {
            System.out.println(key);
        }
    }

}
