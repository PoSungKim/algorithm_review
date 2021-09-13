import java.util.*;

class Solution {
    public int solution(int[] A) {
        
        // 문자열 수정 작업이 많으면 StringBuilder 사용
        StringBuilder sb = new StringBuilder("StringBuilder");
        
        System.out.println(sb.append("aa"));            // StringBuilderaa
        
        System.out.println(sb.insert(0, "test"));       // testStringBuildera
        
        sb.setCharAt(0, 'T');                           // TestStringBuildera
        
        System.out.println(sb.charAt(sb.length()-1));   // a
        
        System.out.println(sb.substring(0,4));          // Test

        System.out.println(sb.delete(0, 4));            // StringBuildera
        
        sb.setLength(6);                                // String
        System.out.println(sb);

        // 문자열 reverse나 비교는 String 사용 
        String str = "1212200";
	String str2= "2212300";

	System.out.println(str.compareTo(str2));

	String revStr = new StringBuilder(str).reverse().toString();
	String revStr2 = new StringBuilder(str2).reverse().toString();

	System.out.println(revStr2.compareTo(revStr));
        
        char [] chArr =  str.toCharArray();
        String bStr = new String(chArr);
        String bStr2 = String.valueOf(chArr);

        return 0;
    }
}
