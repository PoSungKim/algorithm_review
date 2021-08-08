import java.util.*;

class Solution {

    Stack<Integer> Stack = new Stack<>();
    public int solution(int[] A, int[] B) {

        for(int curPos = 0; curPos < A.length;)
            if (!Stack.isEmpty() && B[Stack.peek()] == 1 && B[curPos] == 0)
                if (A[Stack.peek()] > A[curPos]) // 내려오는 물고기가 더 커서 올라오는 물고기를 먹는 케이스
                    curPos++;
                else                             // 올라오는 물고기가 더 커서 내려오는 물고기를 먹는 케이스
                    Stack.pop(); 
            else 
                Stack.push(curPos++);
                
        return Stack.size();   
    }
}
// Time Complexity calculated by Codility: O(N)
