import java.util.*;

class Solution {
    
    Set<String> S = new HashSet<>();
    Map<String, Integer> M = new HashMap<>();
    Queue<String> Q = new LinkedList<>();
    
    public int[] solution(String[] gems) {
        for(String g : gems) S.add(g);
        int total = S.size();
        
        int start = 0,
            end   = 0,
            diff  = gems.length;
        
        int[] answer = new int[2];
        for(int i = 0; i < gems.length; i++){
            String g = gems[i];
            
            M.put(g, M.getOrDefault(g, 0) + 1);
            Q.offer(g);
            
            while (Q.size() > 0 && M.get(Q.peek()) > 1) {
                start++;
                M.put(Q.peek(), M.get(Q.peek()) - 1);
                if (M.get(Q.peek()) == 0) M.remove(Q.peek());
                Q.poll();
            }
            
            if (S.size() == M.size()) {
                
                end = i;
                
                if (end - start < diff) {
                    answer[0] = start + 1;
                    answer[1] = end + 1;
                    diff = end - start;
                }
                
                start++;
                M.put(Q.peek(), M.get(Q.peek()) - 1);
                if (M.get(Q.peek()) == 0) M.remove(Q.peek());
                Q.poll();
            }
        }
        
        return answer;
    }
}
