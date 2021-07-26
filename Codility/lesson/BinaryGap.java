import java.util.*;

class Solution {
    public int solution(int N) {
        
        String binRep = Integer.toString(N, 2);
        int cnt = 0, maxCnt = 0;
        boolean start = false, end = false;
        for(int i = 0; i < binRep.length(); i++) {
            if (binRep.charAt(i) == '0')
                cnt++;
            else {
                if (!start && !end)
                    start = true;
                else if (start && !end) {
                    maxCnt = Math.max(maxCnt, cnt);
                    cnt = 0;
                }
            }
        }
        return maxCnt;
    }
}
