import java.util.Stack;

class Solution {
    
    public boolean isCorrect(String u) {
        
        Stack<Character> S = new Stack<>();
        for(int i = 0; i < u.length(); i++)
            if (u.charAt(i) == '(') 
                S.push('(');
            else 
                if (S.isEmpty())
                    return false;
                else
                    S.pop();
        
        return true;
    }
    
    public int split(String p) {
        
        int left = 0, right = 0, pos = 0;
        
        for(int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') 
                left++;
            else
                right++;
            
            if (left == right) {
                pos = i + 1;
                break;
            }
        }
        return pos;
    }
    
    public String solution(String p) {
        
        if (p.isEmpty())
            return "";
        
        int pos = split(p);
        String u = p.substring(0, pos), v = p.substring(pos);
        
        if (isCorrect(u))
            return u + solution(v);
        
        String answer = "(" + solution(v) + ")";
        
        for(int i = 1; i < u.length() - 1; i++)
            answer += (u.charAt(i) == '(')? ')' : '(';
        
        return answer;
    }
}
