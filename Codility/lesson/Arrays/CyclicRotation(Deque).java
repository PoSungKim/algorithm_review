import java.util.*;

class Solution {
    Deque<Integer> Deq;

    public void rotate() {
        Deq.offerFirst(Deq.pollLast());
    }
    
    public int[] solution(int[] A, int K) {

        if (A.length == 0)
            return A;

        Deq = new ArrayDeque<Integer>() {
            {
                for(int i = 0; i < A.length; i++)
                    offerLast(A[i]);
            }
        };
        
        for(int i = 0; i < K; i++)
            rotate();

        for(int i = 0; i < A.length; i++) 
            A[i] = Deq.pollFirst();
        
        return A;
    }
}
