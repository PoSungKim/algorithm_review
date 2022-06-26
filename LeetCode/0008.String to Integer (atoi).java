class Solution {
    public int myAtoi(String s) {
        int idx  = 0,
            sign = 1,
            n    = s.length();
        
        int res = 0;
        
        while(idx < n && s.charAt(idx) == ' ') 
            idx++;
        
        if (idx < n &&  s.charAt(idx) == '+') {
            sign = 1;
            idx++;
        } else if (idx < n && s.charAt(idx) == '-') {
            sign = -1;
            idx++;
        }
        
        while (idx < n && Character.isDigit(s.charAt(idx))) {
            int digit = Character.getNumericValue(s.charAt(idx));
            
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE ||
                (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            
            res = res * 10 + digit;
            
            idx++;
        }
        
        return sign * (int)res;
    }
}
