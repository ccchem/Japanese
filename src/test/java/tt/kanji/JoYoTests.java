package tt.kanji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import ek.util.Counter;

public class JoYoTests
{

    public static void main(String[] args) throws Exception
    {
        File exFile = new File("/ws4/Work/Kanji/lists/joyo.txt");
        BufferedReader rd = new BufferedReader(new FileReader(exFile));

        Counter<String> cnt = new Counter<>();
        
        String line;
        while((line = rd.readLine()) != null)
        {
            String[] tokens = line.split("\\|");
            if(tokens.length != 5) continue;
            cnt.add(tokens[2]);
        }

        rd.close();

        
        cnt.getMap().forEach((key, val) -> { System.out.println(key + " = " + val); } );
    }

}
