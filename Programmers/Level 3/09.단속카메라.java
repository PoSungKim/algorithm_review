import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int curTime = -30001,
            ans     = 0;
        
        Arrays.sort(routes, (a,b) -> {
            return a[1] - b[1];
        });
        
        for(int[] route : routes) {
            if (curTime < route[0] ) {
                curTime = route[1];
                ans++;
            }
        }
        return ans;
    }
}
