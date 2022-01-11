// https://codechacha.com/ko/java-regex/

class Solution {
    // 문자열 IndexOf나 비교는 String 사용
    // reverse는 StringBuilder 사용 
    public int String() {
      // https://mine-it-record.tistory.com/133 
	    
      // ==           : 참조하는 주소 값 비교
      // .equals()    : 참조하는 String 클래스의 값 비교
      // .compareTo() : 참조하는 String 클래스의 값 비교 (1) 길이 같다면, 다른 char의 차이값 리턴 (2) 길이가 다르다면, 길이의 차이 리턴
      String str  = Integer.toString(Integer.parseInt("1212200"));
      String str2 = "2212300";

      System.out.println(str.compareTo(str2));

      String revStr     = new StringBuilder(str).reverse().toString();
      String revStr2    = new StringBuilder(str2).reverse().toString();

      System.out.println(revStr2.compareTo(revStr));
        
      // char[] to String
      char[] chArr      = str.toCharArray();
      String bStr       = new String(chArr);
      String bStr2      = String.valueOf(chArr);
	    
      StringBuilder tmp = new StringBuilder();
      for (char ch : chArr) tmp.append(ch);
      String bstr3 = tmp.toString();

      String test = "test";
      // 1) 포함
      System.out.println(test.contains("tes"));                             // true
      System.out.println(test.indexOf("te"));                               // 0
      System.out.println(test.matches("^[t][e][s][t]$"));                   // true
      System.out.println(test.matches("^[t|e|s]{4,4}$"));                   // true  >> at least, no more than 4
      System.out.println(test.matches("^[t|e|s]*$"));                       // true  >> * {0,}
      System.out.println(test.matches("^[t|e|s]+$"));                       // true  >> + {1,}
      System.out.println(test.matches("^[t|e|s]?$"));                       // false >> ? {0,1}
	    
      // 2) replaceAll
      System.out.println(test.replaceAll("^(t)(e)(s)(t)$", "$0"));          // $0 >> all  
      System.out.println(test.replaceAll("^(t)(e)(s)(t)$", "$4-$3-$2-$1")); // t-s-e-t 
	    
      // 3) toLowerCase(), toUpperCase
      System.out.println(test.toLowerCase());
      System.out.println(test.toUpperCase());
      for(char c : test) System.out.print(Character.toUpperCase(c));

      // 4) trim
      String emptyString = " test    ";
      System.out.println(emptyString.trim());
	    
      // 5) startsWith, endsWith
      System.out.println(test.startsWith("te"));
      System.out.println(test.endsWith("st"));
	    
      // repeat(int n) >> 현재 문자열 5개를 concat
      System.out.println("O".repeat(5)); // 00000
    }
}
