import java.util.*;

class Solution {
    final static int INF = 987654321;
    int minCnt = INF;
    int[] permLst, circle;
    List<List<Integer>> ls = new ArrayList<>();
    
    public void perm(int cnt, int[] dist, int visited) {
        
        if (cnt == dist.length) {
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i = 0; i < dist.length; i++) temp.add(permLst[i]);
            ls.add(temp);
        }
        
        for(int i = 0; i < dist.length; i++) {
            if ( (visited & (1 << i)) != 0) continue;
            permLst[cnt] = dist[i];
            perm(cnt + 1, dist, visited | (1 << i));
        }
        
    }
    
    public void findMin(List<Integer> ls, int[] weak) {
        
       for(int i = 0; i < weak.length; i++) {
           int start = weak[i],
               pos = weak[i],
               idx = 0;
           
           for( ;idx < ls.size() && pos <= circle[circle.length - 1]; ) {
               pos += ls.get(idx++);
               if (idx >= minCnt)
                   break;
               
               int cnt = 0,
                   newPos = pos;
               for(int j = 0; j < circle.length; j++) {
                   if (start <= circle[j] && circle[j] <= pos) {
                       cnt++;
                       if (cnt == weak.length) {
                           minCnt = Math.min(minCnt, idx);
                           break;
                       }
                       newPos = circle[j + 1]; 
                   }
               }
               
               pos = newPos;
           }
       } 
    }
    public void createCircle(int[] weak, int n) {
        circle = new int[weak.length * 2];
        for(int i = 0 ; i < weak.length; i++) circle[i] = weak[i];
        for(int i = weak.length ; i < weak.length * 2; i++) circle[i] = weak[i - weak.length] + n;
    }
    public int solution(int n, int[] weak, int[] dist) {
        createCircle(weak, n);
        Arrays.sort(dist);
        
        permLst = new int[dist.length];
        perm(0, dist, 0);
        
        for(int i = ls.size() - 1; i >= 0; i--) 
            findMin(ls.get(i), weak);
        
        return INF == minCnt ? -1 : minCnt;
    }
}
