package tt.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class TestSentenceSegmenter
{

    public static void main(String[] args) throws Exception
    {
        BufferedReader rd = new BufferedReader(new FileReader("/ws4/Jap/Books/1Q84/1Q84_BOOK03.txt"));
        FileWriter wr = new FileWriter("/ws4/Jap/Books/1Q84/1/book3-sen.txt", Charset.forName("UTF8"));

        String line;
        while((line = rd.readLine()) != null)
        {
            split(line, wr);
        }
        
        wr.close();
        rd.close();        
    }

    
    private static void split(String text, Writer wr) throws Exception
    {
        int len = text.length();

        boolean first = true;
        
        for(int i = 0; i < len; i++)
        {
            char ch = text.charAt(i);            
            if(ch == '　') continue;
            
            if(ch == '。' || ch == '、')
            {
                first = true;
                wr.write(ch);
                wr.write("\n");
                continue;
            }
            if(ch == '「' && !first)
            {
                first = false;
                wr.write("\n");
                wr.write(ch);
                continue;
            }
            if(ch == '」' && i < len-1)
            {
                first = true;
                wr.write(ch);
                wr.write("\n");
                continue;
            }
            
            first = false;
            wr.write(ch);
        }
        
        wr.write("\n");
    }
}
