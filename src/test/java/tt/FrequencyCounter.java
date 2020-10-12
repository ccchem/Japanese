package tt;

import ek.util.CounterMap;
import ek.util.UnicodeUtils;


public class FrequencyCounter
{
    private CounterMap counters;
    
    
    public FrequencyCounter()
    {
        counters = new CounterMap();
    }

    
    public void add(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            char ch = str.charAt(i);
            if(UnicodeUtils.isKanji(ch))
            {
                counters.inc(ch);
            }
        }
    }
    
    
    public CounterMap getCounters()
    {
        return counters;
    }
}
