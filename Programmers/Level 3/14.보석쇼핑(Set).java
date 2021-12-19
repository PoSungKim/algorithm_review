// 시간초과 풀이

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        
        Set<String> S = new HashSet<>();
        for(String g : gems)
            S.add(g);
        
        List<int[]> ansList = new ArrayList<>();
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
            ansList.add(new int[]{end - start, start + 1, end});
        }
        
        Collections.sort(ansList, (a,b)->{
            return a[0] - b[0];
        });
        
        // ansList.forEach(e->System.out.println(Arrays.toString(e)));
        return new int [] {ansList.get(0)[1], ansList.get(0)[2]};
    }
}
