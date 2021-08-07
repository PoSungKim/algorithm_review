import java.util.*;

class Solution {

    Stack<Integer> Stack = new Stack<>();
    public int solution(int[] A, int[] B) {
        
        int curPos = 0;
        while(curPos < A.length){
            if (!Stack.isEmpty() && B[Stack.peek()] == 1 && B[curPos] == 0) {
                if (A[Stack.peek()] > A[curPos]) {
                    curPos++;
                } else {
                    Stack.pop();
                }
            } else {
                Stack.push(curPos++);
            }
        }
        return Stack.size();   
    }
}
