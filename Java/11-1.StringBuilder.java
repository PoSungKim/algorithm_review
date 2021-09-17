import java.util.*;

class Solution {
    
    // 문자열 수정 작업이 많으면 StringBuilder 사용
    public int StringBuilder() {
        StringBuilder sb = new StringBuilder("StringBuilder");
        
        System.out.println(sb.append("aa"));            // StringBuilderaa
        
        System.out.println(sb.insert(0, "test"));       // testStringBuildera
        
        sb.setCharAt(0, 'T');                           // TestStringBuildera
        
        System.out.println(sb.charAt(sb.length()-1));   // a
        
        System.out.println(sb.substring(0,4));          // Test

        System.out.println(sb.delete(0, 4));            // StringBuildera
        
        sb.setLength(6);                                // String
        System.out.println(sb);
        
        return 0;
    }
}
