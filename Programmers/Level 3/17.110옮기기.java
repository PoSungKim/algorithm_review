import java.util.*;

class Solution {
    
    public String getMinDict(String str) {
        
        Stack<Character> Stack = new Stack<>();
        int cnt = 0;
        for(char c : str.toCharArray()){
            
            if (Stack.size() >= 2) {
                char x = Stack.pop();
                char y = Stack.pop();
                
                if (x == '1' && y == '1' && c =='0') {
                    cnt++;
                    continue;
                } else {
                    Stack.push(y);
                    Stack.push(x);
                }
            }
            
            Stack.push(c);
        }
        
        StringBuilder sb = new StringBuilder();
        boolean isZero = false;
        int idx = Stack.size();
        while(!Stack.isEmpty()) {
            char c = Stack.pop();
            
            if (c == '0') {
                isZero = true;
            }
            
            if (!isZero && c == '1') {
                idx--;
            }
            
            sb.insert(0, c);
        }
        
        if (cnt > 0) {
            while(cnt-- > 0) {
                sb.insert(idx, "110");
                idx += 3;
            }
        } else return sb.toString();
        
        return sb.toString();
    }
    
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i = 0; i < s.length; i++) 
            answer[i] = getMinDict(s[i]);
        
        return answer;
    }
    
}
