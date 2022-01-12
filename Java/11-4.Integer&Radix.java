class Solution {
    // 문자열 reverse나 비교는 String 사용
    public int String() {
      // radix 10
      System.out.println(Integer.toString (1234  ));     // 1234  as String
      System.out.println(Integer.parseInt ("1234"));     // 1234  as int
      System.out.println(Integer.valueOf  ("1234"));     // 1234  as Integer
      
      // radix 5
      // 10 >> 5
      System.out.println(Integer.toString (1234,   5));  // 14414 as String 
      // 5 >> 10
      System.out.println(Integer.parseInt ("1234", 5));  // 194   as int
      // 5 >> 10 
      System.out.println(Integer.valueOf  ("1234", 5));  // 194   as Integer   
        
      System.out.println(Integer.parseInt ("1234", 2));  // java.lang.NumberFormatException 
    }
}
