import java.util.*;

class Solution {

    Stack<Character> Stack = new Stack<Character>();

    public int solution(String S) {
        for(int i = 0 ; i < S.length(); i++) {
            char curChar = S.charAt(i);

            if (Stack.isEmpty())
                Stack.push(curChar);
            else 
                if (curChar == '(') 
                    Stack.push(curChar);
                else 
                    if (Stack.peek() == '(')
                        Stack.pop();
                    else   
                        return 0;
        }
        return Stack.isEmpty() ? 1 : 0;
    }
}
// Time Complexity calculated by Codility: O(N)
