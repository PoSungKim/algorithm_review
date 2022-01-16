import java.util.*;

class Solution {
    
    List<List<String>> List = new ArrayList<>();
    Set<String> Set = new HashSet<>();
    Set<Set<String>> ans = new HashSet<>();
    
    public void calc(int pos, int n) {
        
        if (pos == n) {
            ans.add(new HashSet<>(Set));
            return;
        }
        
        for(String str : List.get(pos)) {
            if (Set.contains(str)) continue;
            Set.add(str);
            calc(pos + 1, n);
            Set.remove(str);
        }
        
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        for(int i = 0; i < banned_id.length; i++) {
            String banned = banned_id[i];
            List<String> tmp = new ArrayList<>();
            String rule = banned.replace("*", ".");
            for(String user : user_id) 
                if (user.matches(rule)) 
                    tmp.add(user);
            
            List.add(tmp);
        }
        
        calc(0, banned_id.length);
        
        return ans.size();
    }
}
