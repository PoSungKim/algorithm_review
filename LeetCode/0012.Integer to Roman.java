import java.util.*;

class Solution {
    
    public static int[] vals = new int[] {1000, 500, 100, 50, 10, 5, 1};
    public static int[] divs = new int[] {1000, 100, 100, 10, 10, 1, 1};
    
    public static String[] syms = new String[] {"M", "D", "C", "L", "X", "V", "I"};
    public static String[] nines = new String[] {"", "CM", "CD", "XC", "XL", "IX", "IV"};
  
    public static String ans = "";
    
    public String findAns(int num, int place) {
        
        if (place >= 7) return "";
        // parsing and manipulating every digit
        // ex) 987 : 9 --> 8 --> 7
        int n = num / divs[place];
        
        if (n > 0) {
            if (n == 9) {
                num %= divs[place];
                return nines[place] + findAns(num, place + 1);
            } else if (n == 4) {
                num %= divs[place];
                return nines[place + 1] + findAns(num, place + 1);
            } else {
                n = num / vals[place];
                num %= vals[place];             
                return syms[place].repeat(n) + findAns(num, place + 1);
            }
        } else {
            return findAns(num, place + 1);
        }
    }
    
    public String intToRoman(int num) {
        return findAns(num, 0);
    }
}

// Runtime: 5 ms, faster than 94.52% of Java online submissions for Integer to Roman.
// Memory Usage: 41.6 MB, less than 98.57% of Java online submissions for Integer to Roman.
