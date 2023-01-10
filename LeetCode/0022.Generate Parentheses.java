class Solution {

    List<String> ans = new ArrayList<>();

    public void makeComb(int left, int right, int n, StringBuilder curStr) {
        
        if (curStr.length() == n * 2) {
            ans.add(curStr.toString());
            return;
        }

        if (left < n) {
            makeComb(left + 1, right, n, curStr.append("("));
            curStr.deleteCharAt(curStr.length() - 1);
        }
        
        if (left > right) {
            makeComb(left, right + 1, n, curStr.append(")"));
            curStr.deleteCharAt(curStr.length() - 1);
        }
            
    }

    public List<String> generateParenthesis(int n) {
        makeComb(0, 0, n, new StringBuilder());
        return ans;
    }
}
