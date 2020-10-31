package ek.util;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class Counter<T>
{
    private Map<T, Integer> map;

    
    public Counter()
    {
        map = new TreeMap<>();
    }
    
    
    public void add(T key)
    {
        map.merge(key, 1, Integer::sum);
    }
    
    
    public List<T> topKeys(int n) 
    {
        return map.entrySet().stream()
            .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
            .limit(n)
            .map(e -> e.getKey())
            .collect(Collectors.toList());
    }
    
    
    public int size()
    {
        return map.size();
    }
}
