package tt.shiro;

import java.io.File;

import ek.shiro.AssParser;
import ek.shiro.ShiroToWord;

public class TestShiroToWord
{

    public static void main(String[] args) throws Exception
    {
        File assFile = new File("/tmp/32.ass");
        File wordFile = new File("/tmp/32.docx");
        
        ShiroToWord cb = new ShiroToWord();
        AssParser ass = new AssParser();
        ass.parse(assFile, cb);
        
        cb.write(wordFile);        
    }

}
