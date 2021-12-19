// 효율성에서 시간초과 풀이

import java.util.*;

class Solution {
    
    public long calc(int[] tmp) {
        long sum = 0;
        for(int i = 0; i < tmp.length; i++)
            sum += tmp[i] * tmp[i];
        
        return sum;
    }
    
    public long solution(int n, int[] works) {
        
        while(n > 0) {
            Arrays.sort(works);
            if(works[works.length - 1] > 0)
                works[works.length - 1]--;
            n--;
        }
        
        return calc(works);
    }
}
