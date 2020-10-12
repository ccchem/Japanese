package tt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import ek.util.CounterMap;
import ek.util.UnicodeUtils;

public class Test1
{
    public static void main(String[] args) throws Exception    
    {
        test2();
    }
    
    
    public static void test1() throws Exception
    {
        String t1 = "なんていう車なんですか";
        
        for(int i = 0; i < t1.length(); i++)
        {
            char ch = t1.charAt(i);
            if(UnicodeUtils.isKanji(ch))
            {
                System.out.println(ch);
            }
        }
    }
    
    
    public static void test2() throws Exception
    {
        File file = new File("/ws4/Jap/Books/1Q84_BOOK01.txt");
        
        BufferedReader rd = new BufferedReader(new FileReader(file));
        
        FrequencyCounter fc = new FrequencyCounter();
        
        String line;
        while((line = rd.readLine()) != null)
        {
            fc.add(line);
        }
        
        rd.close();
        
        CounterMap cmap = fc.getCounters();
        List<CounterMap.Item> list = cmap.getCounts();
        int num = 0;
        
        System.out.println("Total: " + list.size());
        System.out.println();
        
        for(CounterMap.Item item: list)
        {
            System.out.println(item.key + ", " + item.count);
            num++;
            if(num >= 100) break;
        }
    }
    
}
