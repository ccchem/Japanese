package ek.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class CounterMap
{
    public static class Item implements Comparable<Item>
    {
        public Character key;
        public int count;
        
        public Item(Character key)
        {
            this.key = key;
        }
        
        public void inc()
        {
            count++;
        }

        @Override
        public int compareTo(Item other)
        {
            return Integer.compare(other.count, this.count);
        }
    }
    
    //////////////////////////////////////////////////
    
    private Map<Character, Item> map;
    
    
    public CounterMap()
    {
        map = new TreeMap<>();
    }
    
    
    public void inc(Character key)
    {
        Item item = getOrCreate(key);
        item.inc();
    }
    
    
    public List<Item> getCounts()
    {
        List<Item> list = new ArrayList<>();
        list.addAll(map.values());
        Collections.sort(list);
        
        return list;
    }
    
    
    private Item getOrCreate(Character key)
    {
        Item item = map.get(key);
        if(item == null)
        {
            item = new Item(key);
            map.put(key, item);
        }
        
        return item;
    }
}
