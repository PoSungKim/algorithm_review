import java.util.*;

class Solution {
    Map<Integer, Integer> Map = new HashMap<>();
    int ans = -1;
    public int solution(int[] A) {
        for(int i = 0; i < A.length; i++)
            Map.put(A[i], Map.getOrDefault(A[i], 0) + 1);
        
        for(Map.Entry<Integer, Integer> E : Map.entrySet()) {
            if (E.getValue()%2 != 0) {
                ans = E.getKey();
                break;
            }
        }
        
        return ans;
    }
}
// Detected Time Complexity : O(N) or O(N*log(N))
