import java.util.* ;

class Solution {
    Stack<Character> Stack = new Stack<>();
    List<Character>  Right  = new ArrayList<>(Arrays.asList(')', '}', ']'));
    Map<Character, Character> Map = new HashMap<Character, Character>() {
        {
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }
    };
    public int solution(String S) {
        for(int i = 0; i < S.length(); i++) {
            Character curChar = S.charAt(i);
            if (Right.contains(curChar)) {
                if (Stack.isEmpty()) 
                    return 0;
                
                if (Stack.peek() != Map.get(curChar))
                    return 0;
                else
                    Stack.pop();

            } else {
                Stack.push(curChar);                
            }
        }
        return Stack.isEmpty() ? 1 : 0;
    }
}
