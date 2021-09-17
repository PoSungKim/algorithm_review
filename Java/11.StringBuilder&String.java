import java.util.*;

class Solution {
    public int StringBuilder() {
        
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
        
        return 0;
    }
    public int String() {
	// https://mine-it-record.tistory.com/133
        // 문자열 reverse나 비교는 String 사용 
	    
	// ==           : 참조하는 주소 값 비교
	// .equals()    : 참조하는 String 클래스의 값 비교
	// .compareTo() : 참조하는 String 클래스의 값 비교 (1) 길이 같다면, 다른 char의 차이값 리턴 (2) 길이가 다르다면, 길이의 차이 리턴
	
        String str = "1212200";
	String str2= "2212300";

	System.out.println(str.compareTo(str2));

	String revStr = new StringBuilder(str).reverse().toString();
	String revStr2 = new StringBuilder(str2).reverse().toString();

	System.out.println(revStr2.compareTo(revStr));
        
        // char[] to String
        char[] chArr =  str.toCharArray();
        String bStr = new String(chArr);
        String bStr2 = String.valueOf(chArr);
        StringBuilder tmp = new StringBuilder();
	    
        for (char ch : chArr) tmp.append(ch);
        String bstr3 = tmp.toString();
	    
	// lowercase()
	String test = "test";
	System.out.println(test.indexOf("te"));
	System.out.println(test.toLowerCase());
	System.out.println(test.toUpperCase());
	for(char c : test) System.out.print(Character.toUpperCase(c));
	    
	String emptyString = " test    ";
	System.out.println(emptyString.trim());
    }
}
