package tt.trans;

import java.io.File;

import ek.trans.OnomappuToWord;

public class TestOnomappu
{

    public static void main(String[] args) throws Exception
    {
        File trans = new File("/tmp/Mistakes.txt");
        File word = new File("/tmp/Mistakes.docx");
        
        OnomappuToWord ono = new OnomappuToWord();
        ono.process(trans, word);
    }

}
