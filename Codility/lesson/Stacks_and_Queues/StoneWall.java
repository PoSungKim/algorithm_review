import java.util.*;

class Solution {
    Stack<Integer> Stack = new Stack<>();
    public int solution(int[] H) {
        int ans = H.length;

        for(int i = 0; i < H.length; i++) {
            if (Stack.isEmpty())
                Stack.push(H[i]);
            else {
                if (Stack.peek() < H[i]) {
                    Stack.push(H[i]);
                } else {
                    while(!Stack.isEmpty() && Stack.peek() > H[i]) {
                        Stack.pop();
                    }
                    while (!Stack.isEmpty() && Stack.peek() == H[i]) {
                        Stack.pop();
                        ans--;
                    }
                    Stack.push(H[i]);
                }
            }
        }
        
        return ans;
    }
}
// Time Complexity calculated by Codility: O(N)
