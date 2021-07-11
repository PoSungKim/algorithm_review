import java.util.HashMap;
import java.util.Map;

class Solution {
    
    public String solution(String[] participant, String[] completion) {
        
        Map<String, Integer> hm = new HashMap<String, Integer> ();
        
        for(String p : participant) 
            hm.put(p, hm.getOrDefault(p, 0) + 1);
        
        for(String c : completion) 
            hm.put(c, hm.get(c) - 1);
        
        String a = new String();
        for(String k : hm.keySet())
            if (hm.get(k) != 0)
                a = k;
        
        return a;
    }
}
