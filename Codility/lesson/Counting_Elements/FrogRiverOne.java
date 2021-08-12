import java.util.*;

class Solution {
    public int solution(int X, int[] A) {
        Map<Integer, Integer> HM = new HashMap<>();
        for(int i = 1; i <= X; i++)
            HM.put(i, i);
        
        for(int i = 0; i < A.length; i++) {
            if (HM.containsKey(A[i]))
                HM.remove(A[i]);
            
            if (HM.isEmpty())
                return i;
        }
        return -1;
    }
}

