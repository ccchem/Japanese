package tt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class FastTextExtract
{

    public static void main(String[] args) throws Exception
    {
        FileWriter writer = new FileWriter("/ws2/ja.words");
        
        BufferedReader rd = new BufferedReader(new FileReader("/ws2/cc.ja.300.vec"));
        
        // Skip header
        String line = rd.readLine();
        
        while((line = rd.readLine()) != null)
        {
            int idx = line.indexOf(' ');
            if(idx > 0)
            {
                String word = line.substring(0, idx);
                writer.write(word);
                writer.write("\n");
            }
        }

        writer.close();
        rd.close();
    }

}
