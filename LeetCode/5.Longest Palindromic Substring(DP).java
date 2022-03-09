class Solution {
    public String longestPalindrome(String s) {
        
        if (s.length() < 2) return s;
        
        String ans = "";
        int max = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        
        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j <= i; j++) {
                
                boolean baseCase = s.charAt(i) == s.charAt(j);
                dp[j][i] = i - j > 2 ? dp[j + 1][i - 1] && baseCase : baseCase;
                
                if (dp[j][i] && i - j + 1 > max) {
                    max = i - j + 1;
                    ans = s.substring(j, i + 1);
                }
            }
        }
        return ans;
    }
}
