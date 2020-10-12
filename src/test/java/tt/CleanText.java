package tt;

import java.io.BufferedReader;
import java.io.FileReader;


public class CleanText
{

    public static void main(String[] args) throws Exception
    {
        clean1();
    }
    
    
    private static void clean1() throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader("/ws4/Jap/words/na-adj.txt"));
        String line;

        int count = 0;
        
        while((line = rd.readLine()) != null)
        {
            line = line.trim();
            if(line.length() > 0)
            {
                count++;
                System.out.print(line);
                if(count == 5)
                {
                    System.out.println();
                    count = 0;
                }
                else
                {
                    System.out.print("\t");
                }
            }
        }

        rd.close();
    }

}
