import java.lang.reflect.Method;
import java.util.HashSet;

class Solution {
    
    StringBuilder sb = new StringBuilder();
    HashSet<String> hs = new HashSet<>();
    
    public String solution(String new_id) {
        one(new_id);
        two();
        three(); 
        four();
        five();
        six();
        seven();
    
        //for(Method m : sb.getClass().getMethods()) System.out.println(m);
        return sb.toString();
    }
    
    public void one(String s){
        sb.append(s.toLowerCase());
    }
    
    public void two() {
        hs.add("-"); hs.add("_"); hs.add(".");
        for(int i = 0; i < sb.length(); i++) 
            if (!isValid(sb.charAt(i))) {
                sb.deleteCharAt(i);
                i--;
            }
                
    }
    
    public void three() {
        for(int i = 0; i < sb.length(); i++) 
            if (sb.charAt(i) == '.' && i > 0 && sb.charAt(i - 1) == '.') {
                sb.deleteCharAt(i);   
                i--;
            }   
    }
    
    public void four() {
        while(sb.length() > 0 && sb.charAt(0) == '.')
            sb.deleteCharAt(0);
        
        while(0 < sb.length() && sb.charAt(sb.length() - 1) == '.') 
            sb.deleteCharAt(sb.length() - 1);
    }
    
    public void five() {
        if(sb.length() == 0)
            sb.append("a");
    }
    
    public void six() {
        if (sb.length() >= 16)
            sb.setLength(15);
        
        while(0 < sb.length() && sb.charAt(sb.length() - 1) == '.') 
            sb.deleteCharAt(sb.length() - 1);
    }
    
    public void seven() {
        while(sb.length() <= 2)
            sb.append(sb.charAt(sb.length()-1));
    }
    
    public boolean isValid(char c) {
        return ( ('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || hs.contains(String.valueOf(c)));
    }
}
