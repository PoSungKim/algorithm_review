import java.util.*;

class Solution {
    public int solution(int[] A) {
        boolean[] List = new boolean[A.length + 2];

        for(int i = 0; i < A.length; i++) 
            List[A[i]] = true;

        for(int i = 1; i < List.length; i++)
            if (List[i] == false)
                return i;
        
        return 1;
    }
}
// Time Complexity : O(N) or O(N * log(N))
