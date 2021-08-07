import java.util.*;

class Solution {

    Stack<Integer> Stack = new Stack<>();
    public int solution(int[] A, int[] B) {
        int curPos = 0;
        while(curPos < A.length){
            boolean didWhile = false;
            while (curPos < A.length && !Stack.isEmpty() && B[Stack.peek()] == 1 && B[curPos] == 0) {
                didWhile = true;
                if (A[Stack.peek()] > A[curPos]) {
                    curPos++;
                    break;
                } else {
                    Stack.pop();
                }
            }
            if (didWhile == false)
                Stack.push(curPos++);
        }
        return Stack.size();   
    }
}

// Time Complexity calculated by Codility: O(N)
