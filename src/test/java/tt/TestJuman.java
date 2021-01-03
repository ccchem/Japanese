package tt;

import java.util.List;

import com.atilika.kuromoji.jumandic.Token;
import com.atilika.kuromoji.jumandic.Tokenizer;

public class TestJuman
{

    public static void main(String[] args)
    {
        String text = "シロクマくん動物園へ";
        testJumanDic(text);
    }

    
    public static void testJumanDic(String text)
    {
        Tokenizer tkz = new Tokenizer();
        List<Token> tokens = tkz.tokenize(text);
        
        for(Token token : tokens)
        {
            System.out.println(token.getSurface() + " : " 
                    + token.getBaseForm() + " -> " 
                    + token.getReading() + " -> " 
                    + token.getPartOfSpeechLevel1());
        }
    }

    
    // Parts of speech:
    // 名詞 [めいし] [meishi] - noun
    // 接尾辞 [せつびじ] - suffix
    // 助詞 [じょし] - particle
    
}
