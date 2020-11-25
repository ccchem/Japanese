package tt;

import java.util.List;

import ek.util.CharUtils;


public class TestKuromoji
{

    public static void main(String[] args)
    {
        String text = "シロクマくん暇だから何か問題出してよ";
        
        //testIpaDic(text);
        testJumanDic(text);
        //testUniDic(text);
    }


    public static void testIpaDic(String text)
    {
        com.atilika.kuromoji.ipadic.Tokenizer tkz = new com.atilika.kuromoji.ipadic.Tokenizer();
        List<com.atilika.kuromoji.ipadic.Token> tokens = tkz.tokenize(text);
        
        for(com.atilika.kuromoji.ipadic.Token token : tokens) 
        {
            String reading = CharUtils.katakanaToHiragana(token.getReading());
            System.out.println(token.getBaseForm() + " -> " + reading + " -> " + token.getPartOfSpeechLevel1());
        }

    }

    
    public static void testUniDic(String text)
    {
        com.atilika.kuromoji.unidic.Tokenizer tkz = new com.atilika.kuromoji.unidic.Tokenizer();
        List<com.atilika.kuromoji.unidic.Token> tokens = tkz.tokenize(text);
        
        for(com.atilika.kuromoji.unidic.Token token : tokens) 
        {
            String pron = CharUtils.katakanaToHiragana(token.getPronunciation());
            System.out.println(token.getSurface() + " (" + pron + ") -> " + token.getPartOfSpeechLevel1());

            String base = token.getLemma();
            String basePron = CharUtils.katakanaToHiragana(token.getLemmaReadingForm());
            
            System.out.println("    " + base + " -> " + basePron);
        }

    }

    
    public static void testJumanDic(String text)
    {
        com.atilika.kuromoji.jumandic.Tokenizer tkz = new com.atilika.kuromoji.jumandic.Tokenizer();
        List<com.atilika.kuromoji.jumandic.Token> tokens = tkz.tokenize(text);
        
        for(com.atilika.kuromoji.jumandic.Token token : tokens)
        {
            System.out.println(token.getSurface() + " : " + token.getBaseForm() + " -> " + token.getReading() + " -> " + token.getPartOfSpeechLevel1());
        }

    }
    
}
