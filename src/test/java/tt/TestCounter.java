package tt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import ek.util.KanjiCounter;
import ek.util.CharUtils;
import ek.util.Counter;


public class TestCounter
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
            if(CharUtils.isKanji(ch))
            {
                System.out.println(ch);
            }
        }
    }
    
    
    public static void test2() throws Exception
    {
        File file = new File("/ws4/Work/1Q84-1-1.txt");
        //File file = new File("/ws4/Jap/Kids/Shirokuma Cafe/ep3.txt");
        
        KanjiCounter counter = new KanjiCounter();
        counter.add(file);
        
        Map<String, String> db = loadKanjiDB();
        
        System.out.println("Total: " + counter.size());
        System.out.println();
        
        int num = 0;
        for(Character ch: counter.topKeys(200))
        {
            num++;
            System.out.format("%3d -> %s (%s)\n", num, ch, db.get(ch.toString()));
        }
    }
 
    
    private static Map<String, String> loadKanjiDB() throws Exception
    {
        Map<String, String> map = new HashMap<>(3000);
        
        File exFile = new File("/ws4/Work/Kanji/lists/joyo.txt");
        BufferedReader rd = new BufferedReader(new FileReader(exFile));

        String line;
        while((line = rd.readLine()) != null)
        {
            String[] tokens = line.split("\\|");
            if(tokens.length != 5) continue;

            map.put(tokens[1], tokens[2]);
        }

        rd.close();
        
        return map;
    }
}
