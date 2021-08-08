import java.util.*;

class Solution {

    List<Integer> List;
    public int[] solution(int[] A, int K) {
        if( A.length == 0)
            return A;

        List = new ArrayList<Integer>() {
            {
                for(int i = 0; i < A.length; i++)
                    add(A[i]);
            }
        };

        Collections.rotate(List, K);
        
        for(int i = 0; i < A.length; i++)
            A[i] = List.get(i);

        return A;
    }
}
