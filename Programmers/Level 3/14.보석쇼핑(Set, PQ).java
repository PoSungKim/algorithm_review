import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        
        Set<String> S = new HashSet<>();
        for(String g : gems)
            S.add(g);
        
        PriorityQueue<int[]> ansPQ = new PriorityQueue<>((a,b)->{
            return a[0] - b[0];
        });
        int total = S.size();
        
        for(int pos = 0; pos < gems.length; pos++) {
            S.clear();
            int start = pos,
                end = pos,
                size = 0;
            
            while(size != total) {
                if (end >= gems.length) {
                    end = 987654321;
                    break;
                }
                S.add(gems[end++]);
                size = S.size();
            }
            ansPQ.offer(new int[]{end - start, start + 1, end});
        }
        
        return new int [] {ansPQ.peek()[1], ansPQ.peek()[2]};
    }
}
