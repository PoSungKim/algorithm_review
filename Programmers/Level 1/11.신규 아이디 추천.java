import java.lang.reflect.Method;
import java.util.HashSet;

class Solution {
    
    public String solution(String new_id) {
        
        //for(Method m : sb.getClass().getMethods()) System.out.println(m);
        return new KakaoID().one(new_id).two().three().four().five().six().seven();
    }
}

class KakaoID {
    
    StringBuilder sb; 
    HashSet<String> hs; 
    
    KakaoID() {
        sb = new StringBuilder();
        hs = new HashSet<>();
    }
    
    KakaoID one(String s){
        sb.append(s.toLowerCase());
        return this;
    }
    
     KakaoID two() {
        hs.add("-"); hs.add("_"); hs.add(".");
        for(int i = 0; i < sb.length(); i++) 
            if (!isValid(sb.charAt(i))) {
                sb.deleteCharAt(i);
                i--;
            }
        return this;
    }
    
     KakaoID three() {
        for(int i = 0; i < sb.length(); i++) 
            if (sb.charAt(i) == '.' && i > 0 && sb.charAt(i - 1) == '.') {
                sb.deleteCharAt(i);   
                i--;
            }
        return this;
    }
    
     KakaoID four() {
        while(sb.length() > 0 && sb.charAt(0) == '.')
            sb.deleteCharAt(0);
        
        while(0 < sb.length() && sb.charAt(sb.length() - 1) == '.') 
            sb.deleteCharAt(sb.length() - 1);
        
        return this;
    }
    
     KakaoID five() {
        if(sb.length() == 0)
            sb.append("a");
        
        return this;
    }
    
     KakaoID six() {
        if (sb.length() >= 16)
            sb.setLength(15);
        
        while(0 < sb.length() && sb.charAt(sb.length() - 1) == '.') 
            sb.deleteCharAt(sb.length() - 1);
        
        return this;
    }
    
     String seven() {
        while(sb.length() <= 2)
            sb.append(sb.charAt(sb.length()-1));
        
        return sb.toString();
    }
    
     boolean isValid(char c) {
        return ( ('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || hs.contains(String.valueOf(c)));
    }
}
