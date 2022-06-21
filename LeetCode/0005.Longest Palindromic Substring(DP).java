class Solution {
    public String longestPalindrome(String s) {
        
        if (s.length() < 2) return s;
        
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        
        String ans = "";
        int max = 0;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j <= i; j++) {
                
                boolean baseCase = s.charAt(i) == s.charAt(j);
                
                if(i - j > 2) dp[j][i] = dp[j + 1][i - 1] && baseCase;
                else dp[j][i] = baseCase;
                
                if (dp[j][i] && i - j + 1 > max ) {
                    max = i - j + 1;
                    ans = s.substring(j, i + 1);
                }
            }
        }
        
        return ans;
    }
}
