import java.util.*;

class Solution {
    Queue<Integer> Queue;

    public void rotate() {
        Queue.offer(Queue.poll());
    }
    public int[] solution(int[] A, int K) {
        Queue = new LinkedList<Integer>() {
            {
                for(int i = A.length - 1; i >= 0; i--)
                    offer(A[i]);
            }
        };

        for(int i = 0; i < K; i++)
            rotate();

        for(int i = A.length - 1; i >= 0; i--)
            A[i] = Queue.poll();

        return A;
    }
}
