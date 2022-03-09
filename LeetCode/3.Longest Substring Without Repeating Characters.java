class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        char[] charArray = s.toCharArray();
        int[] numArray = new int[s.length()];
        
        for(int i = 0; i < charArray.length; i++) {
            Set<Character> strSet = new HashSet<>();
            strSet.add(charArray[i]);
            numArray[i] = 1;
            for(int j = i + 1; j < charArray.length; j++) {
                if (!strSet.contains(charArray[j])) {
                    strSet.add(charArray[j]);
                    numArray[i]++;
                } else break;
            }
        }
        
        int ans = 0;
        for(int num : numArray) ans = Math.max(ans, num);
        
        return ans;
    }
}
