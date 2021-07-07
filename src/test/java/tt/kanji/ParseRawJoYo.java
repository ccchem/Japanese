package tt.kanji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ParseRawJoYo
{

    public static void main(String[] args) throws Exception
    {
        File exFile = new File("/ws4/Work/Kanji/lists/jojo.txt");
        BufferedReader rd = new BufferedReader(new FileReader(exFile));

        int count = 0;
        String line;
        
        // Skip header
        line = rd.readLine();
        
        while(true)
        {
            line = rd.readLine();
            rd.readLine();  // Skip second line
            
            String[] tokens = line.split("\t");
            if(tokens.length != 9) break;
            
            int id = Integer.parseInt(tokens[0].trim());
            String kanji = tokens[1].trim();
            String grade = tokens[5].trim();
            
            String eng = tokens[7].trim();
            String reading = tokens[8].trim();
    
            System.out.println(id + "|" + kanji+ "|" + grade+ "|" + eng+ "|" + reading);
        }
        
        rd.close();

    }

}
