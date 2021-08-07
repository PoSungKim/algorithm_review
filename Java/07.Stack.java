import java.util.*;

class Solution {
    
    Stack<Character> Stack = new Stack<>();
  
    public int solution(int[] A, int[] B) {

        Stack.push('a'); // ['a']
        Stack.push('b'); // ['a', 'b']
        Stack.push('c'); // ['a', 'b', 'c']
        Stack.isEmpty() // False
          
        System.out.println(Stack.peek()); // 'c'
        
        Stack.pop(); // ['a', 'b']
        Stack.pop(); // ['a']
        Stack.pop(); // []
        Stack.isEmpty() // True
          
        return 0;
    }
}


