class Solution {
    public int solution(int[] A) {
        int ans = 987654321, left = A[0], right = 0;
        for(int i = 1; i < A.length; i++)
            right += A[i];
        ans = Math.abs(left - right);
        for(int p = 1; p < A.length - 1; p++) 
            ans = Math.min(ans, Math.abs( (left += A[p]) - (right -= A[p])));
        
        return ans;
    }
}
// Time Complexity : O(N)
