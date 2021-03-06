package tt.trans;

import java.io.File;

import ek.trans.OnomappuToWord;

public class TestOnomappu
{

    public static void main(String[] args) throws Exception
    {
        File trans = new File("/ws4/Jap/Onomappu/Trains/Trains.txt");
        File word = new File("/ws4/Jap/Onomappu/Trains/Trains.docx");
        
        OnomappuToWord ono = new OnomappuToWord();
        ono.process(trans, word);
    }

}
