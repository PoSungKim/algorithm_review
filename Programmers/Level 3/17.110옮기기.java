// 사전순으로 앞으로 나오려면 값이 작아야 하고, 1이 가장 뒤에 몰려 있어야 한다
// 이에 따라, 최대한 1을 뒤에 놓고, 0이 시작되는 지점을 찾아서 110을 넣어야 1을 뒤에 더 모을 수 있다는 컨셉의 문제
// 뒤에서부터 0과 1의 유무를 계산해야 하기 때문에 Stack을 사용했고, 
// String 리턴 값을 취해야 하기에 String Builder와 .insert(0, String str)로 Stack에서 그대로 값을 넣어준다

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
