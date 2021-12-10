import java.util.*;

class Solution {
    class Route implements Comparable<Route> {
        int start, end;
        public Route(int start, int end) { 
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Route a) {
            return this.end - a.end;
        }
        
        @Override
        public String toString() {
            return String.format("(%s, %s)", start, end);
        }
    }
    
    List<Route> L = new ArrayList<>();
    
    public int solution(int[][] routes) {
        for(int[] route : routes) L.add(new Route(route[0], route[1]));
        Collections.sort(L);
        
        int answer = 1,
            tmp    = L.get(0).end;
        
        for(int i = 1; i < L.size(); i++) {
            Route next = L.get(i);
            if (tmp < next.start) {
                answer++;
                tmp = next.end;
            } 
        }
        
        return answer;
    }
}
