class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        char[] charArray = s.toCharArray();
        LinkedList<Character> charQueue = new LinkedList<>();
        int ans = 0;
        
        for(char ch : charArray) {
            if (!charQueue.contains(ch)) {
                charQueue.offer(ch);
                ans = Math.max(ans, charQueue.size());
            } else {
                
                while (charQueue.size() > 0 && charQueue.peek().charValue() != ch)
                    charQueue.poll();
                
                if (charQueue.size() > 0 && charQueue.peek().charValue() == ch)
                    charQueue.offer(charQueue.poll());
            }
        }
        
        return ans;
    }
}
