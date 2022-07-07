class Solution {
    public boolean isPalindrome(int x) {
        String whole = Integer.toString(x);
        int len      = whole.length();
        int mid      = len/2;
            
        String left  = whole.substring(0, mid);
        String right = whole.substring(len % 2 == 0 ? mid : mid + 1);
        right = new StringBuilder(right).reverse().toString();
        
        return left.equals(right);
    }
}
