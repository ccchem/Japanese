package tt;

import java.io.FileReader;

public class ExportDialogs
{

    public static void main(String[] args) throws Exception
    {
        FileReader rd = new FileReader("/ws4/Books/1Q84/src/1Q84_BOOK01.txt");
        
        boolean start = false;
        int count = 0;
        
        int ch;
        while((ch = rd.read()) > 0)
        {
            switch(ch)
            {
            case '「':
                start = true;
                break;
            case '」':
                start = false;
                count++;
                System.out.println();
                System.out.println();
                break;
            default:
                if(start) System.out.print((char)ch);
            }
            
            
            if(count >= 100) break;
        }
        
        rd.close();
    }

}
