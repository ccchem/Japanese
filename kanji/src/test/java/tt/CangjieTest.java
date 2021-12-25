package tt;

import java.io.FileReader;
import java.util.LinkedHashMap;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import ek.ja.kanji.KanjiLists;


public class CangjieTest
{

    public static void main(String[] args) throws Exception
    {
        LinkedHashMap<Character, String> map = createCharMap(KanjiLists.GRADE_1);
        
        JsonReader rd = new JsonReader(new FileReader("/ws4/Dic/cangjie/cj5-tc-rev.json"));
        rd.beginObject();
        
        while(rd.hasNext()) 
        {
            String kanji = rd.nextName();
            if(map.containsKey(kanji.charAt(0)))
            {
                rd.beginArray();
                String value = rd.nextString();
                while(rd.peek() != JsonToken.END_ARRAY)
                {
                    value += "," + rd.nextString(); 
                }
                rd.endArray();
                
                map.put(kanji.charAt(0), eng2ch(value));
            }
            else
            {
                rd.skipValue();
            }
        }
      
        rd.close();
        
        map.forEach((key, value) -> 
        {
            System.out.println(key + ": " + value);    
        });
        
        
        //System.out.println(count);
    }

    
    public static LinkedHashMap<Character, String> createCharMap(String str)
    {
        LinkedHashMap<Character, String> map = new LinkedHashMap<>(str.length());
        for(int i = 0; i < str.length(); i++)
        {
            map.put(str.charAt(i), "");
        }
        
        return map;
    }

    
    public static String eng2ch(String eng)
    {
        StringBuilder bld = new StringBuilder();
        
        for(int i = 0; i < eng.length(); i++)
        {
            char ch = eng.charAt(i);
            if(ch == ',') 
            {
                bld.append(" * ");
            }
            else 
            {
                bld.append(eng2ch(ch));
            }
        }
        
        return bld.toString();
    }
    
    
    public static char eng2ch(char eng)
    {
        switch(eng)
        {
        case 'a': return '日';
        case 'b': return '月';
        case 'c': return '金';
        case 'd': return '木';
        case 'e': return '水';
        case 'f': return '火';
        case 'g': return '土';
        case 'h': return '竹';
        case 'i': return '戈';
        case 'j': return '十';
        case 'k': return '大';
        case 'l': return '中';
        case 'm': return '一';
        case 'n': return '弓';
        case 'o': return '人';
        case 'p': return '心';
        case 'q': return '手';
        case 'r': return '口';
        case 's': return '尸';
        case 't': return '廿';
        case 'u': return '山';
        case 'v': return '女';
        case 'w': return '田';
        case 'x': return '難';
        case 'y': return '卜';
        case 'z': return '重';
        default: return '*';
        }
    }
}
