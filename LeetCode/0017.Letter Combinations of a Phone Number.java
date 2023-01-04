// Time  Complexity : O(4n) = O(n)
// Space Complexity : O(10 + 4^n) = O(4^n)

class Solution {

    public String[] letters = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public LinkedList<String> ans = new LinkedList();
    
    public List<String> letterCombinations(String digits) {
        
        if (digits.length() == 0) return ans;
        ans.add("");

        while(ans.size() > 0 && ans.peek().length() != digits.length()) {
            String a   = ans.remove();
            String opt = letters[digits.charAt(digits.length() - 1 - a.length()) - '0'];
        
            for(char ch : opt.toCharArray()) ans.add(ch + a);       
        }

        return ans;
    }

}
